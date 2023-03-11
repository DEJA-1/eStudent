package com.example.estudent.util

import androidx.compose.ui.graphics.Color
import com.example.estudent.ui.theme.mGreen
import com.example.estudent.ui.theme.mRed
import com.example.estudent.ui.theme.mYellow
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


fun getRowColor(deadline: String): Color {
    val dateFormat = SimpleDateFormat("dd.MM.yy")
    val deadlineDate = dateFormat.parse(deadline)
    val todayDate = Date()

    val daysDiff = TimeUnit.DAYS.convert(deadlineDate.time - todayDate.time, TimeUnit.MILLISECONDS)

    return when {
        daysDiff <= 2 -> mRed
        daysDiff <= 5 -> mYellow
        else -> mGreen
    }
}