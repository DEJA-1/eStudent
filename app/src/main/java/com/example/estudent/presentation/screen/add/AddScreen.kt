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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddScreen() {

    val keyboardController = LocalSoftwareKeyboardController.current

    val title = remember {
        mutableStateOf("")
    }

    val description = remember {
        mutableStateOf("")
    }

    val category = remember {
        mutableStateOf("")
    }

    val deadline = remember {
        mutableStateOf("")
    }


    val characterCounter = remember {
        mutableStateOf(0)
    }
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
                    text = title,
                    label = "Enter title",
                    maxTextLength = maxTextLength,
                    characterCounter = characterCounter,
                    maxLines = 5,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit icon",
                            tint = Color.Gray
                        )
                    }
                ) {
                    keyboardController?.hide()
                }

                InputEntry(
                    text = description,
                    label = "Enter description",
                    maxTextLength = 70,
                    characterCounter = characterCounter,
                    maxLines = 5,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = "Edit icon",
                            tint = Color.Gray
                        )
                    }
                ) {
                    keyboardController?.hide()
                }

                InputEntry(
                    text = category,
                    label = "Enter category",
                    maxTextLength = 10,
                    characterCounter = characterCounter,
                    maxLines = 5,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Edit icon",
                            tint = Color.Gray
                        )
                    }
                ) {
                    keyboardController?.hide()
                }

                InputEntry(
                    text = deadline,
                    label = "Enter deadline",
                    maxTextLength = 20,
                    characterCounter = characterCounter,
                    maxLines = 5,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Edit icon",
                            tint = Color.Gray
                        )
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
    text: MutableState<String>,
    label: String,
    maxTextLength: Int,
    maxLines: Int,
    characterCounter: MutableState<Int>,
    leadingIcon: @Composable () -> Unit,
    hideKeyboardController: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextInputField(
            textState = text,
            label = label,
            maxLines = maxLines,
            maxLength = maxTextLength,
            characterCounter = characterCounter,
            imeAction = ImeAction.Done,
            onAction = KeyboardActions {
                hideKeyboardController()
            },
            leadingIcon = leadingIcon
        )
        Box(
            modifier = Modifier.fillMaxWidth(0.8f),
            contentAlignment = Alignment.BottomEnd
        ) {
            Text(
                text = "${characterCounter.value}/$maxTextLength",
                color = Color.Gray,
                fontSize = 10.sp,
                textAlign = TextAlign.End
            )
        }
    }
}