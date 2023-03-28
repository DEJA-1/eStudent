package com.example.estudent.presentation.state

import java.time.LocalDate
import java.time.format.DateTimeFormatter

// For Add Screen Input Fields
data class TextFieldState(
    val title: String = "",
    val characterCounterTitle: Int = 0,
    val maxTitleLength: Int = 40,
    val description: String = "",

    val characterCounterDescription: Int = 0,
    val maxDescriptionLength: Int = 70,
    val category: String = "Projects",

    val characterCounterCategory: Int = 0,
    val maxCategoryLength: Int = 10,
    val deadline: String = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDate.now().plusDays(2)),

    val characterCounterDeadline: Int = 0,
    val maxDeadlineLength: Int = 10,

    val errorMessage: String? = null

)
