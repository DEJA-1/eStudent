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

//    fun isInputValid(parameter: String) : Boolean {
//        when (parameter) {
//            "title" -> {
//                return b()
//            }
//            "description" -> {
//                _textFieldState.update {
//                    it.copy(errorMessage = "Invalid description")
//                }
//                return _textFieldState.value.description.isNotBlank()
//            }
//            "category" -> {
//                _textFieldState.update {
//                    it.copy(errorMessage = "Invalid category")
//                }
//                return _textFieldState.value.category.isNotBlank()
//            }
//            "deadline" -> {
//                _textFieldState.update {
//                    it.copy(errorMessage = "Invalid deadline")
//                }
//                return _textFieldState.value.deadline.isNotBlank()
//            }
//            else -> {}
//        }
//        return false
//    }

     fun isTitleAndDescriptionValid(title: String, description: String, errorMessage: String): Boolean {
        return if (title.isBlank() || description.isBlank()) {
            _textFieldState.update {
                it.copy(errorMessage = errorMessage)
            }
            false
        } else {
            _textFieldState.update {
                it.copy(errorMessage = null)
            }
            true
        }
    }

}