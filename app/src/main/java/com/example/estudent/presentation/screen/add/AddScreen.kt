package com.example.estudent.presentation.screen.add

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.estudent.presentation.screen.add.components.CustomSwitch
import com.example.estudent.presentation.screen.common_components.BackgroundIcon
import com.example.estudent.presentation.screen.common_components.TextInputField
import com.example.estudent.presentation.state.TextFieldState
import com.example.estudent.presentation.viewModel.AddViewModel
import com.example.estudent.presentation.viewModel.InputTextFieldViewModel
import com.example.estudent.ui.theme.mGray
import com.example.estudent.ui.theme.mGreen
import com.example.estudent.ui.theme.mRed
import com.example.estudent.ui.theme.mYellow
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
                isInputValid = textFieldState.title.isNotBlank() && textFieldState.description.isNotBlank(),
                onSuccess = {
                    val dutyToInsert = Duty(
                        title = textFieldState.title,
                        description = textFieldState.description,
                        category = textFieldState.category,
                        hasDeadline = textFieldState.hasDeadline,
                        deadline = textFieldState.deadline,
                        addedDate = textFieldState.addedDate,
                        importance = textFieldState.importance
                    )
                    addViewModel.insertDuty(dutyToInsert)

                    Toast.makeText(context, "Successfully added", Toast.LENGTH_SHORT).show()

                    inputTextFieldViewModel.resetTextFieldState()
                },
                onError = {
                    Toast.makeText(context, "Invalid data", Toast.LENGTH_SHORT)
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

    val isChecked = remember {
        mutableStateOf(textFieldState.hasDeadline)
    }

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
            isError = textFieldState.title.isBlank(),
            testTag = TEST_TAG_TITLE_INPUT,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit icon",
                    tint = mGray.copy(alpha = 0.6f)
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
            isError = textFieldState.description.isBlank(),
            testTag = TEST_TAG_DESCRIPTION_INPUT,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = "Edit icon",
                    tint = mGray.copy(alpha = 0.6f)
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
            elements = listOf("Projects", "Tasks", "Exams"),
            onCategoryVariantClick = { categoryName ->
                inputTextFieldViewModel.updateTextFieldStateCategory(categoryName)
            }
        )


        DateInput(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 12.dp),
            onDatePicked = { date ->
                inputTextFieldViewModel.updateTextFieldStateDeadline(deadline = date)
            },
            isChecked = isChecked,
            onSwitchButtonChange = {
                isChecked.value = !isChecked.value
                inputTextFieldViewModel.updateTextFieldStateHasDeadline(hasDeadline = isChecked.value)
            }
        )

        ImportanceInputRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elements = listOf("Important", "Moderate", "Unimportant"),
            onImportanceVariantClick = { importanceName ->
                inputTextFieldViewModel.updateTextFieldStateImportance(importanceName)
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
    elements: List<String>,
    onCategoryVariantClick: (String) -> Unit,
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
            itemsIndexed(elements) { index, category ->

                CategoryAndImportanceVariant(
                    variantName = category,
                    isSelected = selectedCategoryVariantIndex == index,
                    onVariantClick = { categoryName ->
                        onCategoryVariantClick(categoryName)
                        selectedCategoryVariantIndex = index
                    })
            }

        }
    }
}

@Composable
fun ImportanceInputRow(
    modifier: Modifier = Modifier,
    elements: List<String>,
    onImportanceVariantClick: (String) -> Unit,
) {

    var selectedImportanceVariantIndex by remember {
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
            itemsIndexed(elements) { index, category ->

                CategoryAndImportanceVariant(
                    variantName = category,
                    isSelected = selectedImportanceVariantIndex == index,
                    onVariantClick = { importanceName ->
                        onImportanceVariantClick(importanceName)
                        selectedImportanceVariantIndex = index
                    })
            }

        }
    }
}

@Composable
private fun CategoryAndImportanceVariant(
    variantName: String,
    isSelected: Boolean,
    onVariantClick: (String) -> Unit,
) {

    val color: Color by animateColorAsState(
        if (isSelected)
            when (variantName) {
                // Categories
                "Projects" -> mGreen.copy(alpha = 0.7f)
                "Exams" -> mGreen.copy(alpha = 0.7f)
                "Tasks" -> mGreen.copy(alpha = 0.7f)
                // Importance
                "Unimportant" -> mGreen.copy(alpha = 0.7f)
                "Important" -> mRed.copy(alpha = 0.7f)
                "Moderate" -> mYellow.copy(alpha = 0.7f)
                else -> mGray.copy(alpha = 0.7f)
            }
        else
            mGray.copy(alpha = 0.7f),
        animationSpec = tween(650)
    )

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onVariantClick(variantName)
            }
            .testTag(TEST_TAG_CATEGORY_INPUT),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = color,
        elevation = 4.dp
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp),
            text = variantName,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DateInput(
    modifier: Modifier = Modifier,
    isChecked: MutableState<Boolean>,
    onSwitchButtonChange: () -> Unit,
    onDatePicked: (String) -> Unit,
) {

    var buttonText by remember {
        mutableStateOf("Pick a date")
    }

    val dateDialogState = rememberMaterialDialogState()

    var pickedDate by rememberSaveable {
        mutableStateOf(LocalDate.now().plusDays(1))
    }

    var formattedDate = remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("dd.MM.yyyy")
                .format(pickedDate)
        }
    }

    val pickDateButtonColor: Color by animateColorAsState(
        if (isChecked.value)
            mGray.copy(alpha = 0.8f)
        else
            mGray.copy(alpha = 0.3f),
        animationSpec = tween(650)
    )

    MaterialDialog(
        dialogState = dateDialogState,
        properties = DialogProperties(
            dismissOnClickOutside = true
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        border = BorderStroke(width = 2.dp, color = MaterialTheme.colors.onBackground),
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

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomSwitch(isChecked = isChecked, onChange = { onSwitchButtonChange() })

            Text(
                text = "Is there a deadline?",
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .background(pickDateButtonColor)
                .clickable(enabled = isChecked.value) {
                    dateDialogState.show()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp),
                text = buttonText,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

    }

}

@Composable
private fun SaveButton(
    isInputValid: Boolean,
    onSuccess: () -> Unit,
    onError: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.8f)
            .fillMaxHeight(0.14f)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                if (isInputValid)
                    onSuccess()
                else
                    onError()
            }
            .background(mGreen),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 24.dp),
            text = "Save",
            color = MaterialTheme.colors.onBackground,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
