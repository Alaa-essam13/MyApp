package com.example.myapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapp.presentation.UI.DetailPage.RecipeDetailsScreen
import com.example.myapp.presentation.UI.MainPage.HomeScreen
import com.example.myapp.presentation.UI.MainPage.RecipeListViewModel
import com.example.myapp.ui.theme.MyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
//                val vm = hiltViewModel<RecipeListViewModel>()
                val recipeVm = hiltViewModel<RecipeListViewModel>()
                val recipeState = recipeVm.recipeListState.collectAsState().value
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.onPrimaryContainer) {
                    val navController= rememberNavController()
                    NavHost(navController = navController, startDestination = "Home" ){
                        composable("Home") {
                            HomeScreen(navController,recipeState)
                        }
                        composable("RecipeDetails/{recipeId}", arguments = listOf(
                            navArgument("recipeId",){type= NavType.IntType}
                        )) {
                            RecipeDetailsScreen()
                        }
                    }
                }
            }
        }
    }
}
