package com.example.estudent.presentation.viewModel

import app.cash.turbine.test
import com.example.estudent.MainCoroutineRule
import com.example.estudent.data.repository.FakeEStudentDatabaseRepositoryImpl
import com.example.estudent.domain.model.Duty
import com.example.estudent.domain.repository.EStudentDatabaseRepository
import com.example.estudent.domain.use_case.GetAllDutiesUseCase
import com.example.estudent.domain.use_case.UpdateDutyUseCase
import com.example.estudent.presentation.state.DutyUiState
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var getAllDutiesUseCase: GetAllDutiesUseCase
    private lateinit var fakeRepository: EStudentDatabaseRepository
    private lateinit var updateDutyUseCase: UpdateDutyUseCase

    @Before
    fun setup() {
        fakeRepository = FakeEStudentDatabaseRepositoryImpl()
        getAllDutiesUseCase = GetAllDutiesUseCase(fakeRepository)
        updateDutyUseCase = UpdateDutyUseCase(fakeRepository)
        viewModel = HomeViewModel(getAllDutiesUseCase, updateDutyUseCase, fakeRepository)
    }

    @Test
    fun `HomeViewModel getAllDuties return data`() = runBlocking {
        val categories = listOf("Projects", "Exams", "Tasks")

        val duty1 = Duty(
            id = 1,
            title = "a",
            description = "a",
            importance = "a",
            deadline = "a",
            isCompleted = false,
            category = categories.random()
        )
        val duty2 = Duty(
            id = 2,
            category = categories.random(),
            title = "title",
            description = "description"
        )
        val duty3 = Duty(
            id = 3,
            category = categories.random(),
            title = "title",
            description = "description"
        )
        val duties = listOf(duty1, duty2, duty3)

        duties.forEach { duty ->
            fakeRepository.insertDuty(duty)
        }

        viewModel.getAllDuties()

        viewModel.uiState.test {
            assertEquals(DutyUiState(duties = duties, isLoading = false), awaitItem())
        }
    }

    @Test
    fun `HomeViewModel updateDutyIsCompleted`() = runTest {
        var duties = listOf<Duty>()
        val duty = Duty(
            id = 1,
            isCompleted = false, title = "title", description = "description"
        )
        fakeRepository.insertDuty(duty)

        viewModel.updateDutyIsCompleted(duty)

        getAllDutiesUseCase().collect {
            duties = it
        }

        assertThat(duties.find { it.id == 1 }?.isCompleted).isEqualTo(true)
    }
}