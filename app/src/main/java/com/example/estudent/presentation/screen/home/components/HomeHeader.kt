package com.example.estudent.presentation.screen.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.estudent.R
import com.example.estudent.common.TEST_TAG_DUTY_ROW
import com.example.estudent.domain.model.Duty
import com.example.estudent.presentation.screen.common_components.DutyRow

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    duty: Duty,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GreetingText()

        DueTomorrowDuty(
            duty = duty
        )
    }
}

@Composable
fun GreetingText() {
    Column() {
        Text(
            text = stringResource(R.string.hello_string),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6
        )

        Text(
            text = stringResource(R.string.you_got_this_string),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
fun DueTomorrowDuty(
    duty: Duty
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DutyRow(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .testTag(TEST_TAG_DUTY_ROW),
            duty = duty
        )

        Text(
            text = stringResource(R.string.due_tomorrow_string),
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.subtitle2,
            textAlign = TextAlign.Center
        )
    }
}
