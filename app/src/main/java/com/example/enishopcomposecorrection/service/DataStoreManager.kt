package com.example.enishopcomposecorrection.service

import android.content.Context
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

object DataStoreManager {

    //ne peut contenir que des données simples, intPreferencesKey -> int, string, bool, long...
    val BACKGROUND_COLOR_KEY = intPreferencesKey(name = "background_color")
    fun getBackgroundColor(context: Context): Flow<Int> {
        return context.dataStore.data.map { preferences ->
            preferences[BACKGROUND_COLOR_KEY] ?: Color.White.toArgb()
        }
    }

    suspend fun setBackgroundColor(context: Context, color: Int) {
        context.dataStore.edit { preferences ->
            preferences[BACKGROUND_COLOR_KEY] = color
        }
    }

    val DARK_THEME_KEY = booleanPreferencesKey(name = "dark_theme")

    fun isDarkThemeActivited(context: Context): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[DARK_THEME_KEY] ?: false
        }
    }

    suspend fun setDarkThemeActivated(context: Context, isActivated: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_THEME_KEY] = isActivated
        }
    }


}