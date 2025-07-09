package com.example.androidfinal.ui.home.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinal.model.Recipe
import com.example.androidfinal.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _recipeList = MutableStateFlow<List<Recipe>>(emptyList())
    val recipeList: SharedFlow<List<Recipe>> = _recipeList.asStateFlow()

    private val _tagList = MutableStateFlow<List<String>>(emptyList())
    val tagList: SharedFlow<List<String>> = _tagList.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: SharedFlow<String> = _errorMessage.asStateFlow()

    init {
        loadAllRecipes()
        loadRecipeTags()
    }

    private fun loadAllRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = RetrofitClient.recipeApiService.getRecipes()
                _recipeList.value = result.recipes
            } catch (e: Exception) {
                _errorMessage.value = "Error loading recipe tags: ${e.message}"
            }
        }
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