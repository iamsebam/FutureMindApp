package com.sebastianmatyjaszczyk.mainfeature.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class DetailUrlRepository @Inject constructor() {

    private val _detailUrl = MutableStateFlow("")

    val detailUrl = _detailUrl.asStateFlow()

    fun setCurrentUrl(url: String) {
        _detailUrl.value = url
    }
}
