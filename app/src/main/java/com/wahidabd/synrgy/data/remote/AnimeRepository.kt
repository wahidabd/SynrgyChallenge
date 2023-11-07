package com.wahidabd.synrgy.data.remote

import com.wahidabd.synrgy.common.Resource
import com.wahidabd.synrgy.data.remote.dto.AnimeListResponse
import com.wahidabd.synrgy.data.remote.dto.AnimeResponse


/**
 * Created by Wahid on 11/2/2023.
 * Github github.com/wahidabd.
 */


interface AnimeRepository {
    suspend fun getAnime(): Resource<AnimeListResponse>
    suspend fun getAnimeById(id: Int): Resource<AnimeResponse>
}