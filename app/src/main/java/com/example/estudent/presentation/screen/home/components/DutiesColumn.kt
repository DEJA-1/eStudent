package com.example.estudent.presentation.screen.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.estudent.domain.model.Duty
import com.example.estudent.presentation.screen.common_components.DutyRow

@Composable
fun DutiesColumn(
    duties: List<Duty>,
    modifier: Modifier = Modifier,
    minHeightDp: Int,
    isScaled: Boolean = false
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(duties) { duty ->
            DutyRow(
                duty = duty,
                modifier = modifier,
                isScaled = isScaled,
                minHeightDp = minHeightDp
            )
        }
    }
}

