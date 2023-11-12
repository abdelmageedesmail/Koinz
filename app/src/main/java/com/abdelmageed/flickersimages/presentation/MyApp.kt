package com.abdelmageed.flickersimages.presentation

import android.app.Application
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.distribute.Distribute
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.internal.Contexts.getApplication


@HiltAndroidApp
class MyApp : Application() {


//    init {
//        System.loadLibrary("native-lib")
//    }
//
//    private external fun getAppCenterSecretKey(): String
//    override fun onCreate() {
//        super.onCreate()
//        AppCenter.start(
//            this, getAppCenterSecretKey(),
//            Distribute::class.java,
//            Analytics::class.java,
//            Crashes::class.java
//        )
//    }
}