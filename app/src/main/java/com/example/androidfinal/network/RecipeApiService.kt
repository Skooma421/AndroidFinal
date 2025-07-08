package com.example.androidfinal.network

import com.example.androidfinal.dataClass.RecipeResponse
import retrofit2.http.GET

interface RecipeApiService {
    @GET("recipes")
    suspend fun getRecipes(): RecipeResponse
}