package com.wahidabd.data.local

import com.wahidabd.data.local.model.CommentEntity
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 10/27/2023.
 * Github github.com/wahidabd.
 */


interface CommentRepository {

    suspend fun create(data: CommentEntity)
    suspend fun update(id: Long, body: String)
    suspend fun delete(id: Long)
    fun getList(): Flow<List<CommentEntity>>
    fun getListById(id: Long): Flow<List<CommentEntity>>

}