package com.example.estudent.domain.use_case

import com.example.estudent.data.repository.EStudentDatabaseRepositoryImpl
import com.example.estudent.domain.repository.EStudentDatabaseRepository
import javax.inject.Inject

class GetDutiesByCategoryUseCase @Inject constructor(
    private val repository: EStudentDatabaseRepository
) {
    suspend operator fun invoke(category: String) =
        repository.getDutiesByCategory(category = category)
}