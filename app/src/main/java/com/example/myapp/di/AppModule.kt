package com.example.myapp.di

import android.app.Application
import androidx.room.Room
import com.example.myapp.data.local.RecipeDatabase
import com.example.myapp.data.remote.RecipeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Singleton
    @Provides
    fun provideRecipeApi():RecipeApi{
        return Retrofit.Builder()
            .baseUrl(RecipeApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(app: Application):RecipeDatabase{
        return Room.databaseBuilder(
            app,
            RecipeDatabase::class.java,
            "Recipe.db"
        ).build()
    }

}