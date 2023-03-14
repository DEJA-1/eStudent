package com.example.estudent.data.local

import androidx.test.filters.SmallTest
import com.example.estudent.data.local.database.EStudentDao
import com.example.estudent.data.local.database.EStudentDatabase
import com.example.estudent.domain.model.Duty
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@OptIn(ExperimentalCoroutinesApi::class)
@SmallTest
@HiltAndroidTest
class EStudentDaoTest {
    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_database")
    lateinit var database: EStudentDatabase
    private lateinit var dao: EStudentDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.eStudentDao
    }

    @Test
    fun dao_getAllDutiesByCategory() = runTest {
        val categories = listOf("Projects", "Exams", "Tasks")
        val category = "Projects"
        repeat(10) {
            dao.insertDuty(Duty(id = it, category = categories.random()))
        }
        val duties = dao.getDutiesByCategory(category).first()
        duties.forEach { duty ->
            println(duty)
            assertThat(duty.category).isEqualTo(category)
        }
    }

    @Test
    fun dao_insertDuty() = runTest {
        val duty = Duty(id = 3)
        dao.insertDuty(duty)

        val duties = dao.getAllDuties().first()
        assertThat(duties).contains(duty)
    }

    @Test
    fun dao_deleteDuty() = runTest {
        val duty = Duty(id = 4)
        dao.insertDuty(duty)
        var duties = dao.getAllDuties().first()
        assertThat(duties).contains(duty)

        dao.deleteDuty(duty)
        duties = dao.getAllDuties().first()
        assertThat(duties).doesNotContain(duty)
    }

    @After
    fun teardown() {
        database.close()
    }
}