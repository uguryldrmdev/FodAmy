package com.mobillium.fodamy.ui.auth

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobillium.fodamy.Fodamy
import com.mobillium.fodamy.core.base.BaseViewModel
import com.mobillium.fodamy.data.network.Resource
import com.mobillium.fodamy.data.repository.AuthRepository
import com.mobillium.fodamy.data.responses.AuthResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
) : BaseViewModel(repository) {

    private val _loginResponse: MutableLiveData<AuthResponse> = MutableLiveData()
    private val _signUpResponse : MutableLiveData<AuthResponse> = MutableLiveData()

    val loginResponse: LiveData<AuthResponse>
        get() = _loginResponse

    val signUpResponse: LiveData<AuthResponse>
        get() = _signUpResponse

    //
    suspend fun saveToken(token: String, context: Context) {
        repository.saveToken(token, context)
    }

    suspend fun login(email: String,password: String){
        when(val response = repository.login(email,password)){
            is Resource.Success->{
                _loginResponse.postValue(response.value!!)
            }
            is Resource.Failure->{
                showError(response.networkError,response.errorCode,response.errorBody)
            }
            is Resource.Loading->{
                //...
            }
        }
    }
    suspend fun signUp(username: String,email: String,password: String){
        when(val response = repository.signUp(username,email,password)){
            is Resource.Success->{
                _signUpResponse.postValue(response.value!!)
            }
            is Resource.Failure->{
                showError(response.networkError,response.errorCode,response.errorBody)
            }
            is Resource.Loading->{
                //...
            }
        }
    }
    suspend fun logout(){
        repository.logout()
    }
}
