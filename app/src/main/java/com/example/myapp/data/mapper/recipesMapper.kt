package com.example.myapp.data.mapper

import com.example.myapp.Domain.models.Recipe
import com.example.myapp.data.local.RecipeEntity
import com.example.myapp.data.remote.respond.RecipeDto

fun RecipeEntity.toRecipe():Recipe{
    return Recipe(
        id = id,
        image = image,
        caloriesPerServing = caloriesPerServing,
        cookTimeMinutes = cookTimeMinutes,
        cuisine = cuisine,
        difficulty = difficulty,
        ingredients = try{
            ingredients.split(",")
        }catch (e:Exception){
            listOf("","")
        },
        instructions = try{
            instructions.split(",")
        }catch (e:Exception){
            listOf("","")
        },
        mealType = try{
            mealType.split(",")
        }catch (e:Exception){
            listOf("","")
        },
        name = name,
        servings = servings,
        prepTimeMinutes = prepTimeMinutes,
        rating = rating,
        reviewCount = reviewCount,
        tags =try{
            tags.split(",")
        }catch (e:Exception){
            listOf("","")
        },
        userId = userId

    )
}

fun RecipeDto.toRecipeEntity():RecipeEntity{
    return RecipeEntity(
        id = id,
        image = image,
        caloriesPerServing = caloriesPerServing,
        cookTimeMinutes = cookTimeMinutes,
        cuisine = cuisine,
        difficulty = difficulty,
        ingredients = ingredients.joinToString(","),
        instructions = instructions.joinToString(","),
        mealType = mealType.joinToString(","),
        name = name,
        servings = servings,
        prepTimeMinutes = prepTimeMinutes,
        rating = rating,
        reviewCount = reviewCount,
        tags = tags.joinToString(","),
        userId = userId
    )
}