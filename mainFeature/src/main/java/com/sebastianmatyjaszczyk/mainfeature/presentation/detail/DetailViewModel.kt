package com.sebastianmatyjaszczyk.mainfeature.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sebastianmatyjaszczyk.mainfeature.repository.DetailUrlRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    detailUrlRepository: DetailUrlRepository
) : ViewModel() {

    val detailUrlLiveData = detailUrlRepository.detailUrl.asLiveData()
}
