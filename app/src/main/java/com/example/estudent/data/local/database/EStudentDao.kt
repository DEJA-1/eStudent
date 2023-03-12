package com.example.estudent.data.local.database

import androidx.room.*
import com.example.estudent.common.Resource
import com.example.estudent.domain.model.Duty
import kotlinx.coroutines.flow.Flow

@Dao
interface EStudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDuty(duty: Duty)

    @Delete
    suspend fun deleteDuty(duty: Duty)

    @Query("SELECT * FROM duty_table")
    fun getAllDuties(): Flow<List<Duty>>
    
    @Query("SELECT * FROM duty_table WHERE category = :category")
    fun getDutiesByCategory(category: String): Flow<List<Duty>>
}