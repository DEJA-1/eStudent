package com.example.estudent.presentation.state

// For Add Screen Input Fields
data class InputFieldDutyState(
    val title: String = "",
    val characterCounterTitle: Int = 0,
    val maxTitleLength: Int = 40,

    val description: String = "",
    val characterCounterDescription: Int = 0,
    val maxDescriptionLength: Int = 70,

    val category: String = "",
    val characterCounterCategory: Int = 0,
    val maxCategoryLength: Int = 10,

    val deadline: String = "",
    val characterCounterDeadline: Int = 0,
    val maxDeadlineLength: Int = 10

        //"2023-03-20"
)
