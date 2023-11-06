package com.wahidabd.synrgy.common

import kotlinx.serialization.Serializable


/**
 * Created by wahid on 11/5/2023.
 * Github github.com/wahidabd.
 */


@Serializable
data class ResponseListWrapper<T>(
    val data: T? = null,
)
