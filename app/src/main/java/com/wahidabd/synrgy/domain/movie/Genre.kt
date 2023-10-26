package com.wahidabd.synrgy.domain.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * Created by Wahid on 10/11/2023.
 * Github github.com/wahidabd.
 */

@Parcelize
data class Genre(
    val id: Long,
    val name: String,
    val image: String
) : Parcelable
