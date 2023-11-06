package com.wahidabd.synrgy.data.local

import com.wahidabd.synrgy.data.local.model.CommentEntity
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 10/27/2023.
 * Github github.com/wahidabd.
 */


class CommentDataSource (private val db: AppDatabase) : CommentRepository {
    override suspend fun create(data: CommentEntity) {
        return db.commentDao().create(data)
    }

    override suspend fun update(id: Long, body: String) {
        return db.commentDao().update(id, body)
    }

    override suspend fun delete(id: Long) {
        return db.commentDao().delete(id)
    }

    override fun getList(): Flow<List<CommentEntity>> {
        return db.commentDao().getList()
    }

    override fun getListById(id: Long): Flow<List<CommentEntity>> {
        return db.commentDao().getListById(id)
    }
}