package com.example.estudent.presentation.viewModel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.estudent.common.Constants
import com.example.estudent.domain.model.Duty
import com.example.estudent.domain.use_case.DatabaseUseCases
import com.example.estudent.domain.use_case.DeleteDutyUseCase
import com.example.estudent.domain.use_case.GetDutiesByCategoryUseCase
import com.example.estudent.domain.use_case.UpdateDutyUseCase
import com.example.estudent.presentation.state.DutyUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DutyViewModel @Inject constructor(
    private val databaseUseCases: DatabaseUseCases,
    savedStateHandle: SavedStateHandle = SavedStateHandle(),
) : ViewModel() {

    private val _uiState = MutableStateFlow(DutyUiState())
    val uiState = _uiState.asStateFlow()

    val category = savedStateHandle.get<String>(Constants.PARAM_CATEGORY) ?: ""

    init {
        getDutiesByCategory(category = category)
    }

    fun getDutiesByCategory(category: String) = viewModelScope.launch{
        _uiState.update {
            it.copy(isLoading = true)
        }

        databaseUseCases.getDutiesByCategoryUseCase(category).collect { result ->
            _uiState.update {
                it.copy(duties = result, isLoading = false)
            }
        }
    }
    fun updateDutyIsCompleted(duty: Duty) = viewModelScope.launch {
        val updatedDuty = duty.copy(isCompleted = !duty.isCompleted)
        databaseUseCases.updateDutyUseCase(updatedDuty)
    }

    fun deleteDuty(duty: Duty) = viewModelScope.launch {
        databaseUseCases.deleteDutyUseCase(duty)
    }
}