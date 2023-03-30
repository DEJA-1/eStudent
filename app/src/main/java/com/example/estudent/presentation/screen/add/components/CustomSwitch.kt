package com.example.estudent.presentation.screen.add.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomSwitch(
    isChecked: MutableState<Boolean>,
    onChange: () -> Unit,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier
            .size(70.dp, 40.dp),
        shape = RoundedCornerShape(18.dp),
        color = MaterialTheme.colors.background,
        border = BorderStroke(2.dp, MaterialTheme.colors.onBackground),
        elevation = 4.dp,
    ) {
        Switch(
            checked = isChecked.value,
            onCheckedChange = { onChange() },
            colors = SwitchDefaults.colors(
                uncheckedTrackColor = MaterialTheme.colors.background,
                uncheckedThumbColor = MaterialTheme.colors.onBackground,
                checkedTrackColor = MaterialTheme.colors.primary,
                checkedThumbColor = MaterialTheme.colors.primary
            )
        )

    }

}