package com.example.androidfinal.ui.category.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinal.model.Recipe
import com.example.androidfinal.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoryVm : ViewModel() {

    private val _indianRecipes = MutableStateFlow<List<Recipe>>(emptyList())
    val indianRecipes: SharedFlow<List<Recipe>> = _indianRecipes.asStateFlow()

    private val _errorMessage = MutableSharedFlow<String>(replay = 0)
    val errorMessage: SharedFlow<String> = _errorMessage.asSharedFlow()

    fun loadRecipesByTag(tag: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = RetrofitClient.recipeApiService.getRecipesByTag(tag)
                _indianRecipes.value = result.recipes
            } catch (e: Exception) {
                _errorMessage.emit("Error loading recipes: ${e.message}")
            }
        }
    }
}
