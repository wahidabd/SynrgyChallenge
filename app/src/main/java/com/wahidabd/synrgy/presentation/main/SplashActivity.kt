package com.wahidabd.synrgy.presentation.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.wahidabd.synrgy.databinding.ActivitySplashBinding
import com.wahidabd.synrgy.presentation.auth.AuthViewModel
import com.wahidabd.synrgy.presentation.auth.LoginActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

@SuppressLint("CustomSplashScreen")
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