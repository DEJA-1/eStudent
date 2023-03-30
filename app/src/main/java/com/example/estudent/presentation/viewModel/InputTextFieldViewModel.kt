package com.example.estudent.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.estudent.presentation.state.TextFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InputTextFieldViewModel : ViewModel() {

    private val _textFieldState = MutableStateFlow(TextFieldState())
    val textFieldState = _textFieldState.asStateFlow()

    fun updateTextFieldStateTitle(title: String, characterCounter: Int) {
        _textFieldState.update {
            it.copy(title = title, characterCounterTitle = characterCounter)
        }
    }

    fun updateTextFieldStateDescription(description: String, characterCounter: Int) {
        _textFieldState.update {
            it.copy(description = description, characterCounterDescription = characterCounter)
        }
    }

    fun updateTextFieldStateCategory(category: String) {
        _textFieldState.update {
            it.copy(category = category)
        }
    }

    fun updateTextFieldStateDeadline(deadline: String) {
        _textFieldState.update {
            it.copy(deadline = deadline)
        }
    }

    fun resetTextFieldState() {
        _textFieldState.update {
            it.copy(title = "",
            description = "",
            category = "Projects")
        }
    }

}