package com.example.myapp.data.remote.respond

data class RecipesListDto(
    val limit: Int,
    val recipes: List<RecipeDto>,
    val skip: Int,
    val total: Int
)