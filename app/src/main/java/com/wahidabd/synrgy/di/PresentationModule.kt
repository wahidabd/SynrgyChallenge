package com.wahidabd.synrgy.di

import androidx.work.WorkManager
import com.wahidabd.synrgy.utils.AuthDataStore
import org.koin.dsl.module


/**
 * Created by wahid on 11/23/2023.
 * Github github.com/wahidabd.
 */


val presentationModule = module {
    single { AuthDataStore.getInstance(get()) }
    single { WorkManager.getInstance(get()) }
}