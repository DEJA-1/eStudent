package com.example.estudent.presentation.screen.common_components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource


@Composable
fun BackgroundIcon(
    icon: Int,
    contentDescription: String,
) {
    Icon(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(icon),
        contentDescription = contentDescription,
        tint = MaterialTheme.colors.surface
    )
}