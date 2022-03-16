package com.mobillium.fodamy.data.repository

import com.mobillium.fodamy.core.base.BaseRepository
import com.mobillium.fodamy.data.network.AuthApi

class AuthRepository(
    private val api: AuthApi
) : BaseRepository() {

    suspend fun login(
        email: String,
        password: String,
    ) = safeApiCall {
        api.login(email,password)
    }

    suspend fun signUp(
        username: String,
        email: String,
        password: String,
    ) = safeApiCall {
        api.signUp(username,email,password)
    }
}