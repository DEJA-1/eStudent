package com.example.estudent.presentation.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.estudent.R
import com.example.estudent.common.TEST_TAG_HOME_SCREEN
import com.example.estudent.domain.model.Duty
import com.example.estudent.presentation.screen.common_components.DutyRowExpanded
import com.example.estudent.presentation.screen.home.components.DutiesColumn
import com.example.estudent.presentation.screen.home.components.HomeHeader
import com.example.estudent.presentation.screen.home.components.ImportantAndUpcomingSection
import com.example.estudent.presentation.screen.home.components.RecentSection
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
        deadline = "17.03.2023"
    )

    MaterialTheme(colors = HomeScreenColorPaletteLight) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(bottom = 50.dp),
            contentAlignment = Center
        ) {

            Icon(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.baseline_home_24),
                contentDescription = "Home icon",
                tint = MaterialTheme.colors.surface
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

                Spacer(modifier = Modifier.height(40.dp))

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

