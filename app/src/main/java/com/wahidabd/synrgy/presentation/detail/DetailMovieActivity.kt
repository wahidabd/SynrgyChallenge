package com.wahidabd.synrgy.presentation.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.wahidabd.synrgy.R
import com.wahidabd.synrgy.databinding.ActivityDetailMovieBinding
import com.wahidabd.synrgy.domain.movie.Genre
import com.wahidabd.synrgy.presentation.home.MovieViewModel
import com.wahidabd.synrgy.presentation.home.adapter.MovieAdapter

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        val GENRE = "genre"
        fun start(context: Context, genre: Genre) {
            context.startActivity(
                Intent(context, DetailMovieActivity::class.java)
                    .putExtra(GENRE, genre)
            )
        }
    }

    private val viewModel: MovieViewModel by viewModels()
    private val movieAdapter by lazy { MovieAdapter() }
    private var genre: Genre? = null

    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initIntent()
        initView()
        initListener()
        initProcess()
        initObservables()
    }

    private fun initIntent() {
        genre = intent.getParcelableExtra(GENRE)
    }

    private fun initProcess() {
        val json = this.resources.openRawResource(R.raw.movies).bufferedReader().use { it.readText() }
        viewModel.getAllMovie(json, genre?.id ?: 0L)
    }

    private fun initObservables() {
        viewModel.movies.observe(this) { movies ->
            movieAdapter.setList(movies)
        }
    }

    private fun initView() = with(binding) {
        toolbar.title = genre?.name
        rvMovie.adapter = movieAdapter
    }

    private fun initListener() {
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        movieAdapter.setOnClickListener { movie ->
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/search?q=${movie.title}")
            )
            startActivity(intent)
        }
    }
}