package com.mobillium.fodamy.ui.main.homepage

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mobillium.fodamy.R
import com.mobillium.fodamy.core.base.BaseFragment
import com.mobillium.fodamy.data.preferences.MyPreferences
import com.mobillium.fodamy.databinding.FragmentHomepageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomepageFragment : BaseFragment<FragmentHomepageBinding>(
    FragmentHomepageBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myPreferences = MyPreferences(requireContext())
        myPreferences.token.asLiveData().observe(requireActivity(), Observer {
            if (it == null || it == ""){
                binding.buttonAuth.text = getString(R.string.login_title)
            }
            else{
                binding.buttonAuth.text = getString(R.string.sign_up)
            }
        })

        binding.buttonAuth.setOnClickListener {
            findNavController().navigate(HomepageFragmentDirections.actionHomepageFragmentToLoginFragment())
            lifecycleScope.launch { MyPreferences(requireContext()).clearToken() }
        }

    }

}