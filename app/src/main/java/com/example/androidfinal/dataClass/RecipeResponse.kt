package com.example.androidfinal.dataClass

data class RecipeResponse(
    val recipes: List<Recipe>,
    val total: Int,
    val skip: Int,
    val limit: Int
)