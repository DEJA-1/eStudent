package com.example.estudent.presentation.screen.home

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.input.key.Key.Companion.H
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.filters.SmallTest
import com.example.estudent.common.TEST_TAG_DUTY_ROW
import com.example.estudent.domain.model.Duty
import com.example.estudent.ui.theme.HomeScreenColorPaletteLight
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            MaterialTheme(colors = HomeScreenColorPaletteLight) {
                HomeScreen(
                    viewModel = hiltViewModel()
                )
            }
        }
    }


    // TODO
    @Test
    fun homeScreen_dutyRowExpansion() {
        val duty = Duty(
            title = "",
            description = "test",
            deadline = "",
            category = ""
        )

        composeTestRule.onNodeWithTag(TEST_TAG_DUTY_ROW).performClick()
        composeTestRule.onNodeWithTag(TEST_TAG_DUTY_ROW, useUnmergedTree = true)
            .onChildren()
            .filter(hasText(duty.description))
    }

}