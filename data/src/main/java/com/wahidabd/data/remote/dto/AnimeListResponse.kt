package com.wahidabd.data.remote.dto

import kotlinx.serialization.Serializable


/**
 * Created by wahid on 11/6/2023.
 * Github github.com/wahidabd.
 */


@Serializable
data class AnimeListResponse(
    val data: List<AnimeResponse>
)
