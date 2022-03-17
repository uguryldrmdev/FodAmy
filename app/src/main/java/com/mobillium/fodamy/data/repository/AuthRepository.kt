package com.mobillium.fodamy.data.repository

import android.content.Context
import com.mobillium.fodamy.core.base.BaseRepository
import com.mobillium.fodamy.data.network.AuthService
import com.mobillium.fodamy.data.preferences.MyPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val service: AuthService,
) : BaseRepository(service) {

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        service.login(email, password)
    }
    suspend fun signUp(
        username: String,
        email: String,
        password: String,
    ) = safeApiCall {
        service.signUp(username,email,password)
    }
    suspend fun saveToken(token: String,context: Context) {
       MyPreferences(context).saveToken(token)
    }

}
