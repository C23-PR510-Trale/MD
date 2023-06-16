package com.example.tralecapstone.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.tralecapstone.model.response.LoginResponse
import com.example.tralecapstone.model.response.User
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataPreferences constructor(private val dataStore: DataStore<Preferences>){
    private val id = intPreferencesKey("id")
    private val name = stringPreferencesKey("name")
    private val email = stringPreferencesKey("email")
    private val address = stringPreferencesKey("address")
    private val telp = stringPreferencesKey("telp")
    private val bio = stringPreferencesKey("bio")
    private val token = stringPreferencesKey("token")
    private val password = stringPreferencesKey("password")

    fun getSession(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[id] ?: ""
            preferences[name] ?: ""
            preferences[email] ?: ""
            preferences[address] ?: ""
            preferences[telp] ?: ""
            preferences[bio] ?: ""
            preferences[token] ?: ""
            preferences[password] ?: ""
        }
    }

    fun getId(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[id] ?: 0
        }
    }
    fun getName(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[name] ?: ""
        }
    }
    fun getEmail(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[email] ?: ""
        }
    }
    fun getAddress(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[address] ?: ""
        }
    }
    fun getTelp(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[telp] ?: ""
        }
    }
    fun getBio(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[bio] ?: ""
        }
    }
    fun getToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[token] ?: ""
        }
    }

    fun getPassword(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[password] ?: ""
        }
    }

    suspend fun saveSession(ids :Int, names :String, emails :String, addresses :String, telps :String, bios :String, tokens: String, passwords: String) {
        dataStore.edit { preferences ->
            preferences[id] = ids
            preferences[name] = names
            preferences[email] = emails
            preferences[address] = addresses
            preferences[telp] = telps
            preferences[bio] = bios
            preferences[token] = tokens
            preferences[password] = passwords
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