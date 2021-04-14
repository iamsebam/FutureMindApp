package com.sebastianmatyjaszczyk.commonlib.stringDateFormatter

import javax.inject.Inject

class StringDateFormatter @Inject constructor(
    private val sourceStringParser: SourceStringParser,
    private val targetStringFormatter: TargetStringFormatter
) {

    fun format(date: String): String {
        return targetStringFormatter.format(sourceStringParser.parse(date))
    }
}