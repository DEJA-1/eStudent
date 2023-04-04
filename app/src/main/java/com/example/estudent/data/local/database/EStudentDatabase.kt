package com.example.estudent.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.estudent.domain.model.Duty

@Database(entities = [Duty::class], version = 3, exportSchema = false)
abstract class EStudentDatabase : RoomDatabase() {

    abstract val eStudentDao: EStudentDao

    companion object {
        const val DATABASE_NAME = "duty_table"
    }
}