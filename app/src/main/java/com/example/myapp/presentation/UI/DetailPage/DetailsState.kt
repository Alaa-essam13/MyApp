package com.example.myapp.presentation.UI.DetailPage

import com.example.myapp.Domain.models.Recipe

data class DetailsState(
    val isLoading:Boolean = false,
    val recipe: Recipe?=null,
)
