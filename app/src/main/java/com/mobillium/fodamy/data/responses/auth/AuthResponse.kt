package com.mobillium.fodamy.data.responses.auth


data class AuthResponse(
    val token: String,
    val user: User
)