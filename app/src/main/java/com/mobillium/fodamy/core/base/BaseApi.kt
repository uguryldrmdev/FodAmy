package com.mobillium.fodamy.core.base

import okhttp3.ResponseBody
import retrofit2.http.POST

interface BaseApi {
    @POST("api/auth/logout")
    suspend fun logout(): ResponseBody
}