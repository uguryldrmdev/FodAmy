package com.mobillium.fodamy.data.preferences

import android.content.SharedPreferences

class PreferencesManager(private val preferences: SharedPreferences) {

    var token: String
        get() = preferences.getString(TOKEN_KEY, "") ?: ""
        set(value) = preferences.edit().putString(TOKEN_KEY, value).apply()

    var isAppOpened: Boolean
        get() = preferences.getBoolean(IS_APP_OPENED, false)
        set(value) = preferences.edit().putBoolean(IS_APP_OPENED, value).apply()

    companion object {
        private const val TOKEN_KEY = "@token"
        private const val IS_APP_OPENED = "isAppOpened"
    }
}