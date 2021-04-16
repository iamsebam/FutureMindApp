package com.sebastianmatyjaszczyk.commonlib.stringDateFormatter

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SourceStringParser(
    private val dateTimeFormatter: DateTimeFormatter
) {

    fun parse(string: String): LocalDate = LocalDate.parse(string, dateTimeFormatter)
}
