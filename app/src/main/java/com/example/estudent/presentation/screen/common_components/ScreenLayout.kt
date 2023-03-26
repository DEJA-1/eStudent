package com.example.estudent.presentation.screen.common_components

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.estudent.R
import com.example.estudent.domain.model.Duty
import com.example.estudent.presentation.screen.home.components.DutiesColumn
import com.example.estudent.ui.theme.mGreen

@Composable
fun ScreenLayout(
    duties: List<Duty>,
    backgroundIcon: Int,
    backgroundIconContentDescription: String,
    onCheckClicked: (Duty) -> Unit,
) {
    BackgroundIcon(
        icon = backgroundIcon,
        contentDescription = backgroundIconContentDescription
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        ScreenHeader(
            modifier = Modifier.fillMaxWidth()
        ) {
            //TODO ProfileScreen
        }

        Spacer(modifier = Modifier.height(40.dp))

        DutyRowMain(
            duty = duties[0], onCheckClicked = { duty ->
                onCheckClicked(duty)
            },
            modifier = Modifier.heightIn(min = 90.dp),
            fontSizeTitleSp = 24,
            fontSizeDescriptionSp = 18
        )

        Spacer(modifier = Modifier.height(30.dp))

        Divider(
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colors.onBackground,
            thickness = 2.dp
        )

        Spacer(modifier = Modifier.height(30.dp))

        DutiesColumn(duties = duties,
            content = { duty ->
                DutyRowExpanded(
                    duty = duty, onCheckClicked = { onCheckClicked(duty) },
                    minHeightDp = 80,
                    circleSizeDp = 52,
                    fontSizeTitleSp = 20,
                    fontSizeDeadlineSp = 16
                )
            })
    }
}