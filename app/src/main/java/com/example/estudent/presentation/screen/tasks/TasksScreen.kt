package com.example.estudent.presentation.screen.tasks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.estudent.R
import com.example.estudent.common.TEST_TAG_TASKS_SCREEN
import com.example.estudent.presentation.screen.common_components.ScreenLayout
import com.example.estudent.presentation.viewModel.DutyViewModel

@Composable
fun TasksScreen(
    modifier: Modifier = Modifier,
    viewModel: DutyViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp)
            .testTag(TEST_TAG_TASKS_SCREEN),
        contentAlignment = Alignment.Center
    ) {
        ScreenLayout(
            duties = uiState.duties,
            backgroundIcon = R.drawable.tasks,
            backgroundIconContentDescription = "Tasks Icon",
            onCheckClicked = { duty -> viewModel.updateDutyIsCompleted(duty = duty) },
            deleteDuty = { duty -> viewModel.deleteDuty(duty) }
        )
    }

}