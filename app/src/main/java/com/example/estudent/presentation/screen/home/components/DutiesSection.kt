package com.example.estudent.presentation.screen.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.estudent.domain.model.Duty
import com.example.estudent.ui.theme.mTextWhite


@Composable
fun DutiesSection(
    duties: List<Duty>,
    modifier: Modifier = Modifier,
    text: String,
    columnSpacedByDp: Int = 8,
    dutiesSection: @Composable (List<Duty>) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(columnSpacedByDp.dp)
    ) {

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = text,
            color = mTextWhite,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )

        dutiesSection(duties)
    }
}
