package com.example.estudent.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.estudent.R

sealed class Screen(
    val route: String,
    val title: String,
    val icon: Int,
) {
    object Home : Screen(
        route = "home_screen",
        title = "Home",
        icon = R.drawable.baseline_home_24
    )

    object Projects : Screen(
        route = "projects_screen",
        title = "Projects",
        icon = R.drawable.projects
    )

    object Tasks : Screen(
        route = "tasks_screen",
        title = "Tasks",
        icon = R.drawable.tasks
    )

    object Exams : Screen(
        route = "exams_screen",
        title = "Exams",
        icon = R.drawable.exams
    )
}
