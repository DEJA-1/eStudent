package com.example.estudent.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.estudent.domain.model.Duty
import com.example.estudent.domain.repository.EStudentDatabaseRepository
import com.example.estudent.presentation.state.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    val repository: EStudentDatabaseRepository
) : ViewModel() {

    private val _dutyToInsert = MutableStateFlow(Duty())
    val dutyToInsert = _dutyToInsert.asStateFlow()

    fun insertDuty(duty: Duty) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertDuty(duty)
    }

    fun updateDutyToInsertTitle(title: String) {
        _dutyToInsert.update {
            it.copy(title = title)
        }
    }
    fun updateDutyToInsertDescription(description: String) {
        _dutyToInsert.update {
            it.copy(description = description)
        }
    }
    fun updateDutyToInsertCategory(category: String) {
        _dutyToInsert.update {
            it.copy(category = category)
        }
    }
    fun updateDutyToInsertDeadline(deadline: String) {
        _dutyToInsert.update {
            it.copy(deadline = deadline)
        }
    }

}