package com.wahidabd.synrgy.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.lifecycleScope
import com.wahidabd.synrgy.R
import com.wahidabd.synrgy.databinding.ActivitySplashBinding
import com.wahidabd.synrgy.presentation.auth.AuthViewModel
import com.wahidabd.synrgy.presentation.auth.LoginActivity
import com.wahidabd.synrgy.utils.AuthDataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {


    private val viewModel: AuthViewModel by inject()
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            handleObserver()
        }
    }

    private suspend fun handleObserver(){
        viewModel.isLogin.collect { login ->
            delay(2000)
            if(login) {
                MainActivity.start(this)
                finish()
            } else {
                LoginActivity.start(this)
                finish()
            }
        }
    }
}