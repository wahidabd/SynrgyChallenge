package com.wahidabd.di

import com.wahidabd.common.core.ktorHttpClient
import org.koin.dsl.module


/**
 * Created by Wahid on 10/26/2023.
 * Github github.com/wahidabd.
 */


val appModule = module {
    single { ktorHttpClient }
}
