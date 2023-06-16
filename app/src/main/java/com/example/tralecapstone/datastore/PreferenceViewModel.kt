package com.example.tralecapstone.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PreferenceViewModel(private val pref: DataPreferences) : ViewModel() {
    fun getSession(): LiveData<String> {
        return pref.getSession().asLiveData()
    }

    fun getId(): LiveData<Int> {
        return pref.getId().asLiveData()
    }
    fun getName(): LiveData<String> {
        return pref.getName().asLiveData()
    }
    fun getAddress(): LiveData<String> {
        return pref.getAddress().asLiveData()
    }
    fun getEmail(): LiveData<String> {
        return pref.getEmail().asLiveData()
    }
    fun getTelp(): LiveData<String> {
        return pref.getTelp().asLiveData()
    }
    fun getBio(): LiveData<String> {
        return pref.getBio().asLiveData()
    }

    fun getToken(): LiveData<String> {
        return pref.getToken().asLiveData()
    }

    fun getPassword(): LiveData<String> {
        return pref.getPassword().asLiveData()
    }

    fun saveSession(id: Int, name:String, address:String, email:String, telp:String, bio:String, token:String, password:String) {
        viewModelScope.launch {
            Log.d("cek prefvm", "$id $name $token" )
            pref.saveSession(ids = id, names = name, tokens = token, addresses = address, emails = email, telps = telp, bios = bio, passwords=password)
        }
    }

    fun clearSession(){
        viewModelScope.launch {
            pref.clearSession()
        }
    }
}