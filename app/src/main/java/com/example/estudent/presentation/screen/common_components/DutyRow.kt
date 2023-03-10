package com.example.estudent.presentation.screen.common_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.estudent.ui.theme.mBackgroundBlackSecondary


@Preview
@Composable
fun DutyRow(
    modifier: Modifier = Modifier,
    color: Color = Color.Red,
) {
//    Surface(
//        modifier = modifier
//            .fillMaxWidth(0.7f)
//            .height(40.dp)
//            .border(
//                width = 2.dp,
//                color = Color.Black,
//                RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp)
//            ),
//        shape = RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp),
//    ) {
//        Box(
//            modifier = Modifier
//                .background(
//                    Brush.linearGradient(
//                        colors = listOf(Color.LightGray, color),
//                        start = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY),
//                        end = Offset(-400f, 0f)
//                    )
//                )
//        )
//    }

    Card(
        modifier = modifier
            .fillMaxWidth(0.7f)
            .height(40.dp),
        shape = RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp),
        border = BorderStroke(width = 1.dp, color = Color.Black),
        elevation = 8.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(mBackgroundBlackSecondary, color.copy(alpha = 0.8f)),
                        start = Offset(200f, Float.POSITIVE_INFINITY),
                        end = Offset(0f, 0f)
                    )
                ),
        )
    }

}