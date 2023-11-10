package com.wahidabd.synrgy.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.wahidabd.synrgy.common.Resource
import com.wahidabd.synrgy.databinding.ActivityMainBinding
import com.wahidabd.synrgy.presentation.auth.AuthViewModel
import com.wahidabd.synrgy.presentation.auth.LoginActivity
import com.wahidabd.synrgy.utils.toast
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }


    private val animeAdapter by lazy { AnimeAdapter() }

    private val authViewModel: AuthViewModel by inject()
    private val viewModel: MainViewModel by inject()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        viewModel.getTopAnime()

        initListener()
        observers()
    }


    private fun initListener() = with(binding) {
        imgLogout.setOnClickListener {
            authViewModel.setLogin(false)
            LoginActivity.start(this@MainActivity)
            finish()
        }
    }



    private fun observers() {
        viewModel.topAnimes.observe(this) {
            when (it) {
                is Resource.Loading -> Log.d("Main", "Loading")
                is Resource.Error -> toast(it.message)
                is Resource.Success -> animeAdapter.setData(it.data)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvAnime.apply {
            adapter = animeAdapter
        }
    }


}