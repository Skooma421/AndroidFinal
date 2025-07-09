package com.example.androidfinal.ui.register.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.IOException

class RegisterViewModel : ViewModel() {

    private val _registerSuccess = MutableLiveData<Boolean>()
    val registerSuccess: LiveData<Boolean> get() = _registerSuccess

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun register(email: String, password: String, saveUser: (String, String) -> Unit) {
        viewModelScope.launch {
            try {
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    _errorMessage.value = "Invalid email format"
                    return@launch
                }
                saveUser(email, password)
                _registerSuccess.value = true
            } catch (e: IOException) {
                _errorMessage.value = "Network error: ${e.message}"
            } catch (e: Exception) {
                _errorMessage.value = "Registration failed: ${e.message}"
            }
        }


    }
}