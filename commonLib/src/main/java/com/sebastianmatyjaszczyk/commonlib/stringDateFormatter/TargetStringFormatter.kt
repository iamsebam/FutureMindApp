package com.sebastianmatyjaszczyk.commonlib.stringDateFormatter

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TargetStringFormatter(
    private val dateTimeFormatter: DateTimeFormatter
) {

    fun format(localDate: LocalDate): String = dateTimeFormatter.format(localDate)
}
