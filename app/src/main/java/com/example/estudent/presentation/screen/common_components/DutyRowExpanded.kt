package com.example.estudent.presentation.screen.common_components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.estudent.domain.model.Duty
import com.example.estudent.ui.theme.mGreen
import com.example.estudent.util.getDisplayDate
import com.example.estudent.util.getRowColor

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DutyRowExpanded(
    modifier: Modifier = Modifier,
    duty: Duty,
    circleSizeDp: Int = 42,
    fontSizeTitleSp: Int = 18,
    fontSizeDeadlineSp: Int = 14,
    fontSizeDescriptionSp: Int = 12,
    minHeightDp: Int = 50,
    maxHeightDp: Int = 150,
    onCheckClicked: (Duty) -> Unit,
) {

    val circleColor = getRowColor(duty.deadline)
    var expanded by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(
                min = minHeightDp.dp, max = maxHeightDp.dp
            )
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .padding(bottom = 4.dp)
            .clickable {
                expanded = !expanded
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = CenterVertically
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(if (!expanded) CenterVertically else Top)
                    .width(circleSizeDp.dp)
                    .heightIn(min = if (expanded) maxHeightDp.dp else circleSizeDp.dp)
                    .animateContentSize(
                        animationSpec = tween(easing = EaseOutQuart),
                    )
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
                    fontSize = fontSizeTitleSp.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (duty.isCompleted)
                        MaterialTheme.colors.onBackground.copy(0.3f)
                    else
                        MaterialTheme.colors.onBackground,
                    maxLines = if (expanded) 10 else 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = getDisplayDate(duty.deadline),
                    fontSize = fontSizeDeadlineSp.sp,
                    color = if (duty.isCompleted)
                        MaterialTheme.colors.onBackground.copy(0.3f)
                    else
                        MaterialTheme.colors.onBackground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                AnimatedVisibility(
                    visible = expanded,
                    enter = fadeIn(),
                ) {
                    Text(
                        modifier = Modifier.padding(top = 2.dp),
                        text = "Przykladowy opis zadania prykladowy opis zadania przykladowy opis zadania przykladowy opis zadania przykladowy opis zadania",
                        fontSize = fontSizeDescriptionSp.sp,
                        color = if (duty.isCompleted)
                            MaterialTheme.colors.onBackground.copy(0.3f)
                        else
                            MaterialTheme.colors.onBackground,
                        maxLines = if (expanded) 10 else 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        AnimatedVisibility(
            visible = !expanded,
            enter = fadeIn(animationSpec = tween(delayMillis = 400)),
            exit = scaleOut()
        ) {
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

}