package com.example.estudent.data.repository

import com.example.estudent.common.Resource
import com.example.estudent.data.local.database.EStudentDao
import com.example.estudent.domain.model.Duty
import com.example.estudent.domain.repository.EStudentDatabaseRepository
import kotlinx.coroutines.flow.Flow
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

    override fun getAllDuties(): Flow<Resource<List<Duty>>> = flow {
        try {
            emit(Resource.Loading())
            val duties = dao.getAllDuties().firstOrNull()
            emit(Resource.Success(duties ?: emptyList()))
        } catch (e: Exception) {
            emit(Resource.Error("Database Error: ${e.message}"))
        }
    }

    override fun getDutiesByCategory(category: String): Flow<Resource<List<Duty>>> = flow {
        try {
            emit(Resource.Loading())
            val duties = dao.getDutiesByCategory(category = category).firstOrNull()
            emit(Resource.Success(duties ?: emptyList()))
        } catch (e: Exception) {
            emit(Resource.Error("Database Error: ${e.message}"))
        }
    }
}
