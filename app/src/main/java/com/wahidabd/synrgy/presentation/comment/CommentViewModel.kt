package com.wahidabd.synrgy.presentation.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.synrgy.domain.comment.CommentUseCase
import com.wahidabd.synrgy.domain.comment.model.Comment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


/**
 * Created by Wahid on 10/27/2023.
 * Github github.com/wahidabd.
 */


class CommentViewModel(private val useCase: CommentUseCase) : ViewModel() {

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>> get() = _comments

    fun getComments(id: Long) {
        useCase.getListById(id)
            .onEach { _comments.value = it }
            .launchIn(viewModelScope)
    }

    fun create(data: Comment){
        viewModelScope.launch(Dispatchers.IO){
            useCase.create(data)
        }
    }

    fun delete(id: Long){
        viewModelScope.launch(Dispatchers.IO){
            useCase.delete(id)
        }
    }
}