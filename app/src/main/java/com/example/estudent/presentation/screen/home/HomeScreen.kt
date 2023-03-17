package com.example.estudent.presentation.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.estudent.R
import com.example.estudent.common.TEST_TAG_HOME_SCREEN
import com.example.estudent.domain.model.Duty
import com.example.estudent.presentation.screen.common_components.BackgroundIcon
import com.example.estudent.presentation.screen.home.components.HomeHeader
import com.example.estudent.presentation.screen.home.components.ImportantAndUpcomingSection
import com.example.estudent.presentation.screen.home.components.RecentSection
import com.example.estudent.presentation.viewModel.HomeViewModel
import com.example.estudent.ui.theme.HomeScreenColorPaletteLight

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
) {

    val uiState by viewModel.uiState.collectAsState()

    val duty = Duty(
        title = "Object oriented programming assignment",
        description = "Wykonac zadanie 2 z listy laboratorium 2",
        category = "Project",
        importance = "Important",
        deadline = "25.03.2023"
    )

    val duty1 = Duty(
        title = "Object oriented programming assignment",
        description = "Wykonac zadanie 2 z listy laboratorium 2",
        category = "Task",
        importance = "Moderate",
        deadline = "12.03.2023",
        isCompleted = true
    )

    val duty2 = Duty(
        title = "Object oriented programming assignment",
        description = "Wykonac zadanie 2 z listy laboratorium 2",
        category = "Exam",
        importance = "Minor",
        deadline = "18.03.2023"
    )

    MaterialTheme(colors = HomeScreenColorPaletteLight) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(bottom = 60.dp),
            contentAlignment = Center
        ) {

            BackgroundIcon(
                icon = R.drawable.baseline_home_24,
                contentDescription = "Home Icon"
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .testTag(TEST_TAG_HOME_SCREEN),
            ) {

                HomeHeader(modifier = modifier.fillMaxWidth(), onProfileClicked = {
                    // TODO OnProfileClicked
                })

                Spacer(modifier = Modifier.height(30.dp))

                ImportantAndUpcomingSection(duty, duty1, duty2)

                Spacer(modifier = Modifier.height(30.dp))

                Divider(
                    modifier = Modifier.padding(8.dp),
                    color = MaterialTheme.colors.onBackground,
                    thickness = 2.dp
                )

                RecentSection(duty, duty1, duty2,
                    onCheckClicked = { duty ->
                        viewModel.updateDutyIsCompleted(duty)
                    })

            }
        }
    }
}

