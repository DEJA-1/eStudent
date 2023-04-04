package com.example.estudent.presentation.screen.common_components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.estudent.domain.model.Duty

import com.example.estudent.ui.theme.*
import com.example.estudent.util.getDisplayDate
import com.example.estudent.util.getRowColor

@Composable
fun DutyRowShrank(
    modifier: Modifier = Modifier,
    duty: Duty,
) {
    val rowColor = getRowColor(duty = duty)

    Card(
        modifier = modifier
            .heightIn(min = 50.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        backgroundColor = rowColor,
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            horizontalAlignment = Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = duty.title,
                color = mTextWhite,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                modifier = Modifier.align(Start),
                text = getDisplayDate(duty),
                color = mTextWhite,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }

    }

}
