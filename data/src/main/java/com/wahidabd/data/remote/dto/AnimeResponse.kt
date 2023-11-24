package com.wahidabd.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Created by wahid on 11/5/2023.
 * Github github.com/wahidabd.
 */


@Serializable
data class AnimeResponse(
    @SerialName("mal_id")
    val malId: Int? = null,
    val url: String? = null,
    val images: ImageJpgResponse? = null,
    val title: String? = null,
    val type: String? = null,
    val source: String? = null,
    val episodes: Int? = null,
    val status: String? = null,
    val score: Double? = null,
)