package com.example.estudent.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.estudent.common.TEST_TAG_EXAMS_SCREEN
import com.example.estudent.common.TEST_TAG_HOME_SCREEN
import com.example.estudent.common.TEST_TAG_PROJECTS_SCREEN
import com.example.estudent.common.TEST_TAG_TASKS_SCREEN
import com.example.estudent.presentation.screen.exams.ExamsScreen
import com.example.estudent.presentation.screen.home.HomeScreen
import com.example.estudent.presentation.screen.projects.ProjectsScreen
import com.example.estudent.presentation.screen.tasks.TasksScreen

@Composable
fun MyNavHost(
    navController: NavHostController,
    startDestination: String = Screen.Home.route,
) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(

            )
        }

        composable(
            route = Screen.Projects.route
        ) {
            ProjectsScreen(

            )
        }

        composable(
            route = Screen.Tasks.route
        ) {
            TasksScreen(

            )
        }

        composable(
            route = Screen.Exams.route
        ) {
            ExamsScreen(

            )
        }
    }
}