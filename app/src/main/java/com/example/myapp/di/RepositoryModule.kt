package com.example.myapp.di

import com.example.myapp.Domain.repository.RecipeRepository
import com.example.myapp.data.repository.RecipeRepoImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideRecipeRepository(repositoryImpl: RecipeRepoImp): RecipeRepository

}