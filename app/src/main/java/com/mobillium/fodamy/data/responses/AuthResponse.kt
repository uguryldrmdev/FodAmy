package com.mobillium.fodamy.data.responses


data class AuthResponse(
    val token: String,
    val user: User
)