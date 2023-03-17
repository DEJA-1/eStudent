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
class GetDutiesByCategoryUseCaseTest {

    private lateinit var getDutiesByCategoryUseCase: GetDutiesByCategoryUseCase
    private lateinit var fakeRepository: FakeEStudentDatabaseRepositoryImpl

    @Before
    fun setup() {
        fakeRepository = FakeEStudentDatabaseRepositoryImpl()
        getDutiesByCategoryUseCase = GetDutiesByCategoryUseCase(fakeRepository)

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
    fun `Get duties by category`() = runTest {
        val category = "Exams"
        var duties = emptyList<Duty>()

        getDutiesByCategoryUseCase(category).collect {
             duties = it
        }

        duties.forEach { duty ->
            assertThat(duty.category).isEqualTo(category)
        }
    }


}