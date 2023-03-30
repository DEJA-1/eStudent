package com.example.estudent.presentation.screen.common_components

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.estudent.ui.theme.*

@Composable
fun TextInputField(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLines: Int,
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Text,
    onAction: KeyboardActions = KeyboardActions.Default,
    isError: Boolean,
    testTag: String,
    onClearClick: () -> Unit,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable () -> Unit,
) {

    OutlinedTextField(
        modifier = modifier.testTag(testTag),
        value = text,
        onValueChange = { t -> onValueChange(t) },
        label = { Text(text = label) },
        leadingIcon = leadingIcon,
        trailingIcon = {
            Icon(
                painter = painterResource(id = com.example.estudent.R.drawable.cancel),
                contentDescription = "Cancel icon $label",
                modifier = Modifier.clickable {
                    onClearClick()
                },
                tint = mGray
            )
        },
        maxLines = maxLines,
        keyboardActions = onAction,
        isError = isError,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        colors = TextFieldDefaults.textFieldColors(
            disabledTextColor = Color.Gray,
            disabledLeadingIconColor = Color.Gray,
            unfocusedLabelColor = Color.Gray,
            unfocusedIndicatorColor = Color.Gray,
            leadingIconColor = mGreen,
            errorLeadingIconColor = mRed
        )
    )
}