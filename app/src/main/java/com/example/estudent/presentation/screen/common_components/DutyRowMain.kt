package com.example.estudent.presentation.screen.common_components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.estudent.domain.model.Duty
import com.example.estudent.ui.theme.mGreen
import com.example.estudent.ui.theme.mTextWhite
import com.example.estudent.util.getDisplayDate
import com.example.estudent.util.getRowColor

@Composable
fun DutyRowMain(
    modifier: Modifier = Modifier,
    duty: Duty,
    fontSizeTitleSp: Int = 16,
    fontSizeDescriptionSp: Int = 12,
    onCheckClicked: (Duty) -> Unit
) {
    val rowColor = getRowColor(duty)

    Card(
        modifier = modifier
            .heightIn(min = 50.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        backgroundColor = if (duty.isCompleted) rowColor.copy(alpha = 0.3f) else rowColor,
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = duty.title,
                    color = mTextWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = fontSizeTitleSp.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    modifier = Modifier.align(Alignment.Start),
                    text = getDisplayDate(duty),
                    color = mTextWhite,
                    fontWeight = FontWeight.Normal,
                    fontSize = fontSizeDescriptionSp.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }

            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(
                        if (duty.isCompleted)
                            mGreen
                        else
                            Color.Transparent
                    )
                    .border(
                        width = 2.dp, color = if (duty.isCompleted)
                            mGreen
                        else
                            MaterialTheme.colors.onBackground, shape = CircleShape
                    )
                    .clickable {
                        onCheckClicked(duty)
                    }
            )
        }
    }

}
