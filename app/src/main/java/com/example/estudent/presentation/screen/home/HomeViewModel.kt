package com.example.estudent.presentation.screen.home

import androidx.lifecycle.ViewModel
import com.example.estudent.common.Resource
import com.example.estudent.domain.model.Duty
import com.example.estudent.domain.use_case.GetAllDutiesFromDatabaseUseCase
import com.example.estudent.presentation.state.DutyListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllDutiesFromDatabaseUseCase: GetAllDutiesFromDatabaseUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DutyListState())
    val uiState = _uiState.asStateFlow()

    init {
        getAllDutiesFromDatabase()
    }

    private fun getAllDutiesFromDatabase() {
        getAllDutiesFromDatabaseUseCase().onEach { result ->
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

}