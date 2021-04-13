package com.sebastianmatyjaszczyk.databaselib.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list_item_table")
class ListItemEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "detail_url") val detailUrl: String,
    @ColumnInfo(name = "modification_date") val modificationDate: String
)
