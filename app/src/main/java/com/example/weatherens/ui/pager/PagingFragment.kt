package com.example.weatherens.ui.pager

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.work.*
import com.example.weatherens.R
import com.example.weatherens.databinding.FragmentPagerBinding
import com.example.weatherens.util.BindingAdapters.bindToast
import com.example.weatherens.util.SyncWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class PagingFragment : Fragment() {

    private val sharedViewModel: PagingViewModel by activityViewModels()
    private lateinit var adapter: WeatherFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPagerBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_pager,
            container,
            false
        )
        setHasOptionsMenu(true)
        requireActivity().toolbar?.title = getString(R.string.app_name)
        requireActivity().toolbar?.subtitle = ""
        binding.apply {
            vm = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        sharedViewModel.weatherListLiveData.observe(viewLifecycleOwner, Observer { data ->
            if (!data.isNullOrEmpty()) {
                binding.empty.visibility = View.GONE
            } else {
                binding.empty.visibility = View.VISIBLE
            }
            adapter = WeatherFragmentAdapter(requireActivity())
            adapter.setData(data)
            binding.pager.adapter = adapter
            binding.indicator.setViewPager(binding.pager)
        })
        sharedViewModel.deletedLiveData.observe(viewLifecycleOwner, Observer { data ->
            sharedViewModel.loadSavedWeather()
        })
        sharedViewModel.loadSavedWeather()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sync) {
            val constraints: Constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            val syncRequest = OneTimeWorkRequest.Builder(SyncWorker::class.java).setConstraints(constraints).build()
            WorkManager.getInstance(requireContext()).enqueue((syncRequest))
            WorkManager.getInstance(requireContext()).getWorkInfoByIdLiveData(syncRequest.id).observe(
                this,
                Observer { workInfo ->
                    if (workInfo != null && workInfo.state.isFinished) {
                        when (workInfo.state) {
                            WorkInfo.State.SUCCEEDED -> {
                                sharedViewModel.loadSavedWeather()
                                view?.let { bindToast(it, getString(R.string.update_success)) }
                            }
                            WorkInfo.State.FAILED -> {
                                view?.let { bindToast(it, getString(R.string.update_error)) }
                            }
                            WorkInfo.State.CANCELLED -> {
                                view?.let { bindToast(it, getString(R.string.update_error)) }
                            }
                        }
                    }
                }
            )
        }
        return item.onNavDestinationSelected(findNavController()) || super.onOptionsItemSelected(
            item
        )
    }

}