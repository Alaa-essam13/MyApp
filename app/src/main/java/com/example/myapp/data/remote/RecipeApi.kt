package com.example.myapp.data.remote


import com.example.myapp.data.remote.respond.RecipesListDto
import retrofit2.http.GET

interface RecipeApi {

    @GET("recipe")
    suspend fun getAllRecipes(): RecipesListDto

    companion object{
        const val BASE_URL = "https://dummyjson.com/"
    }

}