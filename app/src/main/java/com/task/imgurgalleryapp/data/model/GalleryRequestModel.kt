package com.task.imgurgalleryapp.data.model


/**
 * Created by Abuzar on 11/11/2019.
 */

data class GalleryRequestModel(
    var showViral: Boolean,
    var mature: Boolean,
    var album_previews: Boolean,
    var clientId: String,
    var section: String,
    var sort: String,
    var page: Int,
    var window: String
)