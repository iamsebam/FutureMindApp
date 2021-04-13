package com.sebastianmatyjaszczyk.listfeature.domain

data class ListItemViewEntity(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val detailUrl: String,
    val modificationDate: String
) : Comparable<ListItemViewEntity> {

    override fun compareTo(other: ListItemViewEntity) = id - other.id
}
