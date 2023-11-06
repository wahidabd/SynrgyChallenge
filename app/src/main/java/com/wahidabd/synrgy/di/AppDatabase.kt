package com.wahidabd.synrgy.di

import com.wahidabd.synrgy.data.local.AppDatabase
import com.wahidabd.synrgy.utils.AuthDataStore
import org.koin.dsl.module


/**
 * Created by Wahid on 10/27/2023.
 * Github github.com/wahidabd.
 */


val appDatabase = module {
    single { AppDatabase.getDatabase(get()) }
    single { AuthDataStore.getInstance(get()) }
}