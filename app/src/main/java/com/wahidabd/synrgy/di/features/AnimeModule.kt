package com.wahidabd.synrgy.di.features

import com.wahidabd.synrgy.data.remote.AnimeRepository
import com.wahidabd.synrgy.data.remote.service.AnimeService
import com.wahidabd.synrgy.domain.anime.AnimeInteractor
import com.wahidabd.synrgy.domain.anime.AnimeUseCase
import com.wahidabd.synrgy.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by wahid on 11/6/2023.
 * Github github.com/wahidabd.
 */


val animeModule = module {
    single<AnimeRepository> { AnimeService(get()) }
    single<AnimeUseCase> { AnimeInteractor(get()) }
    viewModel { MainViewModel(get()) }
}