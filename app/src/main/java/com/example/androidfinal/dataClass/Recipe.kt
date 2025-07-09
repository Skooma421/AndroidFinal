package com.example.androidfinal.dataClass

data class Recipe(
    val id: Int? = 1,
    val name: String? = "1",
    val ingredients: List<String>? = listOf("coconut"),
    val instructions: List<String>? = listOf("wyali"),
    val prepTimeMinutes: Int? = 1,
    val cookTimeMinutes: Int? = 1,
    val servings: Int? = 1,
    val difficulty: String? = "easy",
    val cuisine: String? = "indian",
    val caloriesPerServing: Int? = 1,
    val tags: List<String>? = listOf("indian"),
    val userId: Int? = 1,
    val image: String? = "123123",
    val rating: Double? = 1.5,
    val reviewCount: Int? = 12,
    val mealType: List<String>? = listOf("meat")
)