package com.wahidabd.domain.anime

import com.wahidabd.common.core.Resource
import com.wahidabd.domain.anime.model.Anime
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 11/5/2023.
 * Github github.com/wahidabd.
 */


interface AnimeUseCase {
    suspend fun getAnime(): Flow<Resource<List<Anime>>>
    suspend fun getAnimeById(id: Int): Flow<Resource<Anime>>
}