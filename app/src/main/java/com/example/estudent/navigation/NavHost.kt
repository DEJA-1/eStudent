package com.example.estudent.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.example.estudent.presentation.screen.home.HomeViewModel
import com.example.estudent.presentation.screen.projects.ProjectsScreen
import com.example.estudent.presentation.screen.projects.ProjectsViewModel
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
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(viewModel = viewModel)
        }

        composable(
            route = Screen.Projects.route + "/{category}"
        ) {
            val viewModel = hiltViewModel<ProjectsViewModel>()
            ProjectsScreen(viewModel = viewModel)
        }

        composable(
            route = Screen.Tasks.route + "/{category}"
        ) {
            TasksScreen(

            )
        }

        composable(
            route = Screen.Exams.route + "/{category}"
        ) {
            ExamsScreen(

            )
        }
    }
}