package com.example.estudent.domain.use_case

data class DatabaseUseCases(
    val getAllDutiesUseCase: GetAllDutiesUseCase,
    val getDutiesByCategoryUseCase: GetDutiesByCategoryUseCase,
    val updateDutyUseCase: UpdateDutyUseCase,
    val deleteDutyUseCase: DeleteDutyUseCase
)
