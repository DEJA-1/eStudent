package com.example.estudent.presentation.screen.projects

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.estudent.common.TEST_TAG_HOME_SCREEN
import com.example.estudent.common.TEST_TAG_PROJECTS_SCREEN

@Composable
fun ProjectsScreen(

) {

    Box(
        modifier = Modifier.fillMaxSize()
            .testTag(TEST_TAG_PROJECTS_SCREEN),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Projects Screen")
    }
}