package com.task.imgurgalleryapp.ui.gallery

import com.task.imgurgalleryapp.data.model.GalleryModel

/**
 * Created by Abuzar on 11/11/2019.
 */

interface GalleryNavigator {


    fun setGalleryList(t: List<GalleryModel>)
    fun setLinearLayoutManager()
    fun setGridLayoutManager()
    fun setStaggeredLayoutManager()
}