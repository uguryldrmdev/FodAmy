package com.mobillium.fodamy.ui.splash.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mobillium.fodamy.core.base.BaseFragment
import com.mobillium.fodamy.core.base.BaseViewModel
import com.mobillium.fodamy.data.preferences.MyPreferences
import com.mobillium.fodamy.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : BaseFragment<BaseViewModel,FragmentSplashBinding>(
    FragmentSplashBinding::inflate
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchApp()
    }

    private fun launchApp(){
        lifecycleScope.launch {
            delay(2000)
            when(MyPreferences(requireContext()).isAppOpened){
                false ->  findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToIntroFragment())
                true -> {
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomepageFragment())
                }
            }
        }
    }

}