package com.example.estudent.di

import android.app.Application
import androidx.room.Room
import com.example.estudent.data.local.database.EStudentDao
import com.example.estudent.data.local.database.EStudentDatabase
import com.example.estudent.data.repository.EStudentDatabaseRepositoryImpl
import com.example.estudent.domain.repository.EStudentDatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideEStudentDatabase(app: Application): EStudentDatabase {
        return Room.databaseBuilder(
            app,
            EStudentDatabase::class.java,
            EStudentDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideEStudentDatabaseRepository(db: EStudentDatabase) : EStudentDatabaseRepository {
        return EStudentDatabaseRepositoryImpl(db.eStudentDao)
    }
}