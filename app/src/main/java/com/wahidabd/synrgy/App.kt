package com.wahidabd.synrgy

import android.app.Application
import com.wahidabd.synrgy.di.appDatabase
import com.wahidabd.synrgy.di.appModule
import com.wahidabd.synrgy.di.commentModule
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
                commentModule
            )
        }
    }

}