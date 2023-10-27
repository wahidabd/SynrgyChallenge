package com.wahidabd.synrgy.domain.comment

import com.wahidabd.synrgy.data.local.model.CommentEntity
import com.wahidabd.synrgy.domain.comment.model.Comment
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 10/27/2023.
 * Github github.com/wahidabd.
 */


interface CommentUseCase {
    suspend fun create(data: Comment)
    suspend fun update(id: Long, body: String)
    suspend fun delete(id: Long)
    fun getList(): Flow<List<Comment>>
    fun getListById(id: Long): Flow<List<Comment>>
}