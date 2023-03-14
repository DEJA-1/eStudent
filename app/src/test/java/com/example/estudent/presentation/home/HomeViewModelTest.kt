
package com.example.estudent.presentation.home

import com.example.estudent.MainCoroutineRule
import com.example.estudent.data.repository.FakeEStudentDatabaseRepositoryImpl
import com.example.estudent.domain.model.Duty
import com.example.estudent.domain.repository.EStudentDatabaseRepository
import com.example.estudent.domain.use_case.GetAllDutiesUseCase
import com.example.estudent.domain.use_case.UpdateDutyUseCase
import com.example.estudent.presentation.screen.home.HomeViewModel
import com.example.estudent.presentation.state.DutyUiState
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var fakeRepository: EStudentDatabaseRepository
    private lateinit var getAllDutiesUseCase: GetAllDutiesUseCase
    private lateinit var updateDutyUseCase: UpdateDutyUseCase

    @Before
    fun setup() {
        fakeRepository = FakeEStudentDatabaseRepositoryImpl()
        getAllDutiesUseCase = GetAllDutiesUseCase(fakeRepository)
        updateDutyUseCase = UpdateDutyUseCase(fakeRepository)
        viewModel = HomeViewModel(getAllDutiesUseCase, updateDutyUseCase)
    }

    //TODO DOESN'T WORK  RIGHT
    @Test
    fun `HomeViewModel getAllDuties returns Resource Success`() = runTest {
        val uiState = viewModel.uiState.value
        val categories = listOf("Projects", "Exams", "Tasks")

        val duty1 = Duty(id = 1, title = "a", description = "a", importance = "a", deadline = "a", isCompleted = false, category = categories.random())
        val duty2 = Duty(id = 2, category = categories.random())
        val duty3 = Duty(id = 3 , category = categories.random())
        val duties = listOf(duty1, duty2, duty3)

        duties.forEach { duty ->
            fakeRepository.insertDuty(duty)
        }

        viewModel.getAllDuties()

        assertThat(viewModel.uiState.value).isEqualTo(DutyUiState(duties = duties, isLoading = true))
    }


    @Test
    fun `HomeViewModel update duty is completed state`() = runTest {
        var duties = listOf<Duty>()
        val duty = Duty(
            id = 1,
            isCompleted = false
        )
        fakeRepository.insertDuty(duty)

        viewModel.updateDutyIsCompleted(duty)

        getAllDutiesUseCase().collect {
            duties = it.data ?: emptyList()
        }

        assertThat(duties.find { it.id == 1 }?.isCompleted).isEqualTo(true)
    }
}