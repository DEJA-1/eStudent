package com.example.estudent.presentation.screen.add

import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.estudent.R
import com.example.estudent.common.TEST_TAG_ADD_SCREEN
import com.example.estudent.presentation.screen.common_components.BackgroundIcon
import com.example.estudent.presentation.screen.common_components.TextInputField
import com.example.estudent.presentation.viewModel.AddViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddScreen(
    viewModel: AddViewModel
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val inputFieldDutyState by viewModel.inputState.collectAsState()

//
//    val title = remember {
//        mutableStateOf("")
//    }
//
//    val description = remember {
//        mutableStateOf("")
//    }
//
//    val category = remember {
//        mutableStateOf("")
//    }
//
//    val deadline = remember {
//        mutableStateOf("")
//    }
//
//
//    val characterCounterTitle = remember {
//        mutableStateOf(0)
//    }
//
//    val characterCounterDescription = remember {
//        mutableStateOf(0)
//    }
//
//    val characterCounterCategory = remember {
//        mutableStateOf(0)
//    }
//
//    val characterCounterDeadline = remember {
//        mutableStateOf(0)
//    }

    val maxTextLength = 40

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
            Column(
                modifier = Modifier.fillMaxWidth(0.8f),
                verticalArrangement = Arrangement.Center
            ) {


                InputEntry(
                    text = inputFieldDutyState.title,
                    label = "Enter title",
                    maxTextLength = inputFieldDutyState.maxTitleLength,
                    characterCounter = inputFieldDutyState.characterCounterTitle,
                    maxLines = 5,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit icon",
                            tint = Color.Gray
                        )
                    },
                    onValueChange = { text ->
                        if (text.length <= inputFieldDutyState.maxTitleLength) {
                            viewModel.updateInputStateTitle(text)
                            viewModel.updateInputStateCharacterCounterTitle(text.length)
                        }
                    }
                ) {
                    keyboardController?.hide()
                }

                InputEntry(
                    text = inputFieldDutyState.description,
                    label = "Enter description",
                    maxTextLength = inputFieldDutyState.maxDescriptionLength,
                    characterCounter = inputFieldDutyState.characterCounterDescription,
                    maxLines = 5,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = "Edit icon",
                            tint = Color.Gray
                        )
                    },
                    onValueChange = { text ->
                        if (text.length <= inputFieldDutyState.maxDescriptionLength) {
                            viewModel.updateInputStateDescription(text)
                            viewModel.updateInputStateCharacterCounterDescription(text.length)

                        }
                    }
                ) {
                    keyboardController?.hide()
                }

                InputEntry(
                    text = inputFieldDutyState.category,
                    label = "Enter category",
                    maxTextLength = inputFieldDutyState.maxCategoryLength,
                    characterCounter = inputFieldDutyState.characterCounterCategory,
                    maxLines = 5,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Edit icon",
                            tint = Color.Gray
                        )
                    },
                    onValueChange = { text ->
                        if (text.length <= inputFieldDutyState.maxCategoryLength) {
                            viewModel.updateInputStateCategory(text)
                            viewModel.updateInputStateCharacterCounterCategory(text.length)
                        }
                    }
                ) {
                    keyboardController?.hide()
                }
//
                InputEntry(
                    text = inputFieldDutyState.deadline,
                    label = "Enter deadline",
                    maxTextLength = inputFieldDutyState.maxDeadlineLength,
                    characterCounter = inputFieldDutyState.characterCounterDeadline,
                    maxLines = 5,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Edit icon",
                            tint = Color.Gray
                        )
                    },
                    onValueChange = { text ->
                        if (text.length <= inputFieldDutyState.maxDeadlineLength) {
                            viewModel.updateInputStateDeadline(text)
                            viewModel.updateInputStateCharacterCounterDeadline(text.length)
                        }
                    }
                ) {
                    keyboardController?.hide()
                }
            }
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
    characterCounter: Int,
    leadingIcon: @Composable () -> Unit,
    onValueChange: (String) -> Unit,
    hideKeyboardController: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextInputField(
            text = text,
            label = label,
            maxLines = maxLines,
            maxLength = maxTextLength,
            imeAction = ImeAction.Done,
            onAction = KeyboardActions {
                hideKeyboardController()
            },
            onValueChange = { text -> onValueChange(text) },
            leadingIcon = leadingIcon
        )
        Box(
            modifier = Modifier.fillMaxWidth(0.8f),
            contentAlignment = Alignment.BottomEnd
        ) {
            Text(
                text = "${characterCounter}/$maxTextLength",
                color = Color.Gray,
                fontSize = 10.sp,
                textAlign = TextAlign.End
            )
        }
    }
}