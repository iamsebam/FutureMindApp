package com.sebastianmatyjaszczyk.listfeature.repository

import android.util.Patterns
import com.sebastianmatyjaszczyk.databaselib.entity.ListItemEntity
import com.sebastianmatyjaszczyk.listfeature.domain.ListItemViewEntity
import com.sebastianmatyjaszczyk.listfeature.domain.ViewEntity
import com.sebastianmatyjaszczyk.networklib.listApi.NetworkEntity
import javax.inject.Inject

class NetworkEntityMapper @Inject constructor() {

    fun mapToViewEntity(networkEntityList: List<NetworkEntity>) =
        ViewEntity(
            networkEntityList.map { it.toListItemViewEntity() }
        )

    fun mapToDatabaseEntities(networkEntityList: List<NetworkEntity>) =
        networkEntityList.map { it.toListItemDatabaseEntity() }
}

private fun NetworkEntity.toListItemDatabaseEntity(): ListItemEntity {
    val (description, detailUrl) = this.description.separateUrl()
    return ListItemEntity(
        id = orderId,
        title = title,
        description = description.trim(),
        imageUrl = imageUrl,
        detailUrl = detailUrl.trim(),
        modificationDate = modificationDate
    )
}

private fun NetworkEntity.toListItemViewEntity(): ListItemViewEntity {
    val (description, detailUrl) = this.description.separateUrl()
    return ListItemViewEntity(
        id = orderId,
        title = title,
        description = description.trim(),
        imageUrl = imageUrl,
        detailUrl = detailUrl.trim(),
        modificationDate = modificationDate
    )
}

private fun String.separateUrl(): Pair<String, String> {
    val matcher = Patterns.WEB_URL.matcher(this)
    var matchStart = length
    while (matcher.find()) {
        matchStart = matcher.start()
    }
    val noUrl = substring(0, matchStart)
    val url = substring(matchStart)
    return Pair(noUrl, url)
}
