package com.example.myapp.presentation.UI.DetailPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.SubcomposeAsyncImage
import com.example.myapp.Domain.models.Recipe

@Composable
fun RecipeDetailsScreen(
) {
    val detailVM = hiltViewModel<DetailsViewModel>()
    val recipe = detailVM.detailsState.collectAsState().value.recipe
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = 15.dp)
    ) {
        item {
            SubcomposeAsyncImage(
                model = recipe?.image,
                contentDescription = recipe?.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                },
                error = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(70.dp),
                            imageVector = Icons.Rounded.ImageNotSupported,
                            contentDescription = recipe?.name,
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Text(
                text = recipe?.name ?:"",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 16.dp)
            ) {
                if (recipe != null) {
                    items(recipe.tags) { tag ->
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .height(35.dp)
                                .padding(end = 8.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(MaterialTheme.colorScheme.secondaryContainer)
                                .padding(horizontal = 12.dp)
                        ) {
                            Text(
                                text = tag,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }
                }
            }
        }

        item {
            Text(
                text = "Preparation Time: ${recipe?.prepTimeMinutes} min",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Cooking Time: ${recipe?.cookTimeMinutes} min",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        item {
            Text(
                text = "Ingredients",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }

        if (recipe != null) {
            items(recipe.ingredients) { ingredient ->
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(12.dp)
                ) {
                    Text(
                        text = ingredient,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        item {
            Text(
                text = "Instructions",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                color = MaterialTheme.colorScheme.primary
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
            ) {
                recipe?.instructions?.forEachIndexed { index, instruction ->
                    Text(
                        text = "${index + 1}. $instruction",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 4.dp),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun RecipeDetails() {
    val recipe = Recipe(
        name = "Classic Margherita Pizza",
        ingredients = listOf(
            "Pizza dough",
            "Tomato sauce",
            "Fresh mozzarella cheese",
            "Fresh basil leaves",
            "Olive oil",
            "Salt and pepper to taste"
        ),
        instructions = listOf(
            "Preheat the oven to 475°F (245°C).",
            "Roll out the pizza dough and spread tomato sauce evenly.",
            "Top with slices of fresh mozzarella and fresh basil leaves.",
            "Drizzle with olive oil and season with salt and pepper.",
            "Bake in the preheated oven for 12-15 minutes or until the crust is golden brown.",
            "Slice and serve hot."
        ),
        image = "https://cdn.dummyjson.com/recipe-images/1.webp",
        cuisine = "Italian",
        difficulty = "Easy",
        mealType = listOf("Dinner"),
        prepTimeMinutes = 20,
        servings = 4,
        rating = 4.6,
        reviewCount = 98,
        tags = listOf(
            "Pizza",
            "Italian"
        ),
        userId = 166,
        caloriesPerServing = 300,
        id = 1,
        cookTimeMinutes = 15
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            SubcomposeAsyncImage(
                model = recipe.image,
                contentDescription = recipe.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                },
                error = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(70.dp),
                            imageVector = Icons.Rounded.ImageNotSupported,
                            contentDescription = recipe.name,
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Text(
                text = recipe.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 16.dp)
            ) {
                items(recipe.tags) { tag ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .height(35.dp)
                            .padding(end = 8.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .padding(horizontal = 12.dp)
                    ) {
                        Text(
                            text = tag,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }
        }

        item {
            Text(
                text = "Preparation Time: ${recipe.prepTimeMinutes} min",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Cooking Time: ${recipe.cookTimeMinutes} min",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        item {
            Text(
                text = "Ingredients",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }

        items(recipe.ingredients) { ingredient ->
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(12.dp)
            ) {
                Text(
                    text = ingredient,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        item {
            Text(
                text = "Instructions",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                color = MaterialTheme.colorScheme.primary
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
            ) {
                recipe.instructions.forEachIndexed { index, instruction ->
                    Text(
                        text = "${index + 1}. $instruction",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 4.dp),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

