package com.sebastianmatyjaszczyk.mainfeature.util

import com.sebastianmatyjaszczyk.commonlib.StringDateFormatter
import com.sebastianmatyjaszczyk.mainfeature.domain.ListItemViewEntity
import com.sebastianmatyjaszczyk.mainfeature.domain.ViewEntity
import com.sebastianmatyjaszczyk.mainfeaturelib.mainFeatureDatabase.ListItemEntity
import javax.inject.Inject

class ViewEntityMapper @Inject constructor(
    private val stringDateFormatter: StringDateFormatter
) {

    fun mapToViewEntity(databaseEntities: List<ListItemEntity>) =
        ViewEntity(
            databaseEntities.map { it.toListItemViewEntity().withDateFormatted(stringDateFormatter) }
        )
}

private fun ListItemEntity.toListItemViewEntity() =
    ListItemViewEntity(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        detailUrl = detailUrl,
        modificationDate = modificationDate
    )

private fun ListItemViewEntity.withDateFormatted(stringDateFormatter: StringDateFormatter) =
    copy(modificationDate = stringDateFormatter.format(modificationDate))
