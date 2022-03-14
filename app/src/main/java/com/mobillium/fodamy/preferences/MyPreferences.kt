package com.mobillium.fodamy.preferences

import android.content.Context
import android.content.SharedPreferences

class MyPreferences (context: Context)
{
    private var APP_PREFS = "FodamyPrefs"

    private val preferences: SharedPreferences = context.getSharedPreferences(APP_PREFS,Context.MODE_PRIVATE)

    var isAppOpened: Boolean
        get() = preferences.getBoolean("isAppOpened", false)
        set(value) = preferences.edit().putBoolean("isAppOpened", value).apply()
}