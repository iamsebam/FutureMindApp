package com.sebastianmatyjaszczyk.networklib.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sebastianmatyjaszczyk.networklib.BASE_URL
import com.sebastianmatyjaszczyk.networklib.listApi.ListApi
import com.sebastianmatyjaszczyk.networklib.response.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit.Builder = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(NetworkResponseAdapterFactory())

    @Singleton
    @Provides
    fun provideListApi(retrofit: Retrofit.Builder): ListApi =
        retrofit.build()
            .create(ListApi::class.java)
}
