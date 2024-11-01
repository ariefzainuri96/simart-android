package com.example.androidtutorial.utils

import android.content.Context
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

fun Int.dpToPx(displayMetrics: DisplayMetrics): Int = (this * displayMetrics.density).toInt()

fun <T> AppCompatActivity.collectLatestLifeCycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(collect)
        }
    }
}

fun Context.isLoggedIn(): Flow<Boolean?> {
    val isLogin = dataStore.data.map {
        it[PreferenceKeys.IS_LOGIN]
    }

    return isLogin
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "user_preferences"
)