package com.example.myapp.presentation.UI.MainPage

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapp.Domain.models.Recipe

@Composable
fun HomeScreen(navController: NavHostController, recipeState: RecipeState) {
    if (recipeState.recipes.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Scaffold {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(MaterialTheme.colorScheme.scrim)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(250.dp),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
                ) {
                    items(recipeState.recipes.size) { index ->
                        RecipeItem(recipe = recipeState.recipes[index], onClick = {
                            navController.navigate("RecipeDetails/${recipeState.recipes[index].id}")
                        })
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}


