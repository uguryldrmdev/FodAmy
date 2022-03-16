package com.mobillium.fodamy.data.preferences

import android.content.Context
import android.content.SharedPreferences

class MyPreferences (context: Context)
{
    private var APP_PREFS = "FodamyPrefs"

    private val preferences: SharedPreferences = context.getSharedPreferences(APP_PREFS,Context.MODE_PRIVATE)

    var isAppOpened: Boolean
        get() = preferences.getBoolean(IS_APP_OPENED, false)
        set(value) = preferences.edit().putBoolean(IS_APP_OPENED, value).apply()

    var token: String?
        get() = preferences.getString(TOKEN,"")
        set(value) = preferences.edit().putString(TOKEN,value).apply()

    companion object{
        private const val IS_APP_OPENED = "isAppOpened"
        private const val TOKEN = "token"
    }
}