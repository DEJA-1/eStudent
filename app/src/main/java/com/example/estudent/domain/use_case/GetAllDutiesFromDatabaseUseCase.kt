package com.example.estudent.domain.use_case

import com.example.estudent.common.Resource
import com.example.estudent.domain.model.Duty
import com.example.estudent.domain.repository.EStudentDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllDutiesFromDatabaseUseCase @Inject constructor(
    private val repository: EStudentDatabaseRepository
){
    operator fun invoke() : Flow<Resource<List<Duty>>> =
        repository.getAllDutiesFromDatabase()

}