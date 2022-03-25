package com.mobillium.fodamy.data.repository

import com.mobillium.fodamy.core.base.BaseRepository
import com.mobillium.fodamy.data.network.AuthService
import com.mobillium.fodamy.data.preferences.PreferencesManager
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val service: AuthService,
    private val preferencesManager: PreferencesManager
) : BaseRepository() {

    suspend fun login(
        email: String,
        password: String
    ) = execute {
        val response = service.login(email, password)
        preferencesManager.token = response.token
        response
    }

    suspend fun signUp(
        username: String,
        email: String,
        password: String,
    ) = execute {
        val response = service.signUp(username, email, password)
        preferencesManager.token = response.token
        response
    }

    suspend fun logout() =
        execute {
            val response = service.logout()
            preferencesManager.token = ""
            response
        }

    fun isUserLogin(): Boolean{
        return when(preferencesManager.token){
            "" -> false
            else -> true
        }
    }


}
