package com.mobillium.fodamy.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobillium.fodamy.data.network.Resource
import com.mobillium.fodamy.data.repository.AuthRepository
import com.mobillium.fodamy.data.responses.AuthResponse
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
): ViewModel() {

    private val _loginResponse : MutableLiveData<Resource<AuthResponse>> = MutableLiveData()
    private val _signUpResponse : MutableLiveData<Resource<AuthResponse>> = MutableLiveData()

    val loginResponse: LiveData<Resource<AuthResponse>>
        get() = _loginResponse

    val signUpResponse: LiveData<Resource<AuthResponse>>
        get() = _signUpResponse

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = repository.login(email,password)
    }

    fun signUp(
        username: String,
        email: String,
        password: String
    ) = viewModelScope.launch{
        _signUpResponse.value = repository.signUp(username,email,password)
    }
}