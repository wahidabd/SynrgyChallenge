package com.wahidabd.synrgy.domain.comment.model

import com.wahidabd.data.local.model.CommentEntity


/**
 * Created by Wahid on 10/27/2023.
 * Github github.com/wahidabd.
 */


data class Comment(
    val id: Long? = null,
    val movieId: Long,
    val name: String,
    val body: String,
    val createdAt: Long
)

fun com.wahidabd.data.local.model.CommentEntity.toDomain(): Comment =
    Comment(
        id = id,
        movieId = movieId,
        name = name,
        body = body,
        createdAt = createdAt
    )

fun Comment.toEntity(): com.wahidabd.data.local.model.CommentEntity =
    com.wahidabd.data.local.model.CommentEntity(
        id = id ?: 0L,
        movieId = movieId,
        name = name,
        body = body,
        createdAt = createdAt
    )