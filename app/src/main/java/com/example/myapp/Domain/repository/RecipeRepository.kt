package com.example.myapp.Domain.repository

import com.example.myapp.Domain.models.Recipe
import com.example.myapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun getRecipeList(): Flow<Resource<List<Recipe>>>

    suspend fun getRecipe(recipeId: Int): Flow<Resource<Recipe>>

}