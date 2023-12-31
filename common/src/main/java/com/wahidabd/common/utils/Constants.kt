package com.wahidabd.common.utils


/**
 * Created by Wahid on 10/26/2023.
 * Github github.com/wahidabd.
 */


object Constants {
    const val PREF_AUTH = "pref_auth"
    const val PREF_NAME = "pref_name"
    const val PREF_EMAIL = "pref_email"
    const val PREF_PASSWORD = "pref_password"
    const val PREF_IS_LOGIN = "pref_is_login"
    const val PREF_IMAGE = "pref_image"

    const val KTOR_TIME_OUT = 60_000

    private const val BASE_URL = "https://api.jikan.moe/v4"
    const val TOP_ANIME = "$BASE_URL/top/anime"
    const val DETAIL_ANIME = "$BASE_URL/anime/{id}"

    const val INPUT_IMAGE = "input_image_path"
    const val OUTPUT_IMAGE = "output_image_path"
}