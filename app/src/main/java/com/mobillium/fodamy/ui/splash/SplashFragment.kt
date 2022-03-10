package com.mobillium.fodamy.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.mobillium.fodamy.databinding.FragmentSplashBinding
import com.mobillium.fodamy.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<ViewModel, FragmentSplashBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchHomepage()
    }

    private fun launchHomepage(){
        lifecycleScope.launch {
            delay(2000)
            Toast.makeText(requireContext(),"Splash Ending", Toast.LENGTH_SHORT).show()
            //findNavController().navigate
        }
    }

    override fun getViewModel(): Class<ViewModel> {
        TODO("Not yet implemented")
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSplashBinding.inflate(inflater,container,false)
}