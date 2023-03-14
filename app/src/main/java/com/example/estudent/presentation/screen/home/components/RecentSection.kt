package com.example.estudent.presentation.screen.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.estudent.domain.model.Duty
import com.example.estudent.presentation.screen.common_components.DutyRowExpanded

@Composable
fun RecentSection(
    duty: Duty,
    duty1: Duty,
    duty2: Duty,
    onCheckClicked: (Duty) -> Unit,
) {
    Column(
        modifier = Modifier.padding(8.dp),
    ) {

        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = "Recent",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Start
        )

        DutiesColumn(
            duties = listOf(duty, duty1, duty2, duty1),
            modifier = Modifier.fillMaxWidth(),
            content = { duty ->
                DutyRowExpanded(duty = duty, onCheckClicked = { duty ->
                    onCheckClicked(duty)
                })
            }
        )
    }
}



