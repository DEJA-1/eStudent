package com.example.estudent.presentation.screen.common_components

import android.graphics.drawable.Icon
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.estudent.ui.theme.mBackgroundBlackSecondary

@Composable
fun TextInputField(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLines: Int,
    maxLength: Int,
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Text,
    onAction: KeyboardActions = KeyboardActions.Default,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable () -> Unit,
) {

    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = { text -> onValueChange(text) },
        label = { Text(text = label) },
        leadingIcon = leadingIcon,
        maxLines = maxLines,
        keyboardActions = onAction,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        colors = TextFieldDefaults.textFieldColors(
            disabledTextColor = Color.Gray,
            disabledLeadingIconColor = Color.Gray,
            unfocusedLabelColor = Color.Gray,
            unfocusedIndicatorColor = Color.Gray
        )
    )
}