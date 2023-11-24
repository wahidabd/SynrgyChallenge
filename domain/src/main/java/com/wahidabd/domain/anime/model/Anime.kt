package com.wahidabd.domain.anime.model

import com.wahidabd.data.remote.dto.AnimeResponse


/**
 * Created by wahid on 11/5/2023.
 * Github github.com/wahidabd.
 */


data class Anime(
    val malId: Int? = 0,
    val url: String? = "",
    val images: ImageJpg? = null,
    val title: String? = "",
    val type: String? = "",
    val source: String? = "",
    val episodes: Int? = 0,
    val status: String? = "",
    val score: Double? = 0.0,
)

fun com.wahidabd.data.remote.dto.AnimeResponse.toDomain(): Anime =
    Anime(
        malId = malId,
        url = url,
        images = images?.toDomain(),
        title = title,
        type = type,
        source = source,
        episodes = episodes,
        status = status,
        score = score
    )