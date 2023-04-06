package com.example.estudent.presentation.screen.home

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.estudent.common.TEST_TAG_CATEGORY_INPUT
import com.example.estudent.common.TEST_TAG_DESCRIPTION_INPUT
import com.example.estudent.common.TEST_TAG_TITLE_INPUT
import com.example.estudent.data.repository.FakeEStudentDatabaseRepositoryImpl
import com.example.estudent.domain.repository.EStudentDatabaseRepository
import com.example.estudent.presentation.screen.add.AddScreen
import com.example.estudent.presentation.viewModel.AddViewModel
import com.example.estudent.presentation.viewModel.InputTextFieldViewModel
import com.example.estudent.ui.theme.HomeScreenColorPaletteLight
import com.example.estudent.ui.theme.mYellow
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class AddScreenTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var fakeRepository: EStudentDatabaseRepository
    private lateinit var viewModel: AddViewModel
    private lateinit var inputTextFieldViewModel: InputTextFieldViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        fakeRepository = FakeEStudentDatabaseRepositoryImpl()
        viewModel = AddViewModel(fakeRepository)
        inputTextFieldViewModel = InputTextFieldViewModel()

        composeTestRule.setContent {
            MaterialTheme(colors = HomeScreenColorPaletteLight) {
                AddScreen(
                    addViewModel = viewModel,
                    inputTextFieldViewModel = inputTextFieldViewModel
                )
            }
        }
    }

    @Test
    fun addScreen_testTitleInput() {
        val input = "Title"
        composeTestRule.onNodeWithTag(TEST_TAG_TITLE_INPUT).performTextInput(input)
        composeTestRule.onNodeWithTag(TEST_TAG_TITLE_INPUT, useUnmergedTree = true).assertTextEquals(input)
    }

    @Test
    fun addScreen_testTitleCharacterCounter() {
        val input = "Title"
        val textFieldState = inputTextFieldViewModel.textFieldState.value
        composeTestRule.onNodeWithTag(TEST_TAG_TITLE_INPUT).performTextInput(input)
        composeTestRule.onNodeWithTag("$TEST_TAG_TITLE_INPUT counter", useUnmergedTree = true)
            .assertTextEquals("${input.length}/${textFieldState.maxTitleLength}")
    }
    @Test
    fun addScreen_testClearTitle() {
        val input = "Title"
        val label = "Enter title"
        composeTestRule.onNodeWithTag(TEST_TAG_TITLE_INPUT).performTextInput(input)
        composeTestRule.onNodeWithContentDescription("Cancel icon $label").performClick()
        composeTestRule.onNodeWithTag(TEST_TAG_TITLE_INPUT, useUnmergedTree = true).assertTextEquals("")
    }

    @Test
    fun addScreen_testDescriptionInput() {
        val input = "Description"
        composeTestRule.onNodeWithTag(TEST_TAG_DESCRIPTION_INPUT).performTextInput(input)
        composeTestRule.onNodeWithTag(TEST_TAG_DESCRIPTION_INPUT, useUnmergedTree = true).assertTextEquals(input)
    }

    @Test
    fun addScreen_testDescriptionCharacterCounter() {
        val input = "Description"
        val textFieldState = inputTextFieldViewModel.textFieldState.value
        composeTestRule.onNodeWithTag(TEST_TAG_DESCRIPTION_INPUT).performTextInput(input)
        composeTestRule.onNodeWithTag("$TEST_TAG_DESCRIPTION_INPUT counter", useUnmergedTree = true)
            .assertTextEquals("${input.length}/${textFieldState.maxDescriptionLength}")
    }
    @Test
    fun addScreen_testClearDescription() {
        val input = "Description"
        val label = "Enter description"
        composeTestRule.onNodeWithTag(TEST_TAG_DESCRIPTION_INPUT).performTextInput(input)
        composeTestRule.onNodeWithContentDescription("Cancel icon $label").performClick()
        composeTestRule.onNodeWithTag(TEST_TAG_DESCRIPTION_INPUT, useUnmergedTree = true).assertTextEquals("")
    }

    //TODO TEST CATEGORY INPUT

    // TODO TEST DEADLINE INPUT

}