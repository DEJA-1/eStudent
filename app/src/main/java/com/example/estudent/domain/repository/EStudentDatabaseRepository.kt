package com.example.estudent.domain.repository

import com.example.estudent.domain.model.Duty
import kotlinx.coroutines.flow.Flow

interface EStudentDatabaseRepository {

    suspend fun insertDuty(duty: Duty)
    suspend fun deleteDuty(duty: Duty)
    suspend fun updateDuty(duty: Duty)
    fun getAllDuties(): Flow<List<Duty>>
    fun getDutiesByCategory(category: String): Flow<List<Duty>>
}