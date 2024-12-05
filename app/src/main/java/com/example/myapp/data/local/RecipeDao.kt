package com.example.myapp.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface RecipeDao {

    @Upsert
    suspend fun insertRecipe(recipes: List<RecipeEntity>)

    @Query("SELECT * FROM RecipeEntity WHERE id = :id")
    suspend fun getRecipeById(id: Int): RecipeEntity

    @Query("SELECT * FROM RecipeEntity ")
    suspend fun getAllRecipe(): List<RecipeEntity>
}