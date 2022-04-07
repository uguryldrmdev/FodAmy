package com.mobillium.fodamy.ui

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mobillium.fodamy.R
import com.mobillium.fodamy.databinding.ActivityMainBinding
import com.mobillium.fodamy.ui.intro.splash.SplashFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setBottomNav()
    }


    private fun setBottomNav(){
        val navController = findNavController(R.id.fragment_container_view)
        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.splashFragment,
                R.id.introFragment,
                R.id.loginFragment,
                R.id.registerFragment -> {
                    binding.bottomNavigationView.visibility = GONE
                }
                else ->{
                    binding.bottomNavigationView.visibility = VISIBLE
                }
            }
        }


    }
    private fun setBinding(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}