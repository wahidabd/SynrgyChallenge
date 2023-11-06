package com.wahidabd.synrgy.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wahidabd.synrgy.R
import com.wahidabd.synrgy.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}