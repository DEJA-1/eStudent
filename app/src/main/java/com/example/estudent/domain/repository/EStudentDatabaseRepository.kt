package com.example.estudent.domain.repository

import com.example.estudent.common.Resource
import com.example.estudent.domain.model.Duty
import kotlinx.coroutines.flow.Flow

interface EStudentDatabaseRepository {

    suspend fun insertDutyToDatabase(duty: Duty)

    suspend fun deleteDutyFromDatabase(duty: Duty)

    fun getAllDutiesFromDatabase(): Flow<Resource<List<Duty>>>
}