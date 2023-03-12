package com.example.estudent.presentation.screen.projects

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.estudent.common.Constants
import com.example.estudent.common.Resource
import com.example.estudent.domain.use_case.GetDutiesByCategoryUseCase
import com.example.estudent.presentation.state.DutyUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProjectsViewModel @Inject constructor(
    private val getDutiesByCategoryUseCase: GetDutiesByCategoryUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(DutyUiState())
    val uiState = _uiState.asStateFlow()

    val category = savedStateHandle.get<String>(Constants.PARAM_CATEGORY) ?: ""

    init {
        getDutiesByCategory(category = category)
    }

    private fun getDutiesByCategory(category: String) {
        getDutiesByCategoryUseCase(category = category).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            duties = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                }
                is Resource.Loading -> {
                    _uiState.update {
                        it.copy(isLoading = true)
                    }
                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}