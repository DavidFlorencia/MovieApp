package com.dflorencia.movieapp.di

import com.dflorencia.movieapp.api.Keys
import com.dflorencia.movieapp.api.TmdbApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    private val BASE_URL = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun provideApiKey(): String = Keys.apiKey()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(baseUrl: String, moshi: Moshi): Retrofit =
        Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(baseUrl)
        .build()

    @Provides
    fun provideTmdbService(): TmdbApi =
        provideRetrofit(BASE_URL,provideMoshi())
            .create(TmdbApi::class.java)
}