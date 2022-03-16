package com.mobillium.fodamy.ui.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mobillium.fodamy.R
import com.mobillium.fodamy.core.base.BaseFragment
import com.mobillium.fodamy.data.network.AuthApi
import com.mobillium.fodamy.data.network.Resource
import com.mobillium.fodamy.data.preferences.MyPreferences
import com.mobillium.fodamy.data.repository.AuthRepository
import com.mobillium.fodamy.databinding.FragmentRegisterBinding
import com.mobillium.fodamy.ui.auth.AuthViewModel
import com.mobillium.fodamy.ui.main.MainActivity
import com.mobillium.fodamy.ui.startNewActivity


class RegisterFragment: BaseFragment<AuthViewModel, FragmentRegisterBinding, AuthRepository>() {
    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentRegisterBinding.inflate(inflater,container,false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize(){
        setClicks()
    }

    private fun setClicks(){
        binding.buttonLogin.setOnClickListener {
            val email = binding.textInputEmail.text.toString().trim()
            val password = binding.textInputPassword.text.toString().trim()
            //todo add validations
            viewModel.login(email,password)
        }
        binding.textViewHaveAccount.setOnClickListener {
            // launchLogin()
        }
        binding.textViewLogin.setOnClickListener {
           // launchLogin()
        }
    }
    
}
