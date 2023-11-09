package com.ironbird.learningspring.util

import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@Component
class DateUtils {

    fun createDateFromDateString(dateString: String?): Date {
        val format: DateFormat = SimpleDateFormat("yyyy-MM-dd")

        return if (StringUtils.hasText(dateString)) {
            try {
                format.parse(dateString)
            } catch (e: ParseException) {
                Date()
            }
        } else {
            Date()
        }
    }
}