package com.example.estudent.presentation.screen.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.estudent.R
import com.example.estudent.common.TEST_TAG_HOME_SCREEN
import com.example.estudent.domain.model.Duty
import com.example.estudent.presentation.screen.common_components.BackgroundIcon
import com.example.estudent.presentation.screen.common_components.LoadingComponent
import com.example.estudent.presentation.screen.home.components.HomeHeader
import com.example.estudent.presentation.screen.home.components.ImportantAndUpcomingSection
import com.example.estudent.presentation.screen.home.components.RecentSection
import com.example.estudent.presentation.state.DutyUiState
import com.example.estudent.presentation.viewModel.HomeViewModel
import com.example.estudent.ui.theme.HomeScreenColorPaletteLight
import com.example.estudent.ui.theme.mGreen
import com.example.estudent.ui.theme.mYellow

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    navigateToAddScreen: () -> Unit
) {

    val context = LocalContext.current

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

                if (uiState.isLoading) {
                    LoadingComponent()
                } else {
                    HomeScreenContent(uiState, viewModel, context) {
                        navigateToAddScreen()
                    }
                }

            }
        }
    }
}

@Composable
private fun HomeScreenContent(
    uiState: DutyUiState,
    viewModel: HomeViewModel,
    context: Context,
    navigateToAddScreen: () -> Unit
) {

    val importantDuties = uiState.duties.filter { it.importance == "Important" }.take(4)
    val upcomingDuties = uiState.duties.sortedBy { it.deadline }.take(3).dropWhile { !it.hasDeadline }
    val recentDuties = uiState.duties.sortedBy { it.addedDate }.take(4)

    if (uiState.duties.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {

            Text(
                text = "Add your first duty!",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center
            )

            Card(
                modifier = Modifier
                    .clip(CircleShape)
                    .fillMaxWidth(0.5f)
                    .padding(vertical = 4.dp)
                    .clickable {
                        navigateToAddScreen()
                    },
                shape = RoundedCornerShape(16.dp),
                backgroundColor = mYellow,
                elevation = 4.dp
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 20.dp, horizontal = 30.dp),
                    text = "ADD DUTY",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center
                )
            }
        }
    } else {
        ImportantAndUpcomingSection(
            importantDuties = importantDuties,
            upcomingDuties = upcomingDuties
        )

        Spacer(modifier = Modifier.height(30.dp))

        Divider(
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colors.onBackground,
            thickness = 2.dp
        )

        RecentSection(
            duties = recentDuties,
            onCheckClicked = { duty ->
                viewModel.updateDutyIsCompleted(duty)
            },
            deleteDuty = { duty ->
                viewModel.deleteDuty(duty)
                Toast.makeText(context, "Duty deleted", Toast.LENGTH_SHORT).show()
            })
    }
}



