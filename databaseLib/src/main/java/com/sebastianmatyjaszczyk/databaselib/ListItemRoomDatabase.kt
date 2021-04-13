package com.sebastianmatyjaszczyk.databaselib

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sebastianmatyjaszczyk.databaselib.dao.ListItemDao
import com.sebastianmatyjaszczyk.databaselib.entity.ListItemEntity

@Database(entities = [ListItemEntity::class], version = 1, exportSchema = false)
abstract class ListItemRoomDatabase : RoomDatabase() {

    abstract fun listItemDao(): ListItemDao


}