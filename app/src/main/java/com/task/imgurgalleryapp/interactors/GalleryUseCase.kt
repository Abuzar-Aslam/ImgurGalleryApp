package com.task.imgurgalleryapp.interactors

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import com.task.imgurgalleryapp.data.GalleryApi
import com.task.imgurgalleryapp.data.model.GalleryModel
import com.task.imgurgalleryapp.data.model.GalleryRequestModel
import com.task.imgurgalleryapp.data.model.ApiResponse

/**
 * Created by Abuzar on 11/11/2019.
 */


class GalleryUseCase(private val galleryApi: GalleryApi) :
    BaseUseCase<ApiResponse<GalleryModel>>() {


    private var galleryRequestModel: GalleryRequestModel? = null

    fun <O> execute(
        galleryRequestModel: GalleryRequestModel?,
        disposableObserver: O
    ) where O : Disposable, O : Observer<ApiResponse<GalleryModel>> {
        this.galleryRequestModel = galleryRequestModel
        super.execute(disposableObserver)
    }


    override fun buildUseCaseObservable(): Observable<ApiResponse<GalleryModel>> {
        return galleryApi.fetchGallery(
            galleryRequestModel?.clientId,
            galleryRequestModel?.section,
            galleryRequestModel?.sort,
            galleryRequestModel?.page,
            galleryRequestModel?.window,
            galleryRequestModel?.showViral,
            galleryRequestModel?.mature,
            galleryRequestModel?.album_previews)
    }


}