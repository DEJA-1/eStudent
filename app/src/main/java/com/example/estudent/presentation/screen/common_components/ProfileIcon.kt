package com.example.estudent.presentation.screen.common_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ProfileIcon(onProfileClicked: () -> Unit) {
    Icon(
        modifier = Modifier
            .clip(CircleShape)
            .size(58.dp)
            .clickable {
                onProfileClicked()
            },
        imageVector = Icons.Default.AccountCircle, contentDescription = "Profile icon",
        tint = MaterialTheme.colors.onBackground
    )
}