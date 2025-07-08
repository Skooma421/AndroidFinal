package com.example.androidfinal.UI.login.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidfinal.utils.SessionManager

class LoginViewModel : ViewModel() {

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun logic(email: String, password: String, sessionManager: SessionManager) {
        val (savedEmail, savedPassword) = sessionManager.getUser()

        if (email.isBlank() || password.isBlank()) {
            _errorMessage.value = "Please fill in all fields"
            return
        }
        if (email != savedEmail || password != savedPassword) {
            _errorMessage.value = "Incorrect email or password"
            return
        }

        sessionManager.setLoginStatus(true)
        _loginSuccess.value = true
    }

}