package com.example.estudent.presentation.screen.home

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.estudent.data.repository.FakeEStudentDatabaseRepositoryImpl
import com.example.estudent.common.TEST_TAG_CATEGORY_INPUT
import com.example.estudent.common.TEST_TAG_DEADLINE_INPUT
import com.example.estudent.common.TEST_TAG_DESCRIPTION_INPUT
import com.example.estudent.common.TEST_TAG_TITLE_INPUT
import com.example.estudent.data.repository.EStudentDatabaseRepositoryImpl
import com.example.estudent.domain.repository.EStudentDatabaseRepository
import com.example.estudent.presentation.screen.add.AddScreen
import com.example.estudent.presentation.viewModel.AddViewModel
import com.example.estudent.presentation.viewModel.InputTextFieldViewModel
import com.example.estudent.ui.theme.HomeScreenColorPaletteLight
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

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
                    inputTextFieldViewModel = InputTextFieldViewModel()
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

    @Test
    fun addScreen_testCategoryInput() {
        val input = "Category"
        composeTestRule.onNodeWithTag(TEST_TAG_CATEGORY_INPUT).performTextInput(input)
        composeTestRule.onNodeWithTag(TEST_TAG_CATEGORY_INPUT, useUnmergedTree = true).assertTextEquals(input)
    }

    @Test
    fun addScreen_testCategoryCharacterCounter() {
        val input = "Category"
        val textFieldState = inputTextFieldViewModel.textFieldState.value
        composeTestRule.onNodeWithTag(TEST_TAG_CATEGORY_INPUT).performTextInput(input)
        composeTestRule.onNodeWithTag("$TEST_TAG_CATEGORY_INPUT counter", useUnmergedTree = true)
            .assertTextEquals("${input.length}/${textFieldState.maxCategoryLength}")
    }
    @Test
    fun addScreen_testClearCategory() {
        val input = "Category"
        val label = "Enter category"
        composeTestRule.onNodeWithTag(TEST_TAG_CATEGORY_INPUT).performTextInput(input)
        composeTestRule.onNodeWithContentDescription("Cancel icon $label").performClick()
        composeTestRule.onNodeWithTag(TEST_TAG_CATEGORY_INPUT, useUnmergedTree = true).assertTextEquals("")
    }

    @Test
    fun addScreen_testDeadlineInput() {
        val input = "Deadline"
        composeTestRule.onNodeWithTag(TEST_TAG_DEADLINE_INPUT).performTextInput(input)
        composeTestRule.onNodeWithTag(TEST_TAG_DEADLINE_INPUT, useUnmergedTree = true).assertTextEquals(input)
    }

    @Test
    fun addScreen_testDeadlineCharacterCounter() {
        val input = "Deadline"
        val textFieldState = inputTextFieldViewModel.textFieldState.value
        composeTestRule.onNodeWithTag(TEST_TAG_DEADLINE_INPUT).performTextInput(input)
        composeTestRule.onNodeWithTag("$TEST_TAG_DEADLINE_INPUT counter", useUnmergedTree = true)
            .assertTextEquals("${input.length}/${textFieldState.maxDeadlineLength}")
    }
    @Test
    fun addScreen_testClearDeadline() {
        val input = "Deadline"
        val label = "Enter deadline"
        composeTestRule.onNodeWithTag(TEST_TAG_DEADLINE_INPUT).performTextInput(input)
        composeTestRule.onNodeWithContentDescription("Cancel icon $label").performClick()
        composeTestRule.onNodeWithTag(TEST_TAG_DEADLINE_INPUT, useUnmergedTree = true).assertTextEquals("")
    }

}