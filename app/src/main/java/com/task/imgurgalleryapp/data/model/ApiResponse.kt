package com.task.imgurgalleryapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Abuzar on 11/11/2019.
 */

data class ApiResponse<out T>(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: List<T>

)