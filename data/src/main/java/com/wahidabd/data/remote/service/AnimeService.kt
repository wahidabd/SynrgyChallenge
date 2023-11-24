package com.wahidabd.data.remote.service

import com.wahidabd.common.core.Resource
import com.wahidabd.common.utils.Constants
import com.wahidabd.data.remote.AnimeRepository
import com.wahidabd.data.remote.dto.AnimeListResponse
import com.wahidabd.data.remote.dto.AnimeResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.get
import io.ktor.http.path


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

    override suspend fun getAnimeById(id: Int): Resource<AnimeResponse> =
        try {
            val response =
                httpClient.get(Constants.DETAIL_ANIME) {
                    url {
                        path(id.toString())
                    }
                }.body<AnimeResponse>()
            Resource.Success(response)
        } catch (e: Exception) {
            when (e) {
                is ClientRequestException -> Resource.Error(e.message)
                is ConnectTimeoutException -> Resource.Error(e.message ?: "Error")
                else -> Resource.Error(e.message ?: "Error")
            }
        }

}