package com.mobillium.fodamy.data.network

import com.mobillium.fodamy.data.responses.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("api/auth/login")
    suspend fun login(
        @Field("username") email:String,
        @Field("password") password:String
    ):LoginResponse
}