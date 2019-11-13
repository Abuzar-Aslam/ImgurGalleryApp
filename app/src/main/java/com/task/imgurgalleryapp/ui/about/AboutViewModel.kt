package com.task.imgurgalleryapp.ui.about

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import android.content.pm.PackageManager
import com.task.imgurgalleryapp.BuildConfig
import java.util.*


/**
 * Created by Abuzar on 11/13/2019.
 */
class AboutViewModel(context: Context) : ViewModel() {

    val authorName = ObservableField("abuzaraslam96@gmail.com")
    val appVersion = ObservableField("")
    val buildTime = ObservableField("")

    init {
        try {
            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            appVersion.set(pInfo.versionName)
            buildTime.set(Date(BuildConfig.TIMESTAMP).toString())

        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }


}