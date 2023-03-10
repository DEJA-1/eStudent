package com.example.estudent.presentation.screen.exams

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.estudent.common.TEST_TAG_EXAMS_SCREEN
import com.example.estudent.common.TEST_TAG_TASKS_SCREEN

@Composable
fun ExamsScreen(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = Modifier.fillMaxSize()
            .testTag(TEST_TAG_EXAMS_SCREEN),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Exams Screen")
    }
}