package com.example.estudent.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.estudent.domain.model.Duty
import com.example.estudent.domain.use_case.GetAllDutiesUseCase
import com.example.estudent.domain.use_case.UpdateDutyUseCase
import com.example.estudent.presentation.state.DutyUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllDutiesUseCase: GetAllDutiesUseCase,
    private val updateDutyUseCase: UpdateDutyUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DutyUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getAllDuties()
    }

    fun getAllDuties() = viewModelScope.launch {
        _uiState.update {
            it.copy(isLoading = true)
        }

        getAllDutiesUseCase()
            .flowOn(Dispatchers.IO)
            .catch { e ->
                _uiState.update {
                    it.copy(error = "Failed to retrieve duties from database: ${e.message}", isLoading = false)
                }
            }
            .collect { result ->
                _uiState.update {
                    it.copy(duties = result, isLoading = false)
                }
            }

    }

    fun updateDutyIsCompleted(duty: Duty) = viewModelScope.launch {
        val updatedDuty = duty.copy(isCompleted = !duty.isCompleted)
        updateDutyUseCase(updatedDuty)
    }
}