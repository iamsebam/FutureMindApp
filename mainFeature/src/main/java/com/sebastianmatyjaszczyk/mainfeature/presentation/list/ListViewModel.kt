package com.sebastianmatyjaszczyk.mainfeature.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastianmatyjaszczyk.commonlib.NetworkError
import com.sebastianmatyjaszczyk.commonlib.Result
import com.sebastianmatyjaszczyk.commonlib.SingleLiveEvent
import com.sebastianmatyjaszczyk.commonlib.ViewState
import com.sebastianmatyjaszczyk.mainfeature.domain.ListItemViewEntity
import com.sebastianmatyjaszczyk.mainfeature.domain.ViewEntity
import com.sebastianmatyjaszczyk.mainfeature.presentation.misc.ErrorMessagesProvider
import com.sebastianmatyjaszczyk.mainfeature.repository.DetailUrlRepository
import com.sebastianmatyjaszczyk.mainfeature.repository.ListRepository
import com.sebastianmatyjaszczyk.mainfeature.util.ViewEntityMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class ListViewModel @Inject constructor(
    private val listRepository: ListRepository,
    private val detailUrlRepository: DetailUrlRepository,
    private val errorMessagesProvider: ErrorMessagesProvider,
    private val viewEntityMapper: ViewEntityMapper
) : ViewModel() {

    private val _viewState: MutableLiveData<ViewState<ViewEntity>> = MutableLiveData()
    val detailViewNavigationAction: SingleLiveEvent<Nothing> = SingleLiveEvent()

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

    fun onListItemSelected(item: ListItemViewEntity) {
        detailUrlRepository.setCurrentUrl(item.detailUrl)
        detailViewNavigationAction.call()
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
        _viewState.value = ViewState.Error(errorMessagesProvider.messageNoData)
    }

    private fun showError(error: Throwable) {
        val errorMessage = mapErrorToErrorMessage(error)
        _viewState.value = ViewState.Error(errorMessage)
    }

    private fun mapErrorToErrorMessage(error: Throwable) =
        when (error) {
            is NetworkError.NoNetworkError -> errorMessagesProvider.errorNoInternet
            else -> error.message
        } ?: errorMessagesProvider.errorUnknown
}
