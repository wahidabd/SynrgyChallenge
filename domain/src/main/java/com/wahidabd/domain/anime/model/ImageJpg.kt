package com.wahidabd.domain.anime.model

import com.wahidabd.data.remote.dto.ImageJpgResponse
import com.wahidabd.data.remote.dto.ImageResponse


/**
 * Created by wahid on 11/5/2023.
 * Github github.com/wahidabd.
 */


data class ImageJpg(
    val jpg: Image? = null
)

data class Image(
    val imageUrl: String? = "",
    val smallImageUrl: String? = ""
)

fun com.wahidabd.data.remote.dto.ImageJpgResponse.toDomain(): ImageJpg =
    ImageJpg(jpg = jpg?.toDomain())

fun com.wahidabd.data.remote.dto.ImageResponse.toDomain(): Image =
    Image(
        imageUrl = imageUrl,
        smallImageUrl = smallImageUrl
    )