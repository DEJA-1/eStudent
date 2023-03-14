package com.example.estudent.util

import androidx.compose.ui.text.capitalize
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

fun getDisplayDate(deadline: String) : String {
    val dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val deadLineDate = LocalDate.parse(deadline, dateFormat)
    val dayOfWeek = deadLineDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.US)

    return "$dayOfWeek, $deadline"
}