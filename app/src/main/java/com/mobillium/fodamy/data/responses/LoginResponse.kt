package com.mobillium.fodamy.data.responses

import android.content.Context
import com.mobillium.fodamy.data.preferences.MyPreferences

data class LoginResponse(
    val token: String,
    val user: User
)