package com.example.estudent.domain.use_case

import com.example.estudent.data.repository.FakeEStudentDatabaseRepositoryImpl
import com.example.estudent.domain.model.Duty
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)

class GetAllDutiesUseCaseTest {

    private lateinit var getAllDutiesUseCase: GetAllDutiesUseCase
    private lateinit var fakeRepository: FakeEStudentDatabaseRepositoryImpl

    @Before
    fun setup() {
        fakeRepository = FakeEStudentDatabaseRepositoryImpl()
        getAllDutiesUseCase = GetAllDutiesUseCase(fakeRepository)

        val dutiesToInsert = mutableListOf<Duty>()
        val categories = listOf("Projects", "Exams", "Tasks")

        repeat(10) {
            dutiesToInsert.add(
                Duty(
                    id = it,
                    category = categories.random()
                )
            )
        }
        runBlocking {
            dutiesToInsert.forEach { fakeRepository.insertDuty(duty = it) }
        }
    }

    @Test
    fun `Get all duties use case`() = runTest {
        var duties: List<Duty> = emptyList()
        getAllDutiesUseCase().collect {
            duties = it
        }
        assertThat(duties).isNotEmpty()
    }
}