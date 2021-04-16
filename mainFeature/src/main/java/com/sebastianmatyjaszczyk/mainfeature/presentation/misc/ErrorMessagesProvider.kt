package com.sebastianmatyjaszczyk.mainfeature.presentation.misc

import android.content.Context
import com.sebastianmatyjaszczyk.resourceslib.R
import javax.inject.Inject

class ErrorMessagesProvider @Inject constructor(
    private val context: Context
) {

    val errorNoInternet get() = context.getString(R.string.error_no_internet)
    val errorUnknown get() = context.getString(R.string.error_unknown)
    val messageNoData get() = context.getString(R.string.message_no_data)
}
