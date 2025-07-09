package com.example.androidfinal.ui.categories.indian.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinal.model.Recipe
import com.example.androidfinal.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class IndianViewModel : ViewModel() {
    private val _indianRecipes = MutableStateFlow<List<Recipe>>(emptyList())
    val indianRecipes: SharedFlow<List<Recipe>> = _indianRecipes.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: SharedFlow<String> = _errorMessage.asStateFlow()

    init {
        loadRecipesByTag()
    }

    private fun loadRecipesByTag() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = RetrofitClient.recipeApiService.getRecipesByTag("Indian")
                _indianRecipes.value = result.recipes
            } catch (e: Exception) {
                _errorMessage.value = "Error loading recipes: ${e.message}"
            }
        }
    }
}
