package com.example.estudent.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "duty_table")
data class Duty(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "category")
    val category: String = "", // project/task/exam

    @ColumnInfo(name = "importance")
    val importance: String = "",

    @ColumnInfo(name = "deadline")
    val deadline: String = "",

    @ColumnInfo(name = "isCompleted")
    val isCompleted: Boolean = false
)

