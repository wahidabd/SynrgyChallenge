package com.wahidabd.di.features

import com.wahidabd.data.remote.AnimeRepository
import com.wahidabd.data.remote.service.AnimeService
import com.wahidabd.domain.anime.AnimeInteractor
import com.wahidabd.domain.anime.AnimeUseCase
import org.koin.dsl.module


/**
 * Created by wahid on 11/6/2023.
 * Github github.com/wahidabd.
 */


val animeModule = module {
    single<AnimeRepository> {
        AnimeService(
            get()
        )
    }
    single<AnimeUseCase> { AnimeInteractor(get()) }
}