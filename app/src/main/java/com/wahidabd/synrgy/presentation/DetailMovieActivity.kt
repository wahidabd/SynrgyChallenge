package com.wahidabd.synrgy.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.wahidabd.synrgy.databinding.ActivityDetailMovieBinding
import com.wahidabd.synrgy.domain.Genre
import com.wahidabd.synrgy.presentation.adapter.MovieAdapter
import com.wahidabd.synrgy.utils.JsonParser

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
        Log.d("Activity", "This is a activity")
    }

    private fun initIntent() {
        genre = intent.getParcelableExtra(GENRE)
    }

    private fun initView() = with(binding) {
        toolbar.title = genre?.name
        rvMovie.adapter = movieAdapter

        val data = JsonParser.getListMovie(this@DetailMovieActivity, genre?.id ?: 0)
        movieAdapter.setList(data)
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