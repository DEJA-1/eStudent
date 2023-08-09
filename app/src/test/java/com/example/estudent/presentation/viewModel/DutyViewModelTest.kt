package com.example.estudent.presentation.viewModel

import app.cash.turbine.test
import com.example.estudent.MainCoroutineRule
import com.example.estudent.data.repository.FakeEStudentDatabaseRepositoryImpl
import com.example.estudent.domain.model.Duty
import com.example.estudent.domain.repository.EStudentDatabaseRepository
import com.example.estudent.domain.use_case.DatabaseUseCases
import com.example.estudent.domain.use_case.DeleteDutyUseCase
import com.example.estudent.domain.use_case.GetAllDutiesUseCase
import com.example.estudent.domain.use_case.GetDutiesByCategoryUseCase
import com.example.estudent.domain.use_case.UpdateDutyUseCase
import com.example.estudent.presentation.state.DutyUiState
import com.google.common.truth.Truth
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DutyViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: DutyViewModel
    private lateinit var databaseUseCases: DatabaseUseCases
    private lateinit var fakeRepository: EStudentDatabaseRepository
    private lateinit var updateDutyUseCase: UpdateDutyUseCase
    private lateinit var getDutiesByCategoryUseCase: GetDutiesByCategoryUseCase
    private lateinit var getAllDutiesUseCase: GetAllDutiesUseCase
    private lateinit var deleteDutyUseCase: DeleteDutyUseCase


    @Before
    fun setup() {
        fakeRepository = FakeEStudentDatabaseRepositoryImpl()
        updateDutyUseCase = UpdateDutyUseCase(fakeRepository)
        getDutiesByCategoryUseCase = GetDutiesByCategoryUseCase(fakeRepository)
        getAllDutiesUseCase = GetAllDutiesUseCase(fakeRepository)
        deleteDutyUseCase = DeleteDutyUseCase(fakeRepository)
        databaseUseCases = DatabaseUseCases(getAllDutiesUseCase, getDutiesByCategoryUseCase, updateDutyUseCase, deleteDutyUseCase)

        viewModel = DutyViewModel(databaseUseCases)
    }

    @Test
    fun `DutyViewModel getDutiesByCategory return data`() = runBlocking {
        val categories = listOf("Projects", "Exams", "Tasks")
        val category = categories.random()

        val duties = mutableListOf<Duty>()
        var dutiesFilteredByCategory = emptyList<Duty>()

        repeat(10) { iterator ->
            duties.add(Duty(id = iterator, category = categories.random(), title = "title", description = "description"))
        }

        duties.forEach { duty ->
            fakeRepository.insertDuty(duty)
        }

        dutiesFilteredByCategory = duties.filter { it.category == category }.toList()

        viewModel.getDutiesByCategory(category)

        viewModel.uiState.test {
            TestCase.assertEquals(DutyUiState(duties = dutiesFilteredByCategory, isLoading = false), awaitItem())
        }
    }

    @Test
    fun `DutyViewModel updateDutyIsCompleted`() = runTest {
        var duties = listOf<Duty>()
        val category = "Projects"

        val duty = Duty(
            id = 1,
            category = category,
            isCompleted = false, title = "title", description = "description"
        )
        fakeRepository.insertDuty(duty)

        viewModel.updateDutyIsCompleted(duty)

        getDutiesByCategoryUseCase(category).collect {
            duties = it
        }

        Truth.assertThat(duties.find { it.id == 1 }?.isCompleted).isEqualTo(true)
    }
}