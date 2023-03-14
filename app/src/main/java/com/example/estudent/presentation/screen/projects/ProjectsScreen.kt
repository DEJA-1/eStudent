package com.example.estudent.presentation.screen.projects

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.estudent.R
import com.example.estudent.common.TEST_TAG_HOME_SCREEN
import com.example.estudent.common.TEST_TAG_PROJECTS_SCREEN
import com.example.estudent.domain.model.Duty
import com.example.estudent.presentation.screen.common_components.DutyRowExpanded
import com.example.estudent.presentation.screen.common_components.DutyRowMain
import com.example.estudent.presentation.screen.common_components.DutyRowShrank
import com.example.estudent.presentation.screen.common_components.ScreenHeader
import com.example.estudent.presentation.screen.home.components.DutiesColumn
import com.example.estudent.presentation.screen.home.components.DutiesSection

@Composable
fun ProjectsScreen(
    viewModel: ProjectsViewModel,
) {

    val duty1 = Duty(
        title = "Object oriented programming assignment",
        description = "Wykonac zadanie 2 z listy laboratorium 2",
        category = "Task",
        importance = "Moderate",
        deadline = "12.03.2023",
        isCompleted = false
    )

    val duty2 = Duty(
        title = "Object oriented programming assignment",
        description = "Wykonac zadanie 2 z listy laboratorium 2",
        category = "Task",
        importance = "Moderate",
        deadline = "22.03.2023",
        isCompleted = false
    )

    val duty3 = Duty(
        title = "Object oriented programming assignment",
        description = "Wykonac zadanie 2 z listy laboratorium 2",
        category = "Task",
        importance = "Moderate",
        deadline = "18.03.2023",
        isCompleted = false
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(bottom = 50.dp)
            .testTag(TEST_TAG_PROJECTS_SCREEN),
        contentAlignment = Alignment.Center
    ) {

        Icon(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.projects),
            contentDescription = "Home icon",
            tint = MaterialTheme.colors.surface
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

            DutyRowMain(duty = duty1, onCheckClicked = { duty ->
                viewModel.updateDutyIsCompleted(duty)
            },
            modifier = Modifier.heightIn(min = 80.dp))

            Spacer(modifier = Modifier.height(30.dp))

            Divider(
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colors.onBackground,
                thickness = 2.dp
            )

            Spacer(modifier = Modifier.height(30.dp))

            DutiesColumn(duties = listOf(duty1, duty2, duty2, duty3),
                content = { duty ->
                        DutyRowExpanded(duty = duty, onCheckClicked = { viewModel.updateDutyIsCompleted(duty) },
                        modifier = Modifier.fillMaxHeight(0.4f))
                })
        }
    }
}