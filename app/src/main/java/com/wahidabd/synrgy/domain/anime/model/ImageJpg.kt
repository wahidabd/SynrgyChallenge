package com.wahidabd.synrgy.domain.anime.model

import com.wahidabd.synrgy.data.remote.dto.ImageJpgResponse
import com.wahidabd.synrgy.data.remote.dto.ImageResponse


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

fun ImageJpgResponse.toDomain(): ImageJpg =
    ImageJpg(jpg = jpg?.toDomain())

fun ImageResponse.toDomain(): Image =
    Image(
        imageUrl = imageUrl,
        smallImageUrl = smallImageUrl
    )