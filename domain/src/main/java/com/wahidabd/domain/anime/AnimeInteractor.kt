package com.wahidabd.domain.anime

import com.wahidabd.common.core.Resource
import com.wahidabd.data.remote.AnimeRepository
import com.wahidabd.domain.anime.model.Anime
import com.wahidabd.domain.anime.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by wahid on 11/5/2023.
 * Github github.com/wahidabd.
 */


class AnimeInteractor(private val repository: AnimeRepository) :
    AnimeUseCase {

    override suspend fun getAnime(): Flow<Resource<List<Anime>>> = flow {
        when (val response = repository.getAnime()) {
            is Resource.Success ->
                emit(
                    Resource.success(
                        response.data.data.map { anime -> anime.toDomain() }
                    )
                )

            is Resource.Error ->
                emit(
                    Resource.error(
                        response.message
                    )
                )

            is Resource.Loading -> emit(Resource.loading())
        }
    }

    override suspend fun getAnimeById(id: Int): Flow<Resource<Anime>> {
        return flow {
            when (val response = repository.getAnimeById(id)) {
                is Resource.Success -> emit(Resource.success(response.data.toDomain()))
                is Resource.Error -> emit(Resource.error(response.message))
                is Resource.Loading -> emit(Resource.loading())
            }
        }
    }

}