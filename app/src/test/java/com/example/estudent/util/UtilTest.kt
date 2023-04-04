package com.example.estudent.util

import com.example.estudent.domain.model.Duty
import com.example.estudent.ui.theme.mGreen
import com.example.estudent.ui.theme.mRed
import com.example.estudent.ui.theme.mYellow
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.exp

class UtilTest {

    @Test
    fun `Util getRowColor returns green`() {
        val daysAheadFromLocalTime = 10
        val deadline = LocalDate.now().plusDays(daysAheadFromLocalTime.toLong()).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))

        val duty = Duty(deadline = deadline, title = "", description = "")

        val rowColor = getRowColor(duty)

        assertThat(rowColor).isEqualTo(mGreen)
    }

    @Test
    fun `Util getRowColor returns yellow`() {
        val daysAheadFromLocalTime = 6
        val deadline = LocalDate.now().plusDays(daysAheadFromLocalTime.toLong()).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))

        val duty = Duty(deadline = deadline, title = "", description = "")

        val rowColor = getRowColor(duty)

        assertThat(rowColor).isEqualTo(mYellow)
    }

    @Test
    fun `Util getRowColor returns red`() {
        val daysAheadFromLocalTime = 2
        val deadline = LocalDate.now().plusDays(daysAheadFromLocalTime.toLong()).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))

        val duty = Duty(deadline = deadline, title = "", description = "")

        val rowColor = getRowColor(duty)

        assertThat(rowColor).isEqualTo(mRed)
    }

    @Test
    fun `Util getTextImportanceColor returns green`() {
        val importance = "Minor"
        val textImportanceColor = getTextImportanceColor(importance)

        assertThat(textImportanceColor).isEqualTo(mGreen)
    }

    @Test
    fun `Util getTextImportanceColor returns yellow`() {
        val importance = "Moderate"
        val textImportanceColor = getTextImportanceColor(importance)

        assertThat(textImportanceColor).isEqualTo(mYellow)
    }

    @Test
    fun `Util getTextImportanceColor returns red`() {
        val importance = "Important"
        val textImportanceColor = getTextImportanceColor(importance)

        assertThat(textImportanceColor).isEqualTo(mRed)
    }

    @Test
    fun `Util getDisplayDate with deadline`() {
        val deadline = "23.03.2023"

        val duty = Duty(deadline = deadline, title = "", description = "", hasDeadline = true)

        val displayDate = getDisplayDate(duty)

        assertThat(displayDate).isEqualTo("Thursday, 23.03.2023")
    }

    @Test
    fun `Util getDisplayDate without deadline`() {
        val deadline = "23.03.2023"

        val duty = Duty(deadline = deadline, title = "", description = "", hasDeadline = false)

        val displayDate = getDisplayDate(duty)

        assertThat(displayDate).isEqualTo("")
    }

}