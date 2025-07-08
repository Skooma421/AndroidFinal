package com.example.androidfinal.UI.categories.spicy.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinal.dataClass.Recipe
import com.example.androidfinal.network.RetrofitClient
import kotlinx.coroutines.launch

class SpicyViewModel : ViewModel() {
    private val _spicyRecipes = MutableLiveData<List<Recipe>>()
    val spicyRecipes: LiveData<List<Recipe>> = _spicyRecipes

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadSpicyRecipes() {
        viewModelScope.launch {
            try {
                val all = RetrofitClient.recipeApiService.getRecipes().recipes
                val spicyOnly = all.filter { recipe ->
                    recipe.tags.any { it.equals("Spicy", ignoreCase = true) } ||
                            recipe.name.contains("spicy", ignoreCase = true) ||
                            recipe.ingredients.any { it.contains("chili", true) }
                }
                _spicyRecipes.value = spicyOnly
            } catch (e: Exception) {
                _error.value = "Error loading recipes: ${e.message}"
            }
        }
    }
}
