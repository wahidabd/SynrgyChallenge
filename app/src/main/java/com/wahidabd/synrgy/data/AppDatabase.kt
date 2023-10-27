package com.wahidabd.synrgy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wahidabd.synrgy.data.local.model.CommentDao
import com.wahidabd.synrgy.data.local.model.CommentEntity


/**
 * Created by Wahid on 10/27/2023.
 * Github github.com/wahidabd.
 */


@Database(entities = [CommentEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "comment.db"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }
    }

    abstract fun commentDao(): CommentDao

}