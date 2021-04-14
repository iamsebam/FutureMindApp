package com.sebastianmatyjaszczyk.listfeature.util

import android.util.Patterns
import com.sebastianmatyjaszczyk.databaselib.entity.ListItemEntity
import com.sebastianmatyjaszczyk.networklib.listApi.NetworkEntity
import javax.inject.Inject

class NetworkEntityMapper @Inject constructor() {

    fun mapToDomainEntities(networkEntityList: List<NetworkEntity>) =
        networkEntityList.map { it.toListItemEntity() }
}

private fun NetworkEntity.toListItemEntity(): ListItemEntity {
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
