package com.example.estudent.presentation.screen.home

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.filters.SmallTest
import com.example.estudent.common.TEST_TAG_DUTY_ROW
import com.example.estudent.presentation.screen.common_components.Duty
import com.example.estudent.ui.theme.HomeScreenColorPaletteLight
import com.google.common.truth.Truth.assertThat
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
                HomeScreen()
            }
        }
    }


    // TODO
    @Test
    fun homeScreen_dutyRowExpansion() {
        val duty = Duty(
            dutyTitle = "",
            dutyDescription = "test",
            dutyDeadline = "",
            dutyCategory = ""
        )

        composeTestRule.onNodeWithTag(TEST_TAG_DUTY_ROW).performClick()
        composeTestRule.onNodeWithTag(TEST_TAG_DUTY_ROW, useUnmergedTree = true)
            .onChildren()
            .filter(hasText(duty.dutyDescription))
    }

}