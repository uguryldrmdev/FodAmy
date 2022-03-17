package com.mobillium.fodamy.ui.auth

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobillium.fodamy.core.base.BaseViewModel
import com.mobillium.fodamy.data.network.Resource
import com.mobillium.fodamy.data.repository.AuthRepository
import com.mobillium.fodamy.data.responses.AuthResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : BaseViewModel(repository) {

    private val _loginResponse: MutableLiveData<Resource<AuthResponse>> = MutableLiveData()
    private val _signUpResponse : MutableLiveData<Resource<AuthResponse>> = MutableLiveData()

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
    ) = viewModelScope.launch{
        _signUpResponse.value = Resource.Loading
        _signUpResponse.value = repository.signUp(username,email,password)
    }

    suspend fun saveToken(token: String,@ApplicationContext context: Context) {
        repository.saveToken(token,context)
    }
}
