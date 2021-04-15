package com.sebastianmatyjaszczyk.databaselib.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.sebastianmatyjaszczyk.databaselib.entity.ListItemEntity

@Dao
interface ListItemDao {

    @Query("SELECT * FROM list_item_table ORDER BY id ASC")
    suspend fun getAllSorted(): List<ListItemEntity>

    @Transaction
    suspend fun updateAll(listItems: List<ListItemEntity>) {
        deleteAll()
        insertAll(listItems)
    }

    @Insert
    suspend fun insertAll(listItems: List<ListItemEntity>)

    @Query("DELETE FROM list_item_table")
    suspend fun deleteAll()
}
