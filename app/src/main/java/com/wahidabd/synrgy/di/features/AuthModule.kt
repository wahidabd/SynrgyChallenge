package com.wahidabd.synrgy.di.features

import com.wahidabd.synrgy.presentation.auth.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by wahid on 11/6/2023.
 * Github github.com/wahidabd.
 */


val authModule = module {
    viewModel { AuthViewModel(get()) }
}