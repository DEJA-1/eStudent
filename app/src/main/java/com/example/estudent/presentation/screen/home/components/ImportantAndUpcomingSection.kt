package com.example.estudent.presentation.screen.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.estudent.domain.model.Duty
import com.example.estudent.presentation.screen.common_components.DutyRowShrank

@Composable
fun ImportantAndUpcomingSection(
    duties: List<Duty>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        DutiesSection(
            duties = duties,
            modifier = Modifier.fillMaxWidth(0.46f),
            text = "Important",
            dutiesSection = { duties ->

                DutiesColumn(
                    duties = duties,
                    modifier = Modifier
                        .fillMaxWidth(),
                    content = { duty ->
                        DutyRowShrank(
                            duty = duty
                        )
                    }
                )
            }
        )

        DutiesSection(
            duties = duties,
            text = "Upcoming",
            dutiesSection = { duties ->

                DutiesColumn(
                    duties = duties,
                    modifier = Modifier
                        .fillMaxWidth(),
                    content = { duty ->
                        DutyRowShrank(
                            duty = duty
                        )
                    }
                )

            }
        )

    }
}