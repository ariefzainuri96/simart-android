package com.example.androidtutorial.utils

import androidx.datastore.preferences.core.booleanPreferencesKey

class PreferenceKeys {
    companion object {
        val IS_LOGIN = booleanPreferencesKey("is-login")
    }
}