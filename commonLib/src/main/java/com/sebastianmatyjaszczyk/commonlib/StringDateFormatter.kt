package com.sebastianmatyjaszczyk.commonlib

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class StringDateFormatter @Inject constructor(
    private val sourceStringFormatter: DateTimeFormatter,
    private val targetStringFormatter: DateTimeFormatter
) {

    fun format(date: String): String =
        targetStringFormatter.format(
            LocalDate.parse(date, sourceStringFormatter)
        )
}
