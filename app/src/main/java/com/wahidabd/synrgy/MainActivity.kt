package com.wahidabd.synrgy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wahidabd.synrgy.adapter.PostAdapter
import com.wahidabd.synrgy.adapter.StoryAdapter
import com.wahidabd.synrgy.databinding.ActivityMainBinding
import com.wahidabd.synrgy.utils.DummyData

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}