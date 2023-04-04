package com.example.estudent.util

import androidx.compose.ui.graphics.Color
import com.example.estudent.domain.model.Duty
import com.example.estudent.ui.theme.mGreen
import com.example.estudent.ui.theme.mRed
import com.example.estudent.ui.theme.mYellow
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


fun getRowColor(duty: Duty): Color {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy")
    val deadlineDate = dateFormat.parse(duty.deadline)
    val todayDate = Date()

    val daysDiff = TimeUnit.DAYS.convert(deadlineDate.time - todayDate.time, TimeUnit.MILLISECONDS)

    return when {
        !duty.hasDeadline -> mGreen
        daysDiff <= 2 -> mRed
        daysDiff <= 5 -> mYellow
        else -> mGreen
    }
}