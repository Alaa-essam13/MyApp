package com.example.myapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeEntity(
    val caloriesPerServing: Int,
    val cookTimeMinutes: Int,
    val cuisine: String,
    val difficulty: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    val ingredients: String,
    val instructions: String,
    val mealType: String,
    val name: String,
    val prepTimeMinutes: Int,
    val rating: Double,
    val reviewCount: Int,
    val servings: Int,
    val tags: String,
    val userId: Int
)
