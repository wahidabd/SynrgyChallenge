package com.wahidabd.synrgy.di

import com.wahidabd.synrgy.utils.SharedPreferences
import org.koin.dsl.module


/**
 * Created by Wahid on 10/26/2023.
 * Github github.com/wahidabd.
 */


val appModule = module {
    single { SharedPreferences(get())}
}