package com.sebastianmatyjaszczyk.listfeature.repository

import android.util.Patterns
import com.sebastianmatyjaszczyk.listfeature.domain.ListViewItem
import com.sebastianmatyjaszczyk.listfeature.domain.ViewEntity
import com.sebastianmatyjaszczyk.networklib.listApi.NetworkEntity
import javax.inject.Inject

class NetworkEntityMapper @Inject constructor() {

    fun mapToViewEntity(networkEntityList: List<NetworkEntity>) =
        ViewEntity(
            networkEntityList.map { it.toListViewItem() }
        )
}

private fun NetworkEntity.toListViewItem(): ListViewItem {
    val (description, detailUrl) = this.description.separateUrl()
    return ListViewItem(
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
