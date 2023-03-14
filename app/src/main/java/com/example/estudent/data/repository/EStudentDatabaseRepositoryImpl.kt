package com.example.estudent.data.repository

import com.example.estudent.common.Resource
import com.example.estudent.data.local.database.EStudentDao
import com.example.estudent.domain.model.Duty
import com.example.estudent.domain.repository.EStudentDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EStudentDatabaseRepositoryImpl @Inject constructor(
    private val dao: EStudentDao
) : EStudentDatabaseRepository {

    override suspend fun insertDuty(duty: Duty) {
        dao.insertDuty(duty)
    }

    override suspend fun deleteDuty(duty: Duty) {
        dao.deleteDuty(duty)
    }

    override suspend fun updateDuty(duty: Duty) {
        dao.updateDuty(duty)
    }

    override fun getAllDuties(): Flow<Resource<List<Duty>>> = flow {
        try {
            emit(Resource.Loading())
            val duties = dao.getAllDuties().first()
            emit(Resource.Success(duties))
        } catch (e: Exception) {
            emit(Resource.Error("Failed to retrieve all duties from the database: ${e.message}"))
        }
    }

    override fun getDutiesByCategory(category: String): Flow<Resource<List<Duty>>> = flow {
        try {
            emit(Resource.Loading())
            val duties = dao.getDutiesByCategory(category = category).first()
            emit(Resource.Success(duties))
        } catch (e: Exception) {
            emit(Resource.Error("Failed to retrieve duties by category from the database: ${e.message}"))
        }
    }
}
