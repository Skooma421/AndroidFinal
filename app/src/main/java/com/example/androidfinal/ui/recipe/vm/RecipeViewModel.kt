package com.example.androidfinal.ui.recipe.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinal.model.Recipe
import com.example.androidfinal.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {

    private val _recipe = MutableStateFlow<Recipe?>(null)
    val recipe: StateFlow<Recipe?> = _recipe.asStateFlow()

    private val _errorMessage = MutableSharedFlow<String>(replay = 0)
    val errorMessage: SharedFlow<String> = _errorMessage.asSharedFlow()

    fun loadRecipesByTag(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = RetrofitClient.recipeApiService.getRecipesById(id)
                _recipe.value = result
            } catch (e: Exception) {
                _errorMessage.emit("Error loading recipe: ${e.message}")
            }
        }
    }
}