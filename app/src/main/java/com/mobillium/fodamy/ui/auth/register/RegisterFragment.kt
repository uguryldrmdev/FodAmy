package com.mobillium.fodamy.ui.auth.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mobillium.fodamy.core.base.BaseFragment
import com.mobillium.fodamy.data.network.Resource
import com.mobillium.fodamy.databinding.FragmentRegisterBinding
import com.mobillium.fodamy.ui.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RegisterFragment: BaseFragment<FragmentRegisterBinding>(
    FragmentRegisterBinding::inflate
) {

    private val viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }
    private fun initialize(){
        signUpResponse()
        setClicks()
    }

    private fun signUpResponse(){
        viewModel.signUpResponse.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveToken(
                            it.value.token,
                            requireContext()
                        )
                        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToHomepageFragment())
                    }
                }
                is Resource.Failure -> Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setClicks(){
        binding.buttonSignUp.setOnClickListener {
            signUp()
        }
        binding.textViewHaveAccount.setOnClickListener {
            launchLogin()
        }
        binding.textViewLogin.setOnClickListener {
            launchLogin()
        }
    }

    private fun signUp() {
        val username = binding.textInputUsername.text.toString().trim()
        val email = binding.textInputEmail.text.toString().trim()
        val password = binding.textInputPassword.text.toString().trim()
        viewModel.signUp(username,email, password)
    }

    private fun launchLogin(){
        findNavController().popBackStack()
    }
}
