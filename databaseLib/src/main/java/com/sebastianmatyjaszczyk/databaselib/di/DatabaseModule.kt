package com.sebastianmatyjaszczyk.databaselib.di

import android.content.Context
import androidx.room.Room
import com.sebastianmatyjaszczyk.databaselib.ListItemRoomDatabase
import com.sebastianmatyjaszczyk.databaselib.dao.ListItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDatabase {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ListItemRoomDatabase =
        Room.databaseBuilder(
            context,
            ListItemRoomDatabase::class.java,
            "list_item_database"
        ).build()


    @Singleton
    @Provides
    fun provideListItemDao(database: ListItemRoomDatabase): ListItemDao = database.listItemDao()

}