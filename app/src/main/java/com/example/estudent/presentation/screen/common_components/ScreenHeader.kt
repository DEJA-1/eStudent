package com.example.estudent.presentation.screen.common_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ScreenHeader(
    modifier: Modifier = Modifier,
    onProfileClicked: () -> Unit,
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        NicknameText()

        ProfileIcon(onProfileClicked)


    }

}

@Composable
fun NicknameText(
    nickname: String = "DEJA"
) {
    Text(
        text = nickname,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.h3,
        color = MaterialTheme.colors.onBackground
    )
}