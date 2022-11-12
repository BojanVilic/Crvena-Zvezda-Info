package com.bojanvilic.crvenazvezdainfo

import android.app.Application
import com.bojanvilic.crvenazvezdainfo.koin.appModule
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@HiltAndroidApp
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(appModule)
        }

        MobileAds.initialize(this) {}
    }

}