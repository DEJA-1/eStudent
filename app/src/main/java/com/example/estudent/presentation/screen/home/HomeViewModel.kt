package com.example.estudent.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.estudent.common.Resource
import com.example.estudent.domain.model.Duty
import com.example.estudent.domain.use_case.GetAllDutiesUseCase
import com.example.estudent.domain.use_case.UpdateDutyUseCase
import com.example.estudent.presentation.state.DutyUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllDutiesUseCase: GetAllDutiesUseCase,
    private val updateDutyUseCase: UpdateDutyUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DutyUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getAllDuties()
    }

    fun getAllDuties() {
        getAllDutiesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            duties = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            error = result.message ?: "An unexpected error occured",
                            isLoading = false
                        )
                    }
                }
                is Resource.Loading -> {
                    _uiState.update {
                        it.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun updateDutyIsCompleted(duty: Duty) = viewModelScope.launch {
        val updatedDuty = duty.copy(isCompleted = !duty.isCompleted)
        updateDutyUseCase(updatedDuty)
    }
}