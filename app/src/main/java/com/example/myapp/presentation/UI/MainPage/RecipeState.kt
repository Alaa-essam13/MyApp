package com.example.myapp.presentation.UI.MainPage

import com.example.myapp.Domain.models.Recipe

data class RecipeState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
)
