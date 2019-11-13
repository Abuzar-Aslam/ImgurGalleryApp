package com.task.imgurgalleryapp.interactors

import android.content.Context
import io.reactivex.observers.DisposableObserver

/**
 * Created by Abuzar on 11/11/2019.
 */

abstract class BaseUseCaseSubscriber<T> (private val context: Context) : DisposableObserver<T>() {

    override fun onComplete() {}

    override fun onError(e: Throwable) {

    }

    override fun onNext(t: T) {}

}
