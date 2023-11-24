package com.wahidabd.data.remote

import com.wahidabd.common.core.Resource
import com.wahidabd.data.remote.dto.AnimeListResponse
import com.wahidabd.data.remote.dto.AnimeResponse


/**
 * Created by Wahid on 11/2/2023.
 * Github github.com/wahidabd.
 */


interface AnimeRepository {
    suspend fun getAnime(): Resource<AnimeListResponse>
    suspend fun getAnimeById(id: Int): Resource<AnimeResponse>
}