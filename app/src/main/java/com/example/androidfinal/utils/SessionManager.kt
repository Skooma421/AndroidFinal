package com.example.androidfinal.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUser(email: String, password: String) {
        prefs.edit().apply() {
            putString("email", email)
            putString("password", password)
            apply()
        }
    }

    fun getUser(): Pair<String?, String?> {
        val email = prefs.getString("email", null)
        val password = prefs.getString("password", null)
        return Pair(email, password)
    }

    fun setLoginStatus(loggedIn: Boolean) {
        prefs.edit().putBoolean("is_logged_in", loggedIn).apply()
    }

    fun loggedIn(): Boolean {
        return prefs.getBoolean("is_logged_in", false)
    }

    fun logout() {
        prefs.edit().clear().apply()
    }

}