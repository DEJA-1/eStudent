package com.example.data.repository

import com.example.estudent.domain.model.Duty
import com.example.estudent.domain.repository.EStudentDatabaseRepository
import kotlinx.coroutines.flow.Flow

class FakeEStudentDatabaseRepositoryImpl : EStudentDatabaseRepository {
    override suspend fun insertDuty(duty: Duty) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDuty(duty: Duty) {
        TODO("Not yet implemented")
    }

    override suspend fun updateDuty(duty: Duty) {
        TODO("Not yet implemented")
    }

    override fun getAllDuties(): Flow<List<Duty>> {
        TODO("Not yet implemented")
    }

    override fun getDutiesByCategory(category: String): Flow<List<Duty>> {
        TODO("Not yet implemented")
    }
}