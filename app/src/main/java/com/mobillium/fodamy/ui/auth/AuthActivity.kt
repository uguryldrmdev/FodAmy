package com.mobillium.fodamy.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobillium.fodamy.R
import com.mobillium.fodamy.databinding.ActivityAuthBinding
import com.mobillium.fodamy.databinding.ActivityMainBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
    }
    private fun setBinding(){
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}