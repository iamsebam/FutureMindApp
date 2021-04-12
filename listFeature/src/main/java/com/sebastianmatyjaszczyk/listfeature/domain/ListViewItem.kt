package com.sebastianmatyjaszczyk.listfeature.domain

data class ListViewItem(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val detailUrl: String,
    val modificationDate: String
) : Comparable<ListViewItem> {

    override fun compareTo(other: ListViewItem) = id - other.id
}
