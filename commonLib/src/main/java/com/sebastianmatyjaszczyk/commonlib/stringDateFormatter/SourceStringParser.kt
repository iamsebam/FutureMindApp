package com.sebastianmatyjaszczyk.commonlib.stringDateFormatter

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class SourceStringParser @Inject constructor(
    private val dateTimeFormatter: DateTimeFormatter
) {

    fun parse(string: String): LocalDate = LocalDate.parse(string, dateTimeFormatter)
}
