package com.hirocode.movieapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hirocode.movieapp.settings.SettingPreferences
import com.hirocode.movieapp.settings.SettingViewModel

class ViewModelFactory(private val pref: SettingPreferences) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            return SettingViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}