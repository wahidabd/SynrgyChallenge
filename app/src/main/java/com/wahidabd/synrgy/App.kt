package com.wahidabd.synrgy

import android.app.Application
import com.wahidabd.di.appDatabase
import com.wahidabd.di.appModule
import com.wahidabd.di.features.animeModule
import com.wahidabd.di.features.commentModule
import com.wahidabd.synrgy.di.presentationModule
import com.wahidabd.synrgy.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


/**
 * Created by Wahid on 10/26/2023.
 * Github github.com/wahidabd.
 */


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                appModule,
                appDatabase,
                animeModule,
                commentModule,
                presentationModule,
                viewModelModule,
            )
        }
    }

}