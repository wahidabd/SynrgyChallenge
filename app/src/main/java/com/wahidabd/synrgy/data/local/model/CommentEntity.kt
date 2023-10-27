package com.wahidabd.synrgy.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by Wahid on 10/27/2023.
 * Github github.com/wahidabd.
 */


@Entity(tableName = "comment")
data class CommentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val movieId: Long,
    val name: String,
    val body: String,
    val createdAt: Long
)