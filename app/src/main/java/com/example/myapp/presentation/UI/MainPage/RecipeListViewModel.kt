package com.example.myapp.presentation.UI.MainPage

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapp.Domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.example.myapp.util.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
) :ViewModel(){

    private var _recipeListState = MutableStateFlow(RecipeState())

    val recipeListState = _recipeListState.asStateFlow()


    init {
        getRecipies()
    }

    private fun getRecipies() {
       viewModelScope.launch {
           _recipeListState.update {
               it.copy(isLoading = true)
           }

           recipeRepository.getRecipeList().collectLatest { result ->
               when (result) {
                   is Resource.Success -> {
                       result.data?.let {newRecipes->
                           _recipeListState.update {
                               it.copy(
                                   recipes = recipeListState.value.recipes
                                           +newRecipes.shuffled(),
                                   isLoading = false
                               )
                           }
                          Log.d("GGG",recipeListState.value.recipes.toString())
                       }
                   }
                   is Resource.Error -> {
                       _recipeListState.update {
                           it.copy(
                               isLoading = false,
                           )
                       }
                   }
                   is Resource.Loading -> {
                       _recipeListState.update {
                           it.copy(isLoading = result.isLoading)
                       }
                   }
               }
           }
       }
    }

}