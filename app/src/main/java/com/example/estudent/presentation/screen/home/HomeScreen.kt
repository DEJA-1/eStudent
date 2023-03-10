package com.example.estudent.presentation.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.estudent.common.TEST_TAG_HOME_SCREEN
import com.example.estudent.common.TEST_TAG_TASKS_SCREEN
import com.example.estudent.navigation.Screen
import com.example.estudent.presentation.screen.common_components.DutyRow
import com.example.estudent.ui.theme.HomeScreenColorPaletteLight
import com.example.estudent.ui.theme.mBackgroundBlack

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    MaterialTheme(colors = HomeScreenColorPaletteLight) {

        Column(
            modifier = Modifier.fillMaxSize()
                .testTag(TEST_TAG_HOME_SCREEN)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MyHeader()

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DutyRow(
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(50.dp)
                    )

                    Text(
                        text = "Due tomorrow!",
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.subtitle2,
                        textAlign = TextAlign.Center
                    )

                }
            }
        }

    }
}

@Composable
fun MyHeader() {
    Column() {
        Text(
            text = "Hello!",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6
        )

        Text(
            text = "You got this!",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Light
        )
    }
}