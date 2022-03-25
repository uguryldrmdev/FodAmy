package com.mobillium.fodamy.ui.splash.splash

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.mobillium.fodamy.R
import com.mobillium.fodamy.core.base.BaseFragment
import com.mobillium.fodamy.data.preferences.PreferencesManager
import com.mobillium.fodamy.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment
    : BaseFragment<FragmentSplashBinding,SplashViewModel>(R.layout.fragment_splash) {

    @Inject
    lateinit var preferencesManager:PreferencesManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchApp()
    }

    private fun launchApp(){
        lifecycleScope.launch {
            delay(2000)
            when(preferencesManager.isAppOpened){
                false ->  viewModel.navigate(SplashFragmentDirections.actionSplashFragmentToIntroFragment())
                true -> {
                    viewModel.navigate(SplashFragmentDirections.actionSplashFragmentToHomepageFragment())
                }

            }
            Toast.makeText(requireContext(), preferencesManager.token, Toast.LENGTH_SHORT).show()
        }
    }
}