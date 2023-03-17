package com.example.estudent.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.estudent.R

sealed class Screen(
    val route: String,
) {
    object Home : Screen(route = "home_screen")

    object Projects : Screen(route = "projects_screen")

    object Tasks : Screen(route = "tasks_screen")

    object Exams : Screen(route = "exams_screen")

    object Add : Screen(route = "add_screen")
}
