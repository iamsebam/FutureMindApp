package com.sebastianmatyjaszczyk.listfeature.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastianmatyjaszczyk.commonlib.Result
import com.sebastianmatyjaszczyk.commonlib.ViewState
import com.sebastianmatyjaszczyk.listfeature.domain.ListItemViewEntity
import com.sebastianmatyjaszczyk.listfeature.domain.ViewEntity
import com.sebastianmatyjaszczyk.listfeature.repository.ListRepository
import com.sebastianmatyjaszczyk.listfeature.util.ViewEntityMapper
import com.sebastianmatyjaszczyk.networklib.response.NetworkError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class ListViewModel @Inject constructor(
    private val listRepository: ListRepository,
    private val viewEntityMapper: ViewEntityMapper
) : ViewModel() {

    private val _viewState: MutableLiveData<ViewState<ViewEntity>> = MutableLiveData()

    val viewState: LiveData<ViewState<ViewEntity>> get() = _viewState

    init {
        observeListItems()
        loadData()
    }

    fun refreshData() {
        viewModelScope.launch {
            listRepository.refreshData()
        }
    }

    fun onItemSelected(item: ListItemViewEntity) {
        // TODO open the webview with the detailUrl provided
    }

    private fun observeListItems() {
        viewModelScope.launch {
            listRepository.listItemsFlow.collect { result ->
                when (result) {
                    is Result.Success -> {
                        val viewEntity = viewEntityMapper.mapToViewEntity(result.data)
                        showSuccess(viewEntity)
                    }
                    is Result.Failure -> showError(result.error)
                    is Result.Empty -> showEmpty()
                    is Result.Loading -> showLoading()
                }
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            listRepository.loadData()
        }
    }

    private fun showSuccess(viewEntity: ViewEntity) {
        _viewState.value = ViewState.Success(viewEntity)
    }

    private fun showLoading() {
        _viewState.value = ViewState.Loading
    }

    private fun showEmpty() {
        _viewState.value = ViewState.Error("No data available.")
    }

    private fun showError(error: Throwable) {
        val errorMessage = when (error) {
            is NetworkError.NoNetworkError -> "No Internet connection."
            else -> error.message
        } ?: "Something went terribly wrong."
        _viewState.value = ViewState.Error(errorMessage)
    }
}
