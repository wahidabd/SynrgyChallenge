package com.wahidabd.di.features

import com.wahidabd.data.local.AppDatabase
import com.wahidabd.data.local.CommentDataSource
import com.wahidabd.data.local.CommentRepository
import com.wahidabd.domain.comment.CommentInteractor
import com.wahidabd.domain.comment.CommentUseCase
import org.koin.dsl.module


/**
 * Created by Wahid on 10/27/2023.
 * Github github.com/wahidabd.
 */


val commentModule = module {
    single {
        val db: AppDatabase = get()
        return@single db.commentDao()
    }

    single<CommentRepository> {
        CommentDataSource(
            get()
        )
    }
    single<CommentUseCase> { CommentInteractor(get()) }
}