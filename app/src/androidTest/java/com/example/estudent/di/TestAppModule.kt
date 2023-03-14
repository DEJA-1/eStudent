package com.example.estudent.di

import android.content.Context
import androidx.room.Room
import com.example.estudent.data.local.database.EStudentDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {
    @Provides
    @Named("test_database")
    fun provideInMemoryDatabase(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, EStudentDatabase::class.java)
            .allowMainThreadQueries()
            .build()
}