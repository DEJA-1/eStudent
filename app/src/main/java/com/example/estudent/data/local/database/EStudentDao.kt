package com.example.estudent.data.local.database

import androidx.room.*
import com.example.estudent.common.Resource
import com.example.estudent.domain.model.Duty
import kotlinx.coroutines.flow.Flow

@Dao
interface EStudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDutyToDatabase(duty: Duty)

    @Delete
    suspend fun deleteDutyFromDatabase(duty: Duty)

    @Query("SELECT * FROM duty_table")
    fun getAllDutiesFromDatabase(): Flow<List<Duty>>
}