package com.wahidabd.synrgy.di

import com.wahidabd.synrgy.data.AppDatabase
import com.wahidabd.synrgy.data.local.CommentDataSource
import com.wahidabd.synrgy.data.local.CommentRepository
import com.wahidabd.synrgy.domain.comment.CommentInteractor
import com.wahidabd.synrgy.domain.comment.CommentUseCase
import com.wahidabd.synrgy.presentation.comment.CommentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
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

    single<CommentRepository> { CommentDataSource(get()) }
    single<CommentUseCase> { CommentInteractor(get()) }
    viewModel { CommentViewModel(get()) }
}