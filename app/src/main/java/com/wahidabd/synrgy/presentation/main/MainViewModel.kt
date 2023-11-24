package com.wahidabd.synrgy.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.common.core.Resource
import com.wahidabd.domain.anime.AnimeUseCase
import com.wahidabd.domain.anime.model.Anime
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


/**
 * Created by wahid on 11/6/2023.
 * Github github.com/wahidabd.
 */


class MainViewModel(private val useCase: AnimeUseCase) : ViewModel() {

    private val _topAnimes = MutableLiveData<Resource<List<Anime>>>(Resource.loading())
    val topAnimes: LiveData<Resource<List<Anime>>> get() = _topAnimes

    fun getTopAnime() {
        viewModelScope.launch {
            useCase.getAnime()
                .onEach { _topAnimes.value = it }
                .launchIn(viewModelScope)
        }
    }
}