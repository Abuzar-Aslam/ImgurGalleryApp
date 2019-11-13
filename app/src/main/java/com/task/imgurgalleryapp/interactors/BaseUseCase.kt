package com.task.imgurgalleryapp.interactors

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Abuzar on 11/11/2019.
 */


abstract class BaseUseCase<T>(
    private val subscribeOn: Scheduler = Schedulers.io(),
    private val observeOn: Scheduler = AndroidSchedulers.mainThread()
) {
    internal val disposables = CompositeDisposable()


    fun <O> execute(disposableObserver: O) where O : Disposable, O : Observer<T> {
        this.disposables.clear()
        this.disposables.add(
            buildUseCaseObservable()
                .compose(this.applySchedulers())
                .subscribeWith(disposableObserver)
        )
    }


    protected abstract fun buildUseCaseObservable(): Observable<T>

    internal fun buildCacheObservable(): Observable<T> {
        return Observable.empty()
    }

    /***
     * Transforms the any observable to have this UseCase's schedulers applied.
     * Useful when combining/ziping multiple observables you want to run concurrently
     */
    private fun <X> applySchedulers(): ObservableTransformer<X, X> {
        return ObservableTransformer { observable ->
            observable
                .subscribeOn(this.subscribeOn)
                .unsubscribeOn(this.subscribeOn)
                .observeOn(this.observeOn)
        }
    }
}
