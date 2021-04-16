package com.sebastianmatyjaszczyk.mainfeaturelib.mainFeatureDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ListItemEntity::class], version = 1, exportSchema = false)
abstract class MainFeatureDatabase : RoomDatabase() {

    abstract fun listItemDao(): ListItemDao
}
