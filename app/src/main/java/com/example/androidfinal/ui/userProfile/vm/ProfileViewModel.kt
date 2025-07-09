package com.example.androidfinal.ui.userProfile.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidfinal.utils.SessionManager

class ProfileViewModel(private val sessionManager: SessionManager) : ViewModel() {

    private val _email = MutableLiveData<String?>()
    val email: LiveData<String?> get() = _email

    private val _loggedOut = MutableLiveData<Boolean>()
    val loggedOut: LiveData<Boolean> get() = _loggedOut

    fun loadUserMail() {
        _email.value = sessionManager.getEmail()
    }

    fun logout() {
        sessionManager.logout()
        _loggedOut.value = true
    }
}