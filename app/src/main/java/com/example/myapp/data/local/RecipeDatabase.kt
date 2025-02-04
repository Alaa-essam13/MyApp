package com.example.myapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RecipeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RecipeDatabase :RoomDatabase() {
    abstract val recipeDao:RecipeDao
}