package com.example.androidfinal.UI.register.vm

import android.app.Application
import android.content.Context
import android.net.http.HttpException
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    val registerSuccess = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String?>()
    val isLoading = MutableLiveData<Boolean>()

    fun register(email: String, password: String) {
        isLoading.value = true

        viewModelScope.launch {
            delay(500)

            val sharedPref = getApplication<Application>()
                .getSharedPreferences("auth", Context.MODE_PRIVATE)

            val existingEmail = sharedPref.getString("registered_email", null)

            if (existingEmail == email) {
                errorMessage.value = "Account with this email already exists"
                registerSuccess.value = false
            } else {
                sharedPref.edit()
                    .putString("registered_email", email)
                    .putString("registered_password", password)
                    .apply()
                registerSuccess.value = true
                errorMessage.value = null
            }

            isLoading.value = false
        }
    }
}