package com.example.estudent.domain.use_case

import com.example.estudent.data.repository.FakeEStudentDatabaseRepositoryImpl
import com.example.estudent.domain.model.Duty
import com.example.estudent.domain.repository.EStudentDatabaseRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UpdateDutyUseCaseTest {

    private lateinit var updateDutyUseCase: UpdateDutyUseCase
    private lateinit var fakeRepository: EStudentDatabaseRepository

    @Before
    fun setup() {
        fakeRepository = FakeEStudentDatabaseRepositoryImpl()
        updateDutyUseCase = UpdateDutyUseCase(fakeRepository)

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
    fun `Update duty`() = runTest {
        var duties = listOf<Duty>()
        val dutyToUpdate = Duty(
            id = 1,
            category = "Projects",
            isCompleted = true,
            title = "test"
        )
        updateDutyUseCase(dutyToUpdate)
        fakeRepository.getAllDuties().collect {
            duties = it
        }
        assertThat(duties).contains(dutyToUpdate)
    }
}