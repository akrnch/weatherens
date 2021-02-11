package com.example.weatherens.ui.location

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherens.R
import com.example.weatherens.databinding.FragmentAddLocationBinding
import com.example.weatherens.util.BindingAdapters
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_location.view.*

@AndroidEntryPoint
class LocationSearchFragment : Fragment() {

    private val viewModel: LocationSearchViewModel by viewModels()
    lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAddLocationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_location, container, false)
        setHasOptionsMenu(true)
        requireActivity().toolbar?.title = getString(R.string.city_search_title )
        requireActivity().toolbar?.subtitle = ""

        val adapter = SearchCityAdapter(viewModel)
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        divider.setDrawable(ColorDrawable(R.drawable.list_divider))
        binding.cities.addItemDecoration(divider)
        binding.cities.layoutManager = LinearLayoutManager(requireContext())
        binding.cities.adapter = adapter
        viewModel.citiesLiveData.observe(viewLifecycleOwner, Observer { data ->
            if (!data.isNullOrEmpty()) {
                binding.empty.visibility = View.GONE
            } else {
                binding.empty.visibility = View.VISIBLE
            }
            adapter.setData(data)
        })
        viewModel.addCityObservable.observe(viewLifecycleOwner, Observer { _ ->
            requireActivity().onBackPressed()
            BindingAdapters.bindToast(binding.root, getString(R.string.add_success))
        })
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.seacrh_menu, menu)
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        configureSearch(searchView)
        menu.performIdentifierAction(R.id.action_search, 0);
    }

    private fun configureSearch(searchView: SearchView)  {
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchText = query ?: ""
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
}