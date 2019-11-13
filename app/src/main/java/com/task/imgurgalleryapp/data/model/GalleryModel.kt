package com.task.imgurgalleryapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Abuzar on 11/11/2019.
 */

@Parcelize
data class GalleryModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val desc: String,
    @SerializedName("images")
    val images: List<ImageModel>
):Parcelable

@Parcelize
data class ImageModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val desc: String,
    @SerializedName("link")
    val link: String
):Parcelable