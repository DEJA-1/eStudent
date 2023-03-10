package com.example.estudent.presentation.screen.common_components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.estudent.domain.model.Duty
import com.example.estudent.ui.theme.mBackgroundBlack
import com.example.estudent.ui.theme.mGreen
import com.example.estudent.ui.theme.mRed
import com.example.estudent.ui.theme.mTextWhite
import com.example.estudent.util.getDisplayDate
import com.example.estudent.util.getRowColor

@Composable
fun DutyRowExpanded(
    modifier: Modifier = Modifier,
    duty: Duty,
    onCheckClicked: (Duty) -> Unit,
) {

    val circleColor = getRowColor(duty.deadline)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 50.dp)
            .padding(bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = CenterVertically
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(CenterVertically)
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(
                        if (duty.isCompleted)
                            circleColor.copy(0.3f)
                        else
                            circleColor
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = duty.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    color = if (duty.isCompleted)
                        MaterialTheme.colors.onBackground.copy(0.3f)
                    else
                        MaterialTheme.colors.onBackground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = getDisplayDate(duty.deadline),
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Italic,
                    color = if (duty.isCompleted)
                        MaterialTheme.colors.onBackground.copy(0.3f)
                    else
                        MaterialTheme.colors.onBackground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }
        }

        Box(
            modifier = Modifier
                .align(CenterVertically)
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