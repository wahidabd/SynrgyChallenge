package com.wahidabd.synrgy.di

import com.wahidabd.synrgy.presentation.auth.AuthViewModel
import com.wahidabd.synrgy.presentation.main.MainViewModel
import com.wahidabd.synrgy.presentation.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by wahid on 11/23/2023.
 * Github github.com/wahidabd.
 */


val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { AuthViewModel(get()) }
    viewModel { UserViewModel(get(), get()) }
}