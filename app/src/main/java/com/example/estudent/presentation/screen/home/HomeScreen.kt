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

                if (uiState.duties.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(text = "No Duties!")
                    }
                } else {
                    ImportantAndUpcomingSection(duties = uiState.duties)

                    Spacer(modifier = Modifier.height(30.dp))

                    Divider(
                        modifier = Modifier.padding(8.dp),
                        color = MaterialTheme.colors.onBackground,
                        thickness = 2.dp
                    )

                    RecentSection(
                        duties = uiState.duties,
                        onCheckClicked = { duty ->
                            viewModel.updateDutyIsCompleted(duty)
                        })
                }
            }
        }
    }
}

