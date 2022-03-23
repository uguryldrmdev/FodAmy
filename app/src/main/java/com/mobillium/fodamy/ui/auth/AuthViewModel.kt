package com.mobillium.fodamy.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mobillium.fodamy.core.base.BaseViewModel
import com.mobillium.fodamy.data.network.Resource
import com.mobillium.fodamy.data.repository.AuthRepository
import com.mobillium.fodamy.ui.auth.login.LoginFragmentDirections
import com.mobillium.fodamy.ui.auth.register.RegisterFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : BaseViewModel() {

    val username = MutableLiveData("")
    val email = MutableLiveData("")
    val password = MutableLiveData("")


    fun isValid(): Boolean {
        //...
        return true
    }

    fun login() =
        viewModelScope.launch {
            if (isValid()) {
                email.value?.let { emailValue ->
                    password.value?.let { passwordValue ->
                        when (val response = repository.login(emailValue, passwordValue)) {
                            is Resource.Success -> {
                                navigate(LoginFragmentDirections.actionLoginFragmentToHomepageFragment())
                            }
                            is Resource.Failure -> {
                                response.errorBody?.let { showError(it) }
                            }
                            is Resource.Loading -> {
                                showLoading()
                            }
                        }
                    }
                }
            }
        }

    fun signUp() =
        viewModelScope.launch {
            if (isValid()) {
                username.value?.let { usernameValue ->
                    email.value?.let { emailValue ->
                        password.value?.let { passwordValue ->
                            when (val response = repository.signUp(usernameValue,emailValue, passwordValue)) {
                                is Resource.Success -> {
                                    navigate(LoginFragmentDirections.actionLoginFragmentToHomepageFragment())
                                }
                                is Resource.Failure -> {
                                    response.errorBody?.let { showError(it) }
                                }
                                is Resource.Loading -> {
                                    showLoading()
                                }
                            }
                        }
                    }
                }
            }
        }

    fun goRegister() {
        navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
    }

    fun goLogin() {
        navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
    }


}
