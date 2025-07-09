package com.example.androidfinal.network

import com.example.androidfinal.dataClass.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeApiService {

    @GET("recipes")
    suspend fun getRecipes(): RecipeResponse

    @GET("recipes/tag/{tag}")
    suspend fun getRecipesByTag(@Path("tag") tag: String): RecipeResponse
}