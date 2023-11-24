package com.wahidabd.di

import com.wahidabd.data.local.AppDatabase
import org.koin.dsl.module


/**
 * Created by Wahid on 10/27/2023.
 * Github github.com/wahidabd.
 */


val appDatabase = module {
    single { AppDatabase.getDatabase(get()) }
}