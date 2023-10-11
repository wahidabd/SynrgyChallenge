package com.wahidabd.synrgy.domain


/**
 * Created by Wahid on 10/11/2023.
 * Github github.com/wahidabd.
 */


data class Movie(
    val id: Long,
    val poster_path: String,
    val title: String,
    val genre_ids: List<Long>
)
