package com.sebastianmatyjaszczyk.commonlib.stringDateFormatter

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class TargetStringFormatter @Inject constructor(
    private val dateTimeFormatter: DateTimeFormatter
) {

    fun format(localDate: LocalDate): String = dateTimeFormatter.format(localDate)

}