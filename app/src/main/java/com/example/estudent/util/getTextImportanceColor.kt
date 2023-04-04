package com.example.estudent.util

import androidx.compose.ui.graphics.Color
import com.example.estudent.ui.theme.mGreen
import com.example.estudent.ui.theme.mRed
import com.example.estudent.ui.theme.mTextWhite
import com.example.estudent.ui.theme.mYellow

fun getTextImportanceColor(importance: String) : Color {
    return when(importance) {
        "Important" -> mRed
        "Moderate" -> mYellow
        "Unimportant" -> mGreen
        else -> mTextWhite
    }
}