package com.example.estudent.data.repository

import android.util.Log
import com.example.estudent.data.local.database.EStudentDao
import com.example.estudent.domain.model.Duty
import com.example.estudent.domain.repository.EStudentDatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class EStudentDatabaseRepositoryImpl @Inject constructor(
    private val dao: EStudentDao,
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

    override fun getAllDuties(): Flow<List<Duty>> =
        dao.getAllDuties().flowOn(Dispatchers.IO).conflate()



    override fun getDutiesByCategory(category: String): Flow<List<Duty>> =
        dao.getDutiesByCategory(category).flowOn(Dispatchers.IO).conflate()


}
