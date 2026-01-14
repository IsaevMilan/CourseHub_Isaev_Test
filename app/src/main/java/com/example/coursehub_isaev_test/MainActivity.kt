package com.example.coursehub_isaev_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coursehub_isaev_test.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализируем ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Здесь позже настроим Navigation (BottomBar)
    }
}