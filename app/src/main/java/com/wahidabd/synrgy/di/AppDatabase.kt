package com.wahidabd.synrgy.di

import com.wahidabd.synrgy.data.AppDatabase
import org.koin.dsl.module


/**
 * Created by Wahid on 10/27/2023.
 * Github github.com/wahidabd.
 */


val appDatabase = module {
    single { AppDatabase.getDatabase(get()) }
}