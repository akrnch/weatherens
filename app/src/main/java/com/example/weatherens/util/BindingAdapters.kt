package com.example.weatherens.util

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.example.weatherens.data.local.WeatherEntity
import com.nostra13.universalimageloader.core.ImageLoader

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("url")
    fun bindImage(view: ImageView, url: String) {
        ImageLoader.getInstance().displayImage("http://$url", view)
    }

    @JvmStatic
    @BindingAdapter("gone")
    fun bindGone(view: View, shouldBeGone: Boolean) {
        view.visibility = if (shouldBeGone) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter("toast")
    fun bindToast(view: View, text: String?) {
        if (!text.isNullOrEmpty()) {
            Toast.makeText(view.context, text, Toast.LENGTH_SHORT).show()
        }
    }
}