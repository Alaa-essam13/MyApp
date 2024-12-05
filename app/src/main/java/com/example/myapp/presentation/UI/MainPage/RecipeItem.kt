package com.example.myapp.presentation.UI.MainPage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.example.myapp.Domain.models.Recipe
import com.example.myapp.util.RatingBar

@Composable
fun RecipeItem(recipe: Recipe, onClick: () -> Unit) {
    Column(
        Modifier
            .padding(10.dp)
            .fillMaxWidth(.5f)
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .padding(15.dp)
            .clickable { onClick() }
    ) {
        SubcomposeAsyncImage(
            model = recipe.image,
            contentDescription = recipe.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(
                    RoundedCornerShape(15.dp)
                ),
            contentScale = ContentScale.Crop,
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.Gray)
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
                        contentDescription = recipe.name
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = recipe.name, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onPrimary)
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RatingBar(
                rating = recipe.rating,
                modifier = Modifier.weight(1f)
            )

        }
    }
}