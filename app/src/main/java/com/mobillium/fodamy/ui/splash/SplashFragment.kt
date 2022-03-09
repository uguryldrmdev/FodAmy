package com.mobillium.fodamy.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.mobillium.fodamy.R
import com.mobillium.fodamy.databinding.FragmentSplashBinding
import com.mobillium.fodamy.ui.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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