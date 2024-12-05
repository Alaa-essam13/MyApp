package com.example.myapp.presentation.UI.DetailPage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.Domain.repository.RecipeRepository
import com.example.myapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val savedStateHandeler: SavedStateHandle
) :ViewModel() {
    private val recipeId = savedStateHandeler.get<Int>("recipeId")

    private val _detailsState= MutableStateFlow(DetailsState())

    val detailsState = _detailsState.asStateFlow()

    init {
        getRecipeDetails(recipeId ?: -1)
    }

    private fun getRecipeDetails(id: Int) {
        viewModelScope.launch {
            _detailsState.update {
                it.copy(isLoading = true)
            }
            recipeRepository.getRecipe(id).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _detailsState.update {
                            it.copy(
                                isLoading = false,
                            )
                        }
                    }
                    is Resource.Loading ->{
                        _detailsState.update {
                            it.copy(
                                isLoading = result.isLoading
                            )
                        }
                    }
                    is Resource.Success -> {
                        result.data?.let { recipe ->
                            _detailsState.update {
                                it.copy(recipe = recipe)
                            }
                        }
                    }
                }
            }

        }
    }
}