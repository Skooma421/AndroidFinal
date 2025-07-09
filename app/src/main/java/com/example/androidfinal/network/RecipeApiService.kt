package com.example.androidfinal.network

import com.example.androidfinal.model.Recipe
import com.example.androidfinal.model.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeApiService {

    @GET("recipes")
    suspend fun getRecipes(): RecipeResponse

    @GET("recipes/tags")
    suspend fun getRecipeTags(): List<String>

    @GET("recipes/tag/{tag}")
    suspend fun getRecipesByTag(@Path("tag") tag: String): RecipeResponse

    @GET("recipes/{id}")
    suspend fun getRecipesById(@Path("id") id: Int): Recipe
}