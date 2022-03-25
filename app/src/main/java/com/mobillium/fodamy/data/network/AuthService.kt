package com.mobillium.fodamy.data.network

import com.mobillium.fodamy.data.responses.AuthResponse
import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService{

    @FormUrlEncoded
    @POST("api/auth/login")
    suspend fun login(
        @Field("username") email:String,
        @Field("password") password:String
    ):AuthResponse

    @FormUrlEncoded
    @POST("api/auth/register")
    suspend fun signUp(
        @Field("username") username:String,
        @Field("email") email:String,
        @Field("password") password:String
    ):AuthResponse


    @POST("api/auth/logout")
    suspend fun logout(): ResponseBody

}