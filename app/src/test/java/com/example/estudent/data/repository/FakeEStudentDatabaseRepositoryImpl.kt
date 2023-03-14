package com.example.estudent.data.repository

import com.example.estudent.common.Resource
import com.example.estudent.domain.model.Duty
import com.example.estudent.domain.repository.EStudentDatabaseRepository
import kotlinx.coroutines.flow.*

class FakeEStudentDatabaseRepositoryImpl : EStudentDatabaseRepository {
    private val duties = mutableListOf<Duty>()
    private val shouldReturnError = false

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

    override fun getAllDuties(): Flow<Resource<List<Duty>>> = flow {
        emit(Resource.Loading())
        if (shouldReturnError)
            emit(Resource.Error("Failed to retrieve all duties from the database"))
        else
            emit(Resource.Success(duties))

    }

    override fun getDutiesByCategory(category: String): Flow<Resource<List<Duty>>> = flow {
        val dutiesByCategory = duties.filter { it.category == category }

        emit(Resource.Loading())
        if (shouldReturnError)
            emit(Resource.Error("Failed to retrieve all duties from the database"))
        else
            emit(Resource.Success(dutiesByCategory))
    }

}