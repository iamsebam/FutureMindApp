package com.sebastianmatyjaszczyk.listfeature.util

import com.sebastianmatyjaszczyk.commonlib.stringDateFormatter.StringDateFormatter
import com.sebastianmatyjaszczyk.databaselib.entity.ListItemEntity
import com.sebastianmatyjaszczyk.listfeature.domain.ListItemViewEntity
import com.sebastianmatyjaszczyk.listfeature.domain.ViewEntity
import javax.inject.Inject

class ViewEntityMapper @Inject constructor(
    private val stringDateFormatter: StringDateFormatter
) {

    fun mapToViewEntity(databaseEntities: List<ListItemEntity>) =
        ViewEntity(
            databaseEntities.map { it.toListItemViewEntity().withDateFormatted(stringDateFormatter) }
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

private fun ListItemViewEntity.withDateFormatted(stringDateFormatter: StringDateFormatter) =
    copy(modificationDate = stringDateFormatter.format(modificationDate))
