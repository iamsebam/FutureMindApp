package com.sebastianmatyjaszczyk.listfeature.domain

data class ViewEntity(
    val data: List<ListItemViewEntity>
)

internal fun ViewEntity.sorted() = copy(
    data = data.sorted()
)
