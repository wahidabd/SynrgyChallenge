package com.wahidabd.synrgy.data.remote.service

import com.wahidabd.synrgy.common.Resource
import com.wahidabd.synrgy.common.ResponseListWrapper
import com.wahidabd.synrgy.data.remote.AnimeRepository
import com.wahidabd.synrgy.data.remote.dto.AnimeListResponse
import com.wahidabd.synrgy.data.remote.dto.AnimeResponse
import com.wahidabd.synrgy.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.get


/**
 * Created by wahid on 11/5/2023.
 * Github github.com/wahidabd.
 */


class AnimeService(private val httpClient: HttpClient) : AnimeRepository {

    override suspend fun getAnime(): Resource<AnimeListResponse> =
        try {
            val response =
                httpClient.get(Constants.TOP_ANIME).body<AnimeListResponse>()
            Resource.Success(response)
        } catch (e: Exception) {
            when (e) {
                is ClientRequestException -> Resource.Error(e.message)
                is ConnectTimeoutException -> Resource.Error(e.message ?: "Error")
                else -> Resource.Error(e.message ?: "Error")
            }
        }

}