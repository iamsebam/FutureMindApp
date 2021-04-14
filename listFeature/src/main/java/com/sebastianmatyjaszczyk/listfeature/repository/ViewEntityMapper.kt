package com.sebastianmatyjaszczyk.listfeature.repository

import com.sebastianmatyjaszczyk.databaselib.entity.ListItemEntity
import com.sebastianmatyjaszczyk.listfeature.domain.ListItemViewEntity
import com.sebastianmatyjaszczyk.listfeature.domain.ViewEntity
import javax.inject.Inject

class ViewEntityMapper @Inject constructor() {

    fun mapToViewEntity(databaseEntities: List<ListItemEntity>) =
        ViewEntity(
            databaseEntities.map { it.toListItemViewEntity() }
        )
}

private fun ListItemEntity.toListItemViewEntity() = ListItemViewEntity(
    id = id,
    title = title,
    description = description,
    imageUrl = imageUrl,
    detailUrl = detailUrl,
    modificationDate = modificationDate
)
