package com.example.estudent.presentation.screen.add

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Card
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.estudent.R
import com.example.estudent.common.*
import com.example.estudent.domain.model.Duty
import com.example.estudent.presentation.screen.common_components.BackgroundIcon
import com.example.estudent.presentation.screen.common_components.TextInputField
import com.example.estudent.presentation.state.TextFieldState
import com.example.estudent.presentation.viewModel.AddViewModel
import com.example.estudent.presentation.viewModel.InputTextFieldViewModel
import com.example.estudent.ui.theme.mGreen
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddScreen(
    addViewModel: AddViewModel,
    inputTextFieldViewModel: InputTextFieldViewModel,
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val textFieldState by inputTextFieldViewModel.textFieldState.collectAsState()

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
                modifier = Modifier.fillMaxWidth(),
                textFieldState = textFieldState,
                inputTextFieldViewModel = inputTextFieldViewModel,
                hideKeyboardController = {
                    keyboardController?.hide()
                }
            )

            SaveButton(
                modifier = Modifier.padding(vertical = 8.dp),
                errorMessage = textFieldState.errorMessage,
                onSuccess = {
                    val dutyToInsert = Duty(
                        title = textFieldState.title,
                        description = textFieldState.description,
                        category = textFieldState.category,
                        deadline = textFieldState.deadline
                    )
                    addViewModel.insertDuty(dutyToInsert)
                    Toast.makeText(context, "Successfully added", Toast.LENGTH_SHORT).show()
                },
                onError = {
                    Toast.makeText(context, "${textFieldState.errorMessage}", Toast.LENGTH_SHORT)
                        .show()
                }
            )
        }
    }

}

@Composable
private fun InputSection(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState,
    inputTextFieldViewModel: InputTextFieldViewModel,
    hideKeyboardController: () -> Unit,
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        InputEntry(
            modifier = Modifier.fillMaxWidth(0.8f),
            text = textFieldState.title,
            label = "Enter title",
            maxTextLength = textFieldState.maxTitleLength,
            characterCounter = textFieldState.characterCounterTitle,
            maxLines = 5,
            isError = !inputTextFieldViewModel.isTextValid(
                text = textFieldState.title,
                errorMessage = "Invalid title."
            ),
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
            },
            onDoneClick = {
                hideKeyboardController()
            }
        )

        InputEntry(
            modifier = Modifier.fillMaxWidth(0.8f),
            text = textFieldState.description,
            label = "Enter description",
            maxTextLength = textFieldState.maxDescriptionLength,
            characterCounter = textFieldState.characterCounterDescription,
            maxLines = 5,
            isError = !inputTextFieldViewModel.isTextValid(
                text = textFieldState.description,
                errorMessage = "Invalid description."
            ),
            testTag = TEST_TAG_DESCRIPTION_INPUT,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = "Edit icon",
                    tint = Color.Gray
                )
            },
            onClearClick = {
                inputTextFieldViewModel.updateTextFieldStateDescription(
                    description = "",
                    characterCounter = 0
                )
            },
            onValueChange = { text ->
                if (text.length <= textFieldState.maxDescriptionLength) {
                    inputTextFieldViewModel.updateTextFieldStateDescription(text, text.length)
                }
            },
            onDoneClick = { description ->
                hideKeyboardController()
            }
        )

        CategoryInputRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            updateCategory = { categoryName ->
                inputTextFieldViewModel.updateTextFieldStateCategory(categoryName)
            }
        )

//        InputEntry(
//            modifier = Modifier.fillMaxWidth(0.8f),
//            text = textFieldState.deadline,
//            label = "Enter deadline",
//            maxTextLength = textFieldState.maxDeadlineLength,
//            characterCounter = textFieldState.characterCounterDeadline,
//            maxLines = 5,
//            isError = false,
//            testTag = TEST_TAG_DEADLINE_INPUT,
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.DateRange,
//                    contentDescription = "Edit icon"
//                )
//            },
//            onClearClick = {
//                inputTextFieldViewModel.updateTextFieldStateDeadline(
//                    deadline = "",
//                    characterCounter = 0
//                )
//            },
//            onValueChange = { text ->
//                if (text.length <= textFieldState.maxDeadlineLength) {
//                    inputTextFieldViewModel.updateTextFieldStateDeadline(text, text.length)
//                }
//            },
//            onDoneClick = { deadline ->
//                hideKeyboardController()
//            }
//        )

        DateInput(
            modifier = Modifier.fillMaxWidth(0.8f)
                .padding(vertical = 8.dp),
            onDatePicked = { date ->
                inputTextFieldViewModel.updateTextFieldStateDeadline(deadline = date)
            }
        )
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

@Composable
fun CategoryInputRow(
    modifier: Modifier = Modifier,
    categories: List<String> = listOf("Projects", "Tasks", "Exams"),
    updateCategory: (String) -> Unit,
) {

    var selectedCategoryVariantIndex by remember {
        mutableStateOf(0)
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            itemsIndexed(categories) { index, category ->
                CategoryVariant(
                    categoryName = category,
                    isSelected = selectedCategoryVariantIndex == index,
                    onCategoryVariantClick = { categoryName ->
                        updateCategory(categoryName)
                        selectedCategoryVariantIndex = index
                    })
            }

        }

    }

}

@Composable
private fun CategoryVariant(
    categoryName: String,
    isSelected: Boolean,
    onCategoryVariantClick: (String) -> Unit,
) {

    val color: Color by animateColorAsState(
        if (isSelected)
            mGreen
        else
            Color.DarkGray,
        animationSpec = tween(650)
    )

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onCategoryVariantClick(categoryName)
            },
        shape = RoundedCornerShape(16.dp),
        backgroundColor = color,
        elevation = 4.dp
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp),
            text = categoryName,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DateInput(
    modifier: Modifier = Modifier,
    onDatePicked: (String) -> Unit
) {

    var buttonText by remember {
        mutableStateOf("Pick a date")
    }

    val dateDialogState = rememberMaterialDialogState()

    var pickedDate by remember {
        mutableStateOf(LocalDate.now().plusDays(1))
    }

    var formattedDate = remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("dd.MM.yyyy")
                .format(pickedDate)
        }
    }

    MaterialDialog(
        dialogState = dateDialogState,
        properties = DialogProperties(
            dismissOnClickOutside = true
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        border = BorderStroke(width = 2.dp, color = Color.Black),
        buttons = {
            positiveButton(
                text = "Ok"
            ) {
                onDatePicked(formattedDate.value)
            }
            negativeButton(
                text = "Cancel"
            )
        }
    ) {
        this.datepicker(
            initialDate = LocalDate.now().plusDays(1),
            title = "Pick a date",
            allowedDateValidator = {
                it > LocalDate.now()
            },
            onDateChange = {
                pickedDate = it
            }
        )
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(mGreen)
            .clickable {
                dateDialogState.show()
            },
        contentAlignment = Alignment.Center
    ) {

        Text(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 20.dp),
            text = buttonText,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

    }

}

@Composable
private fun SaveButton(
    errorMessage: String?,
    onSuccess: () -> Unit,
    onError: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.8f)
            .clickable {
                if (errorMessage == null)
                    onSuccess()
                else
                    onError()
            }
            .background(mGreen),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 48.dp),
            text = "Save",
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold
        )
    }
}
