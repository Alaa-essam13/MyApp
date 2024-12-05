package com.example.myapp.data.repository

import android.util.Log
import com.example.myapp.Domain.models.Recipe
import com.example.myapp.Domain.repository.RecipeRepository
import com.example.myapp.data.local.RecipeDatabase
import com.example.myapp.data.mapper.toRecipe
import com.example.myapp.data.mapper.toRecipeEntity
import com.example.myapp.data.remote.RecipeApi
import com.example.myapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RecipeRepoImp @Inject constructor(
    private val recipeApi: RecipeApi,
    private val recipeDatabase: RecipeDatabase
): RecipeRepository {
    override suspend fun getRecipeList(): Flow<Resource<List<Recipe>>> {
        return flow {
            emit(Resource.Loading(true))
            val localData = recipeDatabase.recipeDao.getAllRecipe()
           if(localData.isNotEmpty()){
                emit(Resource.Success(
                    data = localData.map {recipeEntity ->
                    recipeEntity.toRecipe()
                }))
               Log.d("GGGG", localData.toString())
               emit(Resource.Loading(false))
                return@flow
           }

            val recipeListFromApi = try {
                recipeApi.getAllRecipes()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error getting movies"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error getting movies"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error getting movies"))
                return@flow
            }

            val recipeEntities = recipeListFromApi.recipes.let {
                it.map { recipeDto ->
                    recipeDto.toRecipeEntity()
                }
            }

            recipeDatabase.recipeDao.insertRecipe(recipeEntities)
            emit(Resource.Success(
                data = recipeEntities.map { recipeEntity ->
                    recipeEntity.toRecipe()
                }))
            emit(Resource.Loading(false))
        }
    }

    override suspend fun getRecipe(recipeId: Int): Flow<Resource<Recipe>> {
        return flow {
            emit(Resource.Loading(true))
            val localData = recipeDatabase.recipeDao.getRecipeById(recipeId)
            if(localData!=null){
                emit(Resource.Success(
                    data = localData.toRecipe()
                ))
               emit(Resource.Loading(false))
                return@flow
            }
            emit(Resource.Error("Error getting movie"))
            emit(Resource.Loading(false))
        }
    }
}