package com.example.estudent.presentation.screen.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.estudent.domain.model.Duty
import com.example.estudent.presentation.screen.common_components.DutyRowShrank

@Composable
fun DutiesColumn(
    duties: List<Duty>,
    modifier: Modifier = Modifier,
    content: @Composable (Duty) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(duties) { duty ->
            content(duty)
        }
    }
}

