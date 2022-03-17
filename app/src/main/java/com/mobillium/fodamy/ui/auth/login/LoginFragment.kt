package com.mobillium.fodamy.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mobillium.fodamy.core.base.BaseFragment
import com.mobillium.fodamy.databinding.FragmentLoginBinding
import com.mobillium.fodamy.data.network.AuthService
import com.mobillium.fodamy.data.network.Resource
import com.mobillium.fodamy.data.preferences.MyPreferences
import com.mobillium.fodamy.data.repository.AuthRepository
import com.mobillium.fodamy.ui.auth.AuthViewModel
import com.mobillium.fodamy.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment: BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
) {

    private val viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }
    private fun initialize(){
        loginResponse()
        setClicks()
    }

    private fun loginResponse(){
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveToken(
                            it.value.token,
                            requireContext()
                        )
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomepageFragment())
                    }
                }
                is Resource.Failure ->  Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setClicks(){
        binding.buttonLogin.setOnClickListener {
            login()
        }
        binding.textViewHaventAccount.setOnClickListener {
            launchRegister()
        }
        binding.textViewSignUp.setOnClickListener {
            launchRegister()
        }
    }

    private fun login() {
        val email = binding.textInputEmail.text.toString().trim()
        val password = binding.textInputPassword.text.toString().trim()
        viewModel.login(email, password)
    }

    private fun launchRegister(){
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
    }
}
