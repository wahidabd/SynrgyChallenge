package com.wahidabd.domain.comment

import com.wahidabd.data.local.CommentRepository
import com.wahidabd.synrgy.domain.comment.model.Comment
import com.wahidabd.synrgy.domain.comment.model.toDomain
import com.wahidabd.synrgy.domain.comment.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * Created by Wahid on 10/27/2023.
 * Github github.com/wahidabd.
 */


class CommentInteractor(private val repository: CommentRepository) : CommentUseCase {
    override suspend fun create(data: Comment) {
        return repository.create(data.toEntity())
    }

    override suspend fun update(id: Long, body: String) {
        return repository.update(id, body)
    }

    override suspend fun delete(id: Long) {
        return repository.delete(id)
    }

    override fun getList(): Flow<List<Comment>> {
        return repository.getList().map { list ->
            list.map { commentEntity ->
                commentEntity.toDomain()
            }
        }
    }

    override fun getListById(id: Long): Flow<List<Comment>> {
        return repository.getListById(id).map { list ->
            list.map { commentEntity ->
                commentEntity.toDomain()
            }
        }
    }
}