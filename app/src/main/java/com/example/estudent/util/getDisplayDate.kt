package com.example.estudent.util

import com.example.estudent.domain.model.Duty
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

fun getDisplayDate(duty: Duty) : String {
    val dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val deadLineDate = LocalDate.parse(duty.deadline, dateFormat)
    val dayOfWeek = deadLineDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.US)

    if (!duty.hasDeadline)
        return "No deadline"

    return "$dayOfWeek, ${duty.deadline}"
}