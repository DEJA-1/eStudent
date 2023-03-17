package com.example.estudent.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.estudent.presentation.state.InputFieldDutyState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddViewModel : ViewModel() {

    private val _inputState = MutableStateFlow(InputFieldDutyState())
    val inputState = _inputState.asStateFlow()

    fun updateInputStateTitle(title: String) {
        _inputState.update {
            it.copy(title = title)
        }
    }

    fun updateInputStateCharacterCounterTitle(characterCounter: Int) {
        _inputState.update {
            it.copy(characterCounterTitle = characterCounter)
        }
    }

    fun updateInputStateDescription(description: String) {
        _inputState.update {
            it.copy(description = description)
        }
    }

    fun updateInputStateCharacterCounterDescription(characterCounter: Int) {
        _inputState.update {
            it.copy(characterCounterDescription = characterCounter)
        }
    }

    fun updateInputStateCategory(category: String) {
        _inputState.update {
            it.copy(category = category)
        }
    }

    fun updateInputStateCharacterCounterCategory(characterCounter: Int) {
        _inputState.update {
            it.copy(characterCounterCategory = characterCounter)
        }
    }

    fun updateInputStateDeadline(deadline: String) {
        _inputState.update {
            it.copy(deadline = deadline)
        }
    }

    fun updateInputStateCharacterCounterDeadline(characterCounter: Int) {
        _inputState.update {
            it.copy(characterCounterDeadline = characterCounter)
        }
    }
}