package com.example.androidfinal.model

data class RecipeResponse(
    val recipes: List<Recipe>,
    val total: Int,
    val skip: Int,
    val limit: Int
)