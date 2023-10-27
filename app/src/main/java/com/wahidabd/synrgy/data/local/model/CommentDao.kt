package com.wahidabd.synrgy.data.local.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wahidabd.synrgy.data.local.model.CommentEntity
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 10/27/2023.
 * Github github.com/wahidabd.
 */


@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(data: CommentEntity)

    @Query("SELECT * FROM comment ORDER BY createdAt DESC")
    fun getList(): Flow<List<CommentEntity>>

    @Query("SELECT * FROM comment WHERE movieId = :id ORDER BY createdAt DESC")
    fun getListById(id: Long): Flow<List<CommentEntity>>

    @Query("UPDATE comment SET body = :body WHERE id = :id")
    suspend fun update(id: Long, body: String)

    @Query("DELETE FROM comment WHERE id = :id")
    suspend fun delete(id: Long)
}