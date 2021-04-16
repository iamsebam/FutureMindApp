package com.sebastianmatyjaszczyk.mainfeaturelib.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.sebastianmatyjaszczyk.mainfeaturelib.mainFeatureApi.MainFeatureApi
import com.sebastianmatyjaszczyk.mainfeaturelib.mainFeatureDatabase.ListItemDao
import com.sebastianmatyjaszczyk.mainfeaturelib.mainFeatureDatabase.MainFeatureDatabase
import com.sebastianmatyjaszczyk.networklib.BASE_URL
import com.sebastianmatyjaszczyk.networklib.response.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainFeatureLibModule {

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
    fun provideMainFeatureApi(retrofit: Retrofit.Builder): MainFeatureApi =
        retrofit.build()
            .create(MainFeatureApi::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MainFeatureDatabase =
        Room.databaseBuilder(
            context,
            MainFeatureDatabase::class.java,
            "main_feature_database"
        ).build()

    @Singleton
    @Provides
    fun provideListItemDao(database: MainFeatureDatabase): ListItemDao = database.listItemDao()
}
