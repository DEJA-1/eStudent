package com.example.estudent.presentation.screen.home

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.input.key.Key.Companion.H
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.filters.SmallTest
import com.example.estudent.common.TEST_TAG_DUTY_ROW
import com.example.estudent.domain.model.Duty
import com.example.estudent.ui.theme.HomeScreenColorPaletteLight
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@SmallTest
class HomeScreenTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            MaterialTheme(colors = HomeScreenColorPaletteLight) {
                HomeScreen(
                    viewModel = hiltViewModel(),
                    navigateToAddScreen = { }
                )
            }
        }
    }

}