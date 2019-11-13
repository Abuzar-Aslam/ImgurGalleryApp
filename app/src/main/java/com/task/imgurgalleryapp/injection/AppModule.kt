package com.task.imgurgalleryapp.injection

import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import com.task.imgurgalleryapp.R
import com.task.imgurgalleryapp.ui.gallery.GalleryViewModel
import com.task.imgurgalleryapp.data.GalleryApi
import com.task.imgurgalleryapp.ui.about.AboutViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Abuzar on 11/11/2019.
 */

val viewModelModule = module {


    viewModel {
        GalleryViewModel(
            androidContext(),
            get()
        )
    }

    viewModel {
        AboutViewModel(
            androidContext()
        )
    }

}


val datModule = module {

    single {


        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(androidContext().getString(R.string.baseurl))
            .build()

        retrofit.create(GalleryApi::class.java)
    }

}