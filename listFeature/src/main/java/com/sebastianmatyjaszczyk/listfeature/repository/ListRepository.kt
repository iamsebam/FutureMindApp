package com.sebastianmatyjaszczyk.listfeature.repository

import com.sebastianmatyjaszczyk.listfeature.domain.ListResult
import com.sebastianmatyjaszczyk.listfeature.domain.ListViewItem
import com.sebastianmatyjaszczyk.listfeature.domain.ViewEntity
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class ListRepository @Inject constructor() {

    private fun loadList(): ListResult = ListResult.Success(
        ViewEntity(
            listOf(
                ListViewItem(0, "Title1", "description1", "", "", "11-04-2021"),
                ListViewItem(1, "Title2", "description2", "", "", "11-04-2021"),
                ListViewItem(2, "Title3", "description3", "", "", "11-04-2021"),
                ListViewItem(3, "Title4", "description4", "", "", "11-04-2021"),
                ListViewItem(4, "Title5", "description5", "", "", "11-04-2021"),
            )
        )
    )

    private fun showError(): ListResult = ListResult.Error(Throwable("VERY SERIOUS ERROR"))

    private var showError = false

    suspend fun fetchData() =
        withContext(Dispatchers.IO) {
            delay(2000)
            showError = !showError
            if (showError) showError() else loadList()
        }
}
