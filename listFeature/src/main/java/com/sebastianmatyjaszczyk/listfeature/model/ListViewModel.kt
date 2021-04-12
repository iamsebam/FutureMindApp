package com.sebastianmatyjaszczyk.listfeature.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastianmatyjaszczyk.listfeature.domain.ListResult
import com.sebastianmatyjaszczyk.listfeature.domain.ListViewItem
import com.sebastianmatyjaszczyk.listfeature.domain.ViewEntity
import com.sebastianmatyjaszczyk.listfeature.repository.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val listRepository: ListRepository
) : ViewModel() {

    private val _listViewItems: MutableLiveData<ViewEntity> = MutableLiveData()
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    private val _error: MutableLiveData<String> = MutableLiveData()

    val listViewItems: LiveData<ViewEntity> get() = _listViewItems
    val isLoading: LiveData<Boolean> get() = _isLoading
    val error: LiveData<String> get() = _error

    fun fetchData() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = listRepository.fetchData()
            _isLoading.value = false
            when (result) {
                is ListResult.Success -> _listViewItems.value = result.data
                is ListResult.Error -> _error.value = result.error.message
            }
        }
    }

    fun onItemSelected(listViewItem: ListViewItem) {
        // TODO open the webview with the detailUrl provided
    }
}
