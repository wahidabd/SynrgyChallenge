package com.wahidabd.synrgy.presentation.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.wahidabd.common.core.Resource
import com.wahidabd.common.utils.requestPermission
import com.wahidabd.synrgy.databinding.ActivityMainBinding
import com.wahidabd.synrgy.presentation.auth.AuthViewModel
import com.wahidabd.synrgy.presentation.auth.LoginActivity
import com.wahidabd.common.utils.toast
import com.wahidabd.synrgy.presentation.user.UserActivity
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_PERMISSION_CODE = 1122

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) checkPermission()

        setupRecyclerView()
        initListener()

        viewModel.getTopAnime()
        observers()
    }


    private fun initListener() = with(binding) {
        imgUser.setOnClickListener {
            UserActivity.start(this@MainActivity)
        }
    }

    private fun observers() = with(binding) {
        viewModel.topAnimes.observe(this@MainActivity) { response ->
            when (response) {
                is Resource.Loading -> {
                    progress.visibility = View.VISIBLE
                    rvAnime.visibility = View.GONE
                }
                is Resource.Error -> {
                    progress.visibility = View.GONE
                    toast(response.message)
                }
                is Resource.Success -> {
                    rvAnime.visibility = View.VISIBLE
                    progress.visibility = View.GONE
                    animeAdapter.setData(response.data)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvAnime.apply {
            adapter = animeAdapter
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun checkPermission(){
        requestPermission(
            permissions = arrayOf(Manifest.permission.POST_NOTIFICATIONS),
            requestCode = REQUEST_PERMISSION_CODE,
            doIfGranted = {},
        )
    }
}