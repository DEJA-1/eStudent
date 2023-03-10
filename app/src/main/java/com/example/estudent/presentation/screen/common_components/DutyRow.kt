package com.example.estudent.presentation.screen.common_components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.estudent.ui.theme.*

data class Duty(
    val dutyTitle: String,
    val dutyDescription: String,
    val dutyCategory: String,
    val dutyDeadline: String
)

@Composable
fun DutyRow(
    modifier: Modifier = Modifier,
    duty: Duty,
) {

    val color = when(duty.dutyCategory) {
        "Important" -> mRed
        "Moderate" -> mYellow
        "Minor" -> mGreen
        else -> mTextWhite
    }

    var showDetails by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier
            .fillMaxWidth(0.7f)
            .clickable {
                showDetails = !showDetails
            },
        shape = RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp),
        border = BorderStroke(width = 1.dp, color = Color.Black),
        elevation = 8.dp
    ) {
        Box(
            modifier = Modifier
                .heightIn(min = 50.dp)
                .background(
                    Brush.linearGradient(
                        colors = listOf(mBackgroundBlackSecondary, color.copy(alpha = 0.8f)),
                        start = Offset(200f, Float.POSITIVE_INFINITY),
                        end = Offset(0f, 0f)
                    )
                ),
            contentAlignment = Center
        ) {

            Column() {

                DutyTitle(
                    text = duty.dutyTitle
                )

                AnimatedVisibility(visible = showDetails) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 4.dp, bottom = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        DutyDescription(
                            text = duty.dutyDescription
                        )

                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            horizontalAlignment = CenterHorizontally
                        ) {

                            DetailsSurface(
                                text = duty.dutyCategory,
                                textColor = color
                            )

                            DetailsSurface(
                                text = duty.dutyDeadline,
                                textColor = mTextWhite
                            )
                        }

                    }
                }
            }
        }

    }

}

@Composable
fun DutyTitle(
    text: String,
) {
    Text(
        modifier = Modifier
            .padding(start = 6.dp, top = 4.dp, bottom = 4.dp, end = 6.dp),
        text = text,
        maxLines = 3,
        color = MaterialTheme.colors.onBackground,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 12.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun DutyDescription(
    text: String
) {
    Text(
        modifier = Modifier
            .fillMaxWidth(0.6f),
        text = text,
        fontSize = 10.sp,
        color = MaterialTheme.colors.onBackground
    )
}

@Composable
fun DetailsSurface(
    text: String,
    textColor: Color,
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 4.dp
    ) {
        Text(
            modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 2.dp, bottom = 2.dp),
            text = text,
            color = textColor,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontSize = 10.sp
        )
    }
}