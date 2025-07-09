package com.example.androidfinal.ui.home.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinal.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _tagList = MutableStateFlow<List<String>>(emptyList())
    val tagList: SharedFlow<List<String>> = _tagList.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: SharedFlow<String> = _errorMessage.asStateFlow()

    init {
        loadRecipeTags()
    }

    private fun loadRecipeTags() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = RetrofitClient.recipeApiService.getRecipeTags()
                _tagList.value = result
            } catch (e: Exception) {
                _errorMessage.value = "Error loading recipe tags: ${e.message}"
            }
        }
    }
}