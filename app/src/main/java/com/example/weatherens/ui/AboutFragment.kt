package com.example.weatherens.ui

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherens.R
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_about.view.*

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.fragment_about, container, false)
        ImageLoader.getInstance().displayImage("drawable://" + R.drawable.portrait, root.photo)
        requireActivity().toolbar?.title = getString(R.string.about_toolbar_title)
        requireActivity().toolbar?.subtitle = getString(R.string.about_toolbar_subtitle)
        root.content.setMovementMethod(LinkMovementMethod.getInstance())
        return root
    }


}