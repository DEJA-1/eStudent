package com.example.estudent.presentation.screen.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.example.estudent.domain.model.Duty

@Composable
fun ImportantAndUpcomingSection(
    duty: Duty,
    duty1: Duty,
    duty2: Duty
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        DutiesSection(
            duties = listOf(duty, duty1, duty),
            modifier = Modifier.fillMaxWidth(0.4f),
            text = "Important"
        ) { duties ->

            DutiesColumn(
                duties = duties,
                minHeightDp = 30,
                isScaled = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(-1f)
            )
        }

        DutiesSection(
            duties = listOf(duty2, duty2),
            text = "Upcoming"
        ) { duties ->

            DutiesColumn(
                duties = duties,
                minHeightDp = 70,
                modifier = Modifier
                    .fillMaxWidth()
            )

        }

    }
}