package com.mobillium.fodamy.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "fodamy_data_store")
class MyPreferences  @Inject constructor(@ApplicationContext context: Context) {


    private var APP_PREFS = "FodamyPrefs"
    private val appContext = context.applicationContext
    private val preferences: SharedPreferences = context.getSharedPreferences(APP_PREFS,Context.MODE_PRIVATE)

    var isAppOpened: Boolean
        get() = preferences.getBoolean(IS_APP_OPENED, false)
        set(value) = preferences.edit().putBoolean(IS_APP_OPENED, value).apply()

    val token: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[TOKEN]
        }

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
        private const val IS_APP_OPENED = "is_app_opened_key"
    }

}