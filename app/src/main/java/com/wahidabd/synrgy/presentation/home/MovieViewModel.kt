package com.wahidabd.synrgy.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.synrgy.domain.movie.Genre
import com.wahidabd.synrgy.domain.movie.Movie
import com.wahidabd.synrgy.utils.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * Created by Wahid on 10/18/2023.
 * Github github.com/wahidabd.
 */


class MovieViewModel : ViewModel() {

    private val _genres = MutableLiveData<List<Genre>>()
    val genres: LiveData<List<Genre>> get() = _genres

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    fun getAllMovie(json: String, id: Long){
        viewModelScope.launch(Dispatchers.Main){
            _movies.value = JsonParser.getListMovie(json, id)
        }
    }

    fun getAllGenre(json: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _genres.value = JsonParser.getAllGenres(json)
        }
    }

}