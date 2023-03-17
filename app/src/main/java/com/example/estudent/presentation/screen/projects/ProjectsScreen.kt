package com.example.estudent.presentation.screen.projects

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.estudent.common.TEST_TAG_PROJECTS_SCREEN
import com.example.estudent.presentation.screen.common_components.ScreenLayout
import com.example.estudent.presentation.viewModel.DutyViewModel

@Composable
fun ProjectsScreen(
    viewModel: DutyViewModel,
) {

    val duties = viewModel.duties

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(bottom = 60.dp)
            .testTag(TEST_TAG_PROJECTS_SCREEN),
        contentAlignment = Alignment.Center
    ) {

        ScreenLayout(
            duties = duties,
            backgroundIcon = com.example.estudent.R.drawable.projects,
            backgroundIconContentDescription = "Projects Icon",
            onCheckClicked = { duty -> viewModel.updateDutyIsCompleted(duty) },
        )
    }
}


