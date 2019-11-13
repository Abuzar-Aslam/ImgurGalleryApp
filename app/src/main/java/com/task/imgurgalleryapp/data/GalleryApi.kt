package com.task.imgurgalleryapp.data

import com.task.imgurgalleryapp.data.model.GalleryModel
import io.reactivex.Observable
import com.task.imgurgalleryapp.data.model.ApiResponse
import retrofit2.http.*

/**
 * Created by Abuzar on 11/11/2019.
 */

interface GalleryApi {
    @GET("3/gallery/{section}/{sort}/{window}/{page}")
    fun fetchGallery(
        @Header("Authorization") secretKey: String?, @Path("section") section: String?, @Path("sort") sort: String?,
        @Path("page") page: Int?, @Path("window") window: String?,
        @Query("showViral") showViral: Boolean?,@Query("mature") mature: Boolean?,@Query("album_previews") album_previews: Boolean?
    ): Observable<ApiResponse<GalleryModel>>
}