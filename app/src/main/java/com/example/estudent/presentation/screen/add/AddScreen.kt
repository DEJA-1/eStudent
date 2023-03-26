package com.example.estudent.presentation.screen.add

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.estudent.R
import com.example.estudent.common.*
import com.example.estudent.presentation.screen.common_components.BackgroundIcon
import com.example.estudent.presentation.screen.common_components.TextInputField
import com.example.estudent.presentation.state.TextFieldState
import com.example.estudent.presentation.viewModel.AddViewModel
import com.example.estudent.presentation.viewModel.InputTextFieldViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddScreen(
    addViewModel: AddViewModel,
    inputTextFieldViewModel: InputTextFieldViewModel
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val textFieldState by inputTextFieldViewModel.textFieldState.collectAsState()
    val dutyToInsert by addViewModel.dutyToInsert.collectAsState()

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 55.dp)
            .background(MaterialTheme.colors.background)
            .testTag(TEST_TAG_ADD_SCREEN),
        contentAlignment = Alignment.Center
    ) {

        BackgroundIcon(icon = R.drawable.add, contentDescription = "Add Icon")

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            InputSection(
                modifier = Modifier.fillMaxWidth(0.8f),
                textFieldState = textFieldState,
                inputTextFieldViewModel = inputTextFieldViewModel,
                hideKeyboardController = {
                    keyboardController?.hide()
                }
            )

            SaveButton(
                errorMessage = textFieldState.errorMessage,
                onSuccess = {
                    addViewModel.insertDuty(dutyToInsert)
                    Toast.makeText(context, "Successfully added", Toast.LENGTH_SHORT).show()
                },
                onError = {
                    Toast.makeText(context, "${textFieldState.errorMessage}", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

}

@Composable
private fun SaveButton(
    errorMessage: String?,
    onSuccess: () -> Unit,
    onError: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .clickable {
                if (errorMessage == null)
                    onSuccess()
                else
                    onError()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Save",
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun InputSection(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState,
    inputTextFieldViewModel: InputTextFieldViewModel,
    hideKeyboardController: () -> Unit
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        InputEntry(
            text = textFieldState.title,
            label = "Enter title",
            maxTextLength = textFieldState.maxTitleLength,
            characterCounter = textFieldState.characterCounterTitle,
            maxLines = 5,
            isError = !inputTextFieldViewModel.isTextValid(text = textFieldState.title, errorMessage = "Invalid title."),
            testTag = TEST_TAG_TITLE_INPUT,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit icon",
                    tint = Color.Gray
                )
            },
            onClearClick = {
                inputTextFieldViewModel.updateTextFieldStateTitle(title = "", characterCounter = 0)
            },
            onValueChange = { text ->
                if (text.length <= textFieldState.maxTitleLength) {
                    inputTextFieldViewModel.updateTextFieldStateTitle(text, text.length)
                }
            }
        ) { title ->

            hideKeyboardController()
        }

        InputEntry(
            text = textFieldState.description,
            label = "Enter description",
            maxTextLength = textFieldState.maxDescriptionLength,
            characterCounter = textFieldState.characterCounterDescription,
            maxLines = 5,
            isError = !inputTextFieldViewModel.isTextValid(text = textFieldState.description, errorMessage = "Invalid description."),
            testTag = TEST_TAG_DESCRIPTION_INPUT,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = "Edit icon",
                    tint = Color.Gray
                )
            },
            onClearClick = {
                inputTextFieldViewModel.updateTextFieldStateDescription(description = "", characterCounter = 0)
            },
            onValueChange = { text ->
                if (text.length <= textFieldState.maxDescriptionLength) {
                    inputTextFieldViewModel.updateTextFieldStateDescription(text, text.length)
                }
            }
        ) { description ->
            hideKeyboardController()
        }

        InputEntry(
            text = textFieldState.category,
            label = "Enter category",
            maxTextLength = textFieldState.maxCategoryLength,
            characterCounter = textFieldState.characterCounterCategory,
            maxLines = 5,
            isError = !inputTextFieldViewModel.isTextValid(text = textFieldState.category, errorMessage = "Invalid category."),
            testTag = TEST_TAG_CATEGORY_INPUT,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Edit icon",
                    tint = Color.Gray
                )
            },
            onClearClick = {
                inputTextFieldViewModel.updateTextFieldStateCategory(category = "", characterCounter = 0)
            },
            onValueChange = { text ->
                if (text.length <= textFieldState.maxCategoryLength) {
                    inputTextFieldViewModel.updateTextFieldStateCategory(text, text.length)
                }
            }
        ) { category ->
            hideKeyboardController()
        }

        InputEntry(
            text = textFieldState.deadline,
            label = "Enter deadline",
            maxTextLength = textFieldState.maxDeadlineLength,
            characterCounter = textFieldState.characterCounterDeadline,
            maxLines = 5,
            isError = !inputTextFieldViewModel.isTextValid(text = textFieldState.deadline, errorMessage = "Invalid deadline."),
            testTag = TEST_TAG_DEADLINE_INPUT,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Edit icon"
                )
            },
            onClearClick = {
                inputTextFieldViewModel.updateTextFieldStateDeadline(deadline = "", characterCounter = 0)
            },
            onValueChange = { text ->
                if (text.length <= textFieldState.maxDeadlineLength) {
                    inputTextFieldViewModel.updateTextFieldStateDeadline(text, text.length)
                }
            }
        ) { deadline ->
            hideKeyboardController()
        }
    }
}

@Composable
private fun InputEntry(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxTextLength: Int,
    maxLines: Int,
    isError: Boolean,
    characterCounter: Int,
    leadingIcon: @Composable () -> Unit,
    testTag: String = "",
    onClearClick: () -> Unit,
    onValueChange: (String) -> Unit,
    onDoneClick: (String) -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextInputField(
            text = text,
            label = label,
            maxLines = maxLines,
            isError = isError,
            imeAction = ImeAction.Done,
            onAction = KeyboardActions {
                onDoneClick(text)
            },
            testTag = testTag,
            onClearClick = { onClearClick() },
            onValueChange = { text -> onValueChange(text) },
            leadingIcon = leadingIcon
        )
        Box(
            modifier = Modifier.fillMaxWidth(0.8f),
            contentAlignment = Alignment.BottomEnd
        ) {
            Text(
                modifier = Modifier.testTag("$testTag counter"),
                text = "${characterCounter}/$maxTextLength",
                color = Color.Gray,
                fontSize = 10.sp,
                textAlign = TextAlign.End
            )
        }
    }
}