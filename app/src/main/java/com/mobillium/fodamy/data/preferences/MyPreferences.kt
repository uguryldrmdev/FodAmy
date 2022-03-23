package com.mobillium.fodamy.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "fodamy_data_store")

class MyPreferences @Inject constructor(@ApplicationContext context: Context) {

    private var APP_PREFS = "FodamyPrefs"

    private val preferences: SharedPreferences =
        context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)

    var isAppOpened: Boolean
        get() = preferences.getBoolean("isAppOpened", false)
        set(value) = preferences.edit().putBoolean("isAppOpened", value).apply()

    private val appContext = context.applicationContext

//    val token: Flow<String?>
//        get() = appContext.dataStore.data.map { preferences ->
//            preferences[TOKEN]
//        }

    suspend fun saveToken(token: String) {
        appContext.dataStore.edit { preferences ->
            preferences[TOKEN] = token
        }
    }

    suspend fun clearToken() {
        appContext.dataStore.edit { preferences ->
            preferences[TOKEN] = ""
        }
    }

    companion object {
        private val TOKEN = stringPreferencesKey("key_token")
        private val IS_APP_OPENED = booleanPreferencesKey("is_app_opened_key")
        private const val TOKEN_KEY = "@token"
    }


    var token: String
        get() = preferences.getString(TOKEN_KEY, "") ?: ""
        set(value) = preferences.edit().putString(TOKEN_KEY, value).apply()


}

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