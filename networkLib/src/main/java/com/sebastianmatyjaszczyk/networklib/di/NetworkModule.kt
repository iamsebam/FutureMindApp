package com.sebastianmatyjaszczyk.networklib.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sebastianmatyjaszczyk.networklib.BASE_URL
import com.sebastianmatyjaszczyk.networklib.ListApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .create()

    @Singleton
    @Provides
    fun providesRetrofit(gson: Gson): Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))

    @Singleton
    @Provides
    fun provideListApi(retrofit: Retrofit.Builder): ListApi =
        retrofit.build()
            .create(ListApi::class.java)
}