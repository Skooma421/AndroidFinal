package com.example.androidfinal.ui.userProfile.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidfinal.ui.userProfile.vm.ProfileViewModel
import com.example.androidfinal.utils.SessionManager

class ProfileViewModelFactory(private val sessionManager: SessionManager) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(sessionManager) as T
    }
}