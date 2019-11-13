package com.task.imgurgalleryapp.main

import android.app.Application
import com.task.imgurgalleryapp.injection.datModule
import com.task.imgurgalleryapp.injection.viewModelModule
import org.koin.android.ext.android.startKoin
import com.squareup.picasso.Picasso
import com.jakewharton.picasso.OkHttp3Downloader


/**
 * Created by Abuzar on 11/11/2019.
 */

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(viewModelModule, datModule))
        initiatePicasso()
    }

    fun initiatePicasso() {
        val builder = Picasso.Builder(this)
        builder.downloader(OkHttp3Downloader(this, Integer.MAX_VALUE.toLong()))
        val built = builder.build()
        built.setIndicatorsEnabled(true)
        built.isLoggingEnabled = true
        Picasso.setSingletonInstance(built)
    }

}