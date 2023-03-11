package com.example.estudent.util

import com.example.estudent.ui.theme.mGreen
import com.example.estudent.ui.theme.mRed
import com.example.estudent.ui.theme.mYellow
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UtilTest {

    @Test
    fun `Util getRowColor returns green`() {
        val deadline = "21.03.23"
        val rowColor = getRowColor(deadline)

        assertThat(rowColor).isEqualTo(mGreen)
    }

    @Test
    fun `Util getRowColor returns yellow`() {
        val deadline = "15.03.23"
        val rowColor = getRowColor(deadline)

        assertThat(rowColor).isEqualTo(mYellow)
    }

    @Test
    fun `Util getRowColor returns red`() {
        val deadline = "12.03.23"
        val rowColor = getRowColor(deadline)

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
}