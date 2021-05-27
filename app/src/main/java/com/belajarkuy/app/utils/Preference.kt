package com.belajarkuy.app.utils

import android.content.Context
import androidx.preference.PreferenceManager

object Preference {

    fun getToken(context: Context): String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString("token", "")
    }

    fun saveToken(context: Context, token: String): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val prefsEditor = prefs.edit()
        prefsEditor.putString("token", token)
        prefsEditor.apply()
        return true
    }
}