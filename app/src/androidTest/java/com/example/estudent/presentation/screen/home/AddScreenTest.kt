package com.example.estudent.presentation.screen.home

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.estudent.presentation.screen.add.AddScreen
import com.example.estudent.presentation.viewModel.AddViewModel
import com.example.estudent.ui.theme.HomeScreenColorPaletteLight
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: AddViewModel

    @Before
    fun setUp() {
        viewModel = AddViewModel()

        composeTestRule.setContent {
            MaterialTheme(colors = HomeScreenColorPaletteLight) {
                AddScreen(
                    viewModel = viewModel
                )
            }
        }
    }

    @Test
    fun addScreen_testTitleInput() {
        val input = "Zadanie"
        composeTestRule.onNodeWithText("Enter title").performTextInput(input)
        val inputState = viewModel.inputState
        assertThat(inputState.value.title).isEqualTo(input)
    }

    @Test
    fun addScreen_testTitleCharacterCounter() {
        val input = "Title"
        composeTestRule.onNodeWithText("Enter title").performTextInput(input)
        val inputState = viewModel.inputState
        assertThat(inputState.value.characterCounterTitle).isEqualTo(input.length)
    }

    @Test
    fun addScreen_testDescriptionInput() {
        val input = "Description"
        composeTestRule.onNodeWithText("Enter description").performTextInput(input)
        val inputState = viewModel.inputState
        assertThat(inputState.value.description).isEqualTo(input)
    }

    @Test
    fun addScreen_testDescriptionCharacterCounter() {
        val input = "Description"
        composeTestRule.onNodeWithText("Enter description").performTextInput(input)
        val inputState = viewModel.inputState
        assertThat(inputState.value.characterCounterDescription).isEqualTo(input.length)
    }

    @Test
    fun addScreen_testCategoryInput() {
        val input = "Category"
        composeTestRule.onNodeWithText("Enter category").performTextInput(input)
        val inputState = viewModel.inputState
        assertThat(inputState.value.category).isEqualTo(input)
    }

    @Test
    fun addScreen_testCategoryCharacterCounter() {
        val input = "Category"
        composeTestRule.onNodeWithText("Enter category").performTextInput(input)
        val inputState = viewModel.inputState
        assertThat(inputState.value.characterCounterCategory).isEqualTo(input.length)
    }

    @Test
    fun addScreen_testDeadlineInput() {
        val input = "Deadline"
        composeTestRule.onNodeWithText("Enter deadline").performTextInput(input)
        val inputState = viewModel.inputState
        assertThat(inputState.value.deadline).isEqualTo(input)
    }

    @Test
    fun addScreen_testDeadlineCharacterCounter() {
        val input = "Deadline"
        composeTestRule.onNodeWithText("Enter deadline").performTextInput(input)
        val inputState = viewModel.inputState
        assertThat(inputState.value.characterCounterDeadline).isEqualTo(input.length)
    }
}