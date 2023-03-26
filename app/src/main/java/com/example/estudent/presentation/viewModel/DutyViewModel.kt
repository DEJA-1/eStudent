package com.example.estudent.presentation.viewModel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.estudent.common.Constants
import com.example.estudent.domain.model.Duty
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
    private val getDutiesByCategoryUseCase: GetDutiesByCategoryUseCase,
    private val updateDutyUseCase: UpdateDutyUseCase,
    savedStateHandle: SavedStateHandle = SavedStateHandle(),
) : ViewModel() {

    private val _uiState = MutableStateFlow(DutyUiState())
    val uiState = _uiState.asStateFlow()

    val category = savedStateHandle.get<String>(Constants.PARAM_CATEGORY) ?: ""

    // temporary for the purpose of 'testing' before database
    val duty1 = Duty(
        title = "Object oriented programming assignment",
        description = "Wykonac zadanie 2 z listy laboratorium 2",
        category = "Task",
        importance = "Moderate",
        deadline = "12.03.2023",
        isCompleted = false
    )

    val duty2 = Duty(
        title = "Object oriented programming assignment",
        description = "Wykonac zadanie 2 z listy laboratorium 2",
        category = "Task",
        importance = "Moderate",
        deadline = "22.03.2023",
        isCompleted = false
    )

    val duty3 = Duty(
        title = "Object oriented programming assignment",
        description = "Wykonac zadanie 2 z listy laboratorium 2",
        category = "Task",
        importance = "Moderate",
        deadline = "18.03.2023",
        isCompleted = false
    )
    val duties = listOf(duty1, duty2, duty1, duty3)

    init {
        getDutiesByCategory(category = category)
    }

    fun getDutiesByCategory(category: String) = viewModelScope.launch {

        _uiState.update {
            it.copy(isLoading = true)
        }

        getDutiesByCategoryUseCase(category = category)
            .flowOn(Dispatchers.IO)
            .catch { cause ->
                Log.d("Database", "${cause.message}")

                _uiState.update {
                    it.copy(
                        error = "Failed to retrieve dutes from database",
                        isLoading = false
                    )
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