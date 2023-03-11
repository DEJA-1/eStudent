package com.example.estudent.presentation.state

import com.example.estudent.domain.model.Duty

data class DutyListState(
    val isLoading: Boolean = false,
    val duties: List<Duty> = emptyList(),
    val error: String = ""
)
