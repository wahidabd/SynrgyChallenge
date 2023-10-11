package com.wahidabd.synrgy.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wahidabd.synrgy.databinding.ActivityDetailMovieBinding
import com.wahidabd.synrgy.presentation.adapter.MovieAdapter
import com.wahidabd.synrgy.utils.JsonParser

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        val EXTRA_ID = "extra id"
        fun start(context: Context, id: Long) {
            context.startActivity(
                Intent(context, DetailMovieActivity::class.java)
                    .putExtra(EXTRA_ID, id)
            )
        }
    }

    private val movieAdapter by lazy { MovieAdapter() }
    private var id: Long = 0L

    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initIntent()
        initView()
        initListener()
    }
    private fun initIntent() {
        id = intent.getLongExtra(EXTRA_ID, 0L)
    }

    private fun initView() = with(binding) {
        rvMovie.adapter = movieAdapter

        val data = JsonParser.getListMovie(this@DetailMovieActivity, id)
        movieAdapter.setList(data)
    }

    private fun initListener(){
        movieAdapter.setOnClickListener { movie ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=${movie.title}"))
            startActivity(intent)
        }
    }
}