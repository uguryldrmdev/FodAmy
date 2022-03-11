package com.mobillium.fodamy.ui.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.mobillium.fodamy.R
import com.mobillium.fodamy.core.base.BaseFragment
import com.mobillium.fodamy.databinding.FragmentHomepageBinding
import com.mobillium.fodamy.databinding.FragmentSplashBinding
import com.mobillium.fodamy.preferences.MyPreferences
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomepageFragment : BaseFragment<ViewModel, FragmentHomepageBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getViewModel(): Class<ViewModel> {
        TODO("Not yet implemented")
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomepageBinding.inflate(inflater,container,false)
}