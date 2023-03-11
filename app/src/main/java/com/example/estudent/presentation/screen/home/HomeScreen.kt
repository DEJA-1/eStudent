package com.example.estudent.presentation.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.estudent.common.TEST_TAG_HOME_SCREEN
import com.example.estudent.domain.model.Duty
import com.example.estudent.presentation.screen.home.components.DutiesColumn
import com.example.estudent.presentation.screen.home.components.HomeHeader
import com.example.estudent.presentation.screen.home.components.ImportantAndUpcomingSection
import com.example.estudent.ui.theme.HomeScreenColorPaletteLight

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {

    val duty = Duty(
        title = "Object oriented programming assignment",
        description = "Wykonac zadanie 2 z listy laboratorium 2",
        category = "Project",
        importance = "Important",
        deadline = "25.03.23"
    )

    val duty1 = Duty(
        title = "Object oriented programming assignment",
        description = "Wykonac zadanie 2 z listy laboratorium 2",
        category = "Task",
        importance = "Moderate",
        deadline = "12.03.23"
    )

    val duty2 = Duty(
        title = "Object oriented programming assignment",
        description = "Wykonac zadanie 2 z listy laboratorium 2",
        category = "Exam",
        importance = "Minor",
        deadline = "15.03.23"
    )

    MaterialTheme(colors = HomeScreenColorPaletteLight) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .testTag(TEST_TAG_HOME_SCREEN)
        ) {
            HomeHeader(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 8.dp),
                duty = duty1
            )

            Spacer(modifier = Modifier.height(30.dp))

            ImportantAndUpcomingSection(duty, duty1, duty2)

            Spacer(modifier = Modifier.height(30.dp))

            Divider(
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colors.onBackground,
                thickness = 2.dp
            )

//            RecentSection()

            Column(
                modifier = Modifier.padding(8.dp),
            ) {

                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = "Recent",
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 32.sp,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Start
                )

                DutiesColumn(
                    duties = listOf(duty, duty1, duty2), minHeightDp = 80,
                    modifier = Modifier.fillMaxWidth()
                )
            }

        }

    }
}



