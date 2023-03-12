package com.example.estudent.presentation.screen.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.estudent.R

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    onProfileClicked: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        GreetingText()

        Icon(
            modifier = Modifier
                .clip(CircleShape)
                .size(64.dp)
                .clickable {
                    onProfileClicked()
                },
            imageVector = Icons.Default.AccountCircle, contentDescription = "Profile icon",
            tint = MaterialTheme.colors.onBackground
        )
    }
}

@Composable
fun GreetingText() {
    Column() {
        Text(
            text = stringResource(R.string.hello_string),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6
        )

        Text(
            text = stringResource(R.string.you_got_this_string),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Light
        )
    }
}
