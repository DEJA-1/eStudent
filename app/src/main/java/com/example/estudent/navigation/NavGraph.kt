package com.example.estudent.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.estudent.presentation.screen.add.AddScreen
import com.example.estudent.presentation.screen.exams.ExamsScreen
import com.example.estudent.presentation.screen.home.HomeScreen
import com.example.estudent.presentation.viewModel.HomeViewModel
import com.example.estudent.presentation.screen.projects.ProjectsScreen
import com.example.estudent.presentation.viewModel.DutyViewModel
import com.example.estudent.presentation.screen.tasks.TasksScreen
import com.example.estudent.presentation.viewModel.AddViewModel
import com.example.estudent.presentation.viewModel.InputTextFieldViewModel

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
            val viewModel = hiltViewModel<DutyViewModel>()
            ProjectsScreen(viewModel = viewModel)
        }

        composable(
            route = Screen.Tasks.route + "/{category}"
        ) {
            val viewModel = hiltViewModel<DutyViewModel>()
            TasksScreen(
                viewModel = viewModel
            )
        }

        composable(
            route = Screen.Exams.route + "/{category}"
        ) {
            val viewModel = hiltViewModel<DutyViewModel>()
            ExamsScreen(
                viewModel = viewModel
            )
        }

        composable(
            route = Screen.Add.route
        ) {
            val addViewModel = hiltViewModel<AddViewModel>()
            val inputTextFieldViewModel = viewModel<InputTextFieldViewModel>()
            AddScreen(
                addViewModel = addViewModel,
                inputTextFieldViewModel = inputTextFieldViewModel
            )
        }
    }
}