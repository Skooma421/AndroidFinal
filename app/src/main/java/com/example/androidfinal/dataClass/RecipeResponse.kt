package com.example.androidfinal.dataClass

import com.squareup.moshi.Json

data class RecipeResponse(
    @Json(name = "recipes") val recipes: List<Recipe>,
    @Json(name = "total") val total: Int,
    @Json(name = "skip") val skip: Int,
    @Json(name = "limit") val limit: Int
)