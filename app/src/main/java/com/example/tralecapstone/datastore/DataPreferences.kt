package com.example.tralecapstone.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    private val id = stringPreferencesKey("id")
    private val name = stringPreferencesKey("name")
    private val token = stringPreferencesKey("token")

    fun getSession(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[id] ?: ""
            preferences[name] ?: ""
            preferences[token] ?: ""
        }
    }

    fun getName(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[name] ?: ""
        }
    }

    fun getToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[token] ?: ""
        }
    }

    suspend fun saveSession(idS: String, nameS: String, tokenS: String) {
        dataStore.edit { preferences ->
            preferences[id] = idS
            preferences[name] = nameS
            preferences[token] = tokenS
        }
    }

    suspend fun clearSession() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: DataPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): DataPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = DataPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}