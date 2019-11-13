package com.task.imgurgalleryapp.ui.gallery

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.task.imgurgalleryapp.data.GalleryApi
import com.task.imgurgalleryapp.data.model.GalleryModel
import com.task.imgurgalleryapp.data.model.GalleryRequestModel
import com.task.imgurgalleryapp.data.model.ApiResponse
import com.task.imgurgalleryapp.interactors.BaseUseCaseSubscriber
import com.task.imgurgalleryapp.interactors.GalleryUseCase
import androidx.recyclerview.widget.RecyclerView
import com.task.imgurgalleryapp.R


/**
 * Created by Abuzar on 11/11/2019.
 */

class GalleryViewModel(
    private val context: Context,
    private val galleryApi: GalleryApi
) : ViewModel() {


    private var galleryNavigator: GalleryNavigator? = null
    var refreshing = ObservableBoolean(true)
    var lastItem = ObservableBoolean(false)
    private var galleryRequestModel: GalleryRequestModel? = null
    var galleryList = ArrayList<GalleryModel>()
    private var pageNumber = 0

    init {
        galleryRequestModel = GalleryRequestModel(
            showViral = true,
            mature = true,
            album_previews = true,
            window = context.getString(R.string.window),
            clientId = context.getString(R.string.clientId),
            page = pageNumber,
            section = context.getString(R.string.section_hot),
            sort = context.getString(R.string.sort)
        )
    }

    fun setNavigator(galleryNavigator: GalleryNavigator) {
        this.galleryNavigator = galleryNavigator
    }

    fun getGalleryApi() {
        refreshing.set(true)
        GalleryUseCase(galleryApi).execute(galleryRequestModel, GallerySubscriber(context))
    }

    fun onSectionChanged(radioGroup: RadioGroup, id: Int) {
        when (id) {
            R.id.sectionHot -> {
                onSectionStatusChanged(context.getString(R.string.section_hot))
            }
            R.id.sectionTop -> {
                onSectionStatusChanged(context.getString(R.string.section_top))
            }
        }
    }

    fun onViralChanged(radioGroup: RadioGroup, id: Int) {
        when (id) {
            R.id.showViral -> {
                onViralStatusChange(true)
            }
            R.id.hideViral -> {
                onViralStatusChange(false)
            }
        }
    }

    private fun onViralStatusChange(viral: Boolean) {
        galleryList.clear()
        this.galleryRequestModel?.showViral = viral
        getGalleryApi()
    }

    private fun onSectionStatusChanged(section: String) {
        galleryList.clear()
        this.galleryRequestModel?.section = section
        getGalleryApi()
    }


    internal inner class GallerySubscriber(context: Context) :
        BaseUseCaseSubscriber<ApiResponse<GalleryModel>>(context) {

        override fun onNext(t: ApiResponse<GalleryModel>) {
            refreshing.set(false)
            galleryNavigator?.setGalleryList(getGalleryList(t))
            lastItem.set(false)
        }

        override fun onError(e: Throwable) {
            lastItem.set(false)
            refreshing.set(false)
        }

    }

    fun getGalleryList(t: ApiResponse<GalleryModel>): ArrayList<GalleryModel> {

        for (galleryItem in t.data) {
            if (galleryItem.images != null) {
                for (image in galleryItem.images) {
                    if (image.link.isNotEmpty() && !image.link.contains("mp4")) {
                        galleryList.add(galleryItem)
                        break
                    }
                }
            }
        }
        return galleryList
    }

    fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

        if (!recyclerView.canScrollVertically(1) && !lastItem.get()) {
            lastItem.set(true)
            pageNumber++
            galleryRequestModel?.page = pageNumber
            getGalleryApi()
        }
    }

    fun onSelectItem(pos: Int) {
        when (pos) {
            0 -> galleryNavigator?.setStaggeredLayoutManager()
            1 -> galleryNavigator?.setGridLayoutManager()
            2 -> galleryNavigator?.setLinearLayoutManager()

        }
    }


}