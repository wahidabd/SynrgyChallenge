package com.wahidabd.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Created by wahid on 11/5/2023.
 * Github github.com/wahidabd.
 */


@Serializable
data class ImageJpgResponse(
    val jpg: ImageResponse? = null
)

@Serializable
data class ImageResponse(
    @SerialName("image_url")
    val imageUrl: String? = null,
    @SerialName("small_image_url")
    val smallImageUrl: String? = null
)