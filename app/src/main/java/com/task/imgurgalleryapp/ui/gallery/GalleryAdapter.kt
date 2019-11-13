package com.task.imgurgalleryapp.ui.gallery

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.task.imgurgalleryapp.R
import com.task.imgurgalleryapp.data.model.GalleryModel
import com.squareup.picasso.NetworkPolicy


/**
 * Created by Abuzar on 11/11/2019.
 */

class GalleryAdapter(private val galleryNavigator: GalleryNavigator) :
    ListAdapter<GalleryModel, GalleryAdapter.VH>(DifferentUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(parent.context,
            galleryNavigator,
            LayoutInflater.from(parent.context).inflate(R.layout.item_gallery, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    class VH(private val context: Context,private val galleryNavigator: GalleryNavigator, view: View) :
        RecyclerView.ViewHolder(view) {

        private val iv: ImageView = view.findViewById(R.id.iv)
        private val name: TextView = view.findViewById(R.id.name)

        fun bind(galleryModel: GalleryModel) {

            for (image in galleryModel.images) {
                if (!image.link.isNullOrEmpty()) {

                    loadPicassoImage(galleryModel.images[0].link)
                    break
                }
            }
            name.text = galleryModel.title
        }

        private fun loadPicassoImage(url: String) {

            Picasso.with(context)
                .load(url)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(iv, object : Callback {
                    override fun onSuccess() {
                    }
                    override fun onError() {
                        //Try again online if cache failed
                        Picasso.with(context)
                            .load(url)
                            .error(R.drawable.ic_launcher_foreground)
                            .into(iv, object : Callback {
                                override fun onSuccess() {
                                }

                                override fun onError() {
                                    Log.v("Picasso", "Could not fetch image")
                                }
                            })
                    }
                })
        }

    }
}


internal object DifferentUtil : DiffUtil.ItemCallback<GalleryModel>() {
    override fun areItemsTheSame(oldItem: GalleryModel, newItem: GalleryModel) = oldItem == newItem

    override fun areContentsTheSame(oldItem: GalleryModel, newItem: GalleryModel): Boolean {
        return false
    }
}