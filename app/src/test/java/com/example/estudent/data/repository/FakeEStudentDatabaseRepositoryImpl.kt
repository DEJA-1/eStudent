package com.example.estudent.data.repository

import com.example.estudent.domain.model.Duty
import com.example.estudent.domain.repository.EStudentDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeEStudentDatabaseRepositoryImpl() : EStudentDatabaseRepository {
    private val duties = mutableListOf<Duty>()
    var shouldReturnError: Boolean = false

    override suspend fun insertDuty(duty: Duty) {
        duties.add(duty)
    }

    override suspend fun deleteDuty(duty: Duty) {
        duties.remove(duty)
    }

    override suspend fun updateDuty(duty: Duty) {
        val index = duties.indexOfFirst { it.id == duty.id }
        if (index != -1) {
            duties[index] = duty
        }
    }

    override fun getAllDuties(): Flow<List<Duty>> = flow {
        if (shouldReturnError) {
            throw Exception("Failed to retrieve duties from database")
        } else {
            emit(duties.toList())
        }
    }

    override fun getDutiesByCategory(category: String): Flow<List<Duty>> = flow {
        val dutiesByCategory = duties.filter { it.category == category }
        emit(dutiesByCategory)
    }

}