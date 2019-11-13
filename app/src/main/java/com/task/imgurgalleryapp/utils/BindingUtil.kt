package com.task.imgurgalleryapp.utils

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.task.imgurgalleryapp.ui.gallery.GalleryViewModel


/**
 * Created by Abuzar on 11/11/2019.
 */


@BindingAdapter("visible")
fun setVisible(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("onScrollListener")
fun setOnScrollListener(
    view: RecyclerView,
    viewModel: GalleryViewModel?
) {
    val newValue: RecyclerView.OnScrollListener?
    if (viewModel == null) {
        return
    }
    newValue = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            viewModel.onScrollStateChanged(recyclerView, newState)
        }
    }
    view.addOnScrollListener(newValue)
}