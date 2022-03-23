package com.mobillium.fodamy.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mobillium.fodamy.core.base.BaseViewModel
import com.mobillium.fodamy.data.network.Resource
import com.mobillium.fodamy.data.repository.AuthRepository
import com.mobillium.fodamy.data.responses.AuthResponse
import com.mobillium.fodamy.ui.auth.login.LoginFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : BaseViewModel(repository) {

    val email = MutableLiveData("")
    val password = MutableLiveData("")

    fun isValid(): Boolean {
        return true
    }

    fun newLogin() =
        viewModelScope.launch {
            if (isValid()) {
                email.value?.let { emailValue ->
                    password.value?.let { passwordValue ->
                        when (val response = repository.login(emailValue, passwordValue)) {
                            is Resource.Success -> {
                                navigate(LoginFragmentDirections.actionLoginFragmentToHomepageFragment())
                            }
                            is Resource.Failure -> {
                                showError(response.errorBody ?: "ERROR")
                            }
                            Resource.Loading -> {
                                showLoading()
                            }
                        }
                    }
                }
            }
        }

    fun goRegister() {
        navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
    }


    private val _loginResponse: MutableLiveData<Resource<AuthResponse>> = MutableLiveData()
    private val _signUpResponse: MutableLiveData<Resource<AuthResponse>> = MutableLiveData()

    val loginResponse: LiveData<Resource<AuthResponse>>
        get() = _loginResponse
    val signUpResponse: LiveData<Resource<AuthResponse>>
        get() = _signUpResponse

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = repository.login(email, password)
    }

    fun signUp(
        username: String,
        email: String,
        password: String
    ) = viewModelScope.launch {
        _signUpResponse.value = Resource.Loading
        _signUpResponse.value = repository.signUp(username, email, password)
    }

}
