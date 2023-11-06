package com.wahidabd.synrgy.common

import com.wahidabd.synrgy.domain.anime.model.Anime


/**
 * Created by wahid on 11/5/2023.
 * Github github.com/wahidabd.
 */


sealed class Resource<T> {
    data class Success<T>(val data: T): Resource<T>()
    data class Error<T>(val message: String): Resource<T>()
    class Loading<T>: Resource<T>()

    companion object {
        fun <T> success(data: T): Resource<T> = Success(data)
        fun <T> error(message: String): Resource<T> = Error(message)
        fun <T> loading(): Resource<T> = Loading()
    }
}