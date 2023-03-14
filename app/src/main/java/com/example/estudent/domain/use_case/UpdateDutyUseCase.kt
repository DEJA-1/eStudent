package com.example.estudent.domain.use_case

import com.example.estudent.domain.model.Duty
import com.example.estudent.domain.repository.EStudentDatabaseRepository
import javax.inject.Inject

class UpdateDutyUseCase @Inject constructor(
    private val repository: EStudentDatabaseRepository
){
    suspend operator fun invoke(duty: Duty) =
        repository.updateDuty(duty)
}