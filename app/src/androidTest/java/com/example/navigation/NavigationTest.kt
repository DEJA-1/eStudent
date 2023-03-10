package com.example.navigation

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.testing.TestNavHostController
import androidx.test.filters.MediumTest
import com.example.estudent.MainActivity
import com.example.estudent.MyApp
import com.example.estudent.common.*
import com.example.estudent.navigation.MyNavHost
import com.example.estudent.navigation.Screen
import com.example.estudent.ui.theme.EStudentTheme
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@MediumTest
class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        composeTestRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            EStudentTheme {
                MyApp()
            }
        }
    }

    @Test
    fun navigation_verifyStartDestination() {
        val startDestination = TEST_TAG_HOME_SCREEN
        composeTestRule.onNodeWithTag(startDestination).assertIsDisplayed()
    }

    @Test
    fun navigation_navigateToProjectsScreen() {
        composeTestRule.onNodeWithContentDescription("Navigation icon Projects").performClick()
        composeTestRule.onNodeWithTag(TEST_TAG_PROJECTS_SCREEN).assertIsDisplayed()
    }

    @Test
    fun navigation_navigateToTasksScreen() {
        composeTestRule.onNodeWithContentDescription("Navigation icon Tasks").performClick()
        composeTestRule.onNodeWithTag(TEST_TAG_TASKS_SCREEN).assertIsDisplayed()
    }

    @Test
    fun navigation_navigateToExamsScreen() {
        composeTestRule.onNodeWithContentDescription("Navigation icon Exams").performClick()
        composeTestRule.onNodeWithTag(TEST_TAG_EXAMS_SCREEN).assertIsDisplayed()
    }

    @Test
    fun navigation_navigateToHomeScreen() {
        composeTestRule.onNodeWithContentDescription("Navigation icon Home").performClick()
        composeTestRule.onNodeWithTag(TEST_TAG_HOME_SCREEN).assertIsDisplayed()
    }
}