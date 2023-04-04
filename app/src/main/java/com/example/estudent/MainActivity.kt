package com.example.estudent

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.estudent.common.TEST_TAG_BOTTOM_NAVIGATION_BAR
import com.example.estudent.navigation.BottomNavItem
import com.example.estudent.navigation.BottomNavigationBar
import com.example.estudent.navigation.MyNavHost
import com.example.estudent.navigation.Screen
import com.example.estudent.ui.theme.EStudentTheme
import com.example.estudent.ui.theme.mGreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyApp() {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry.value?.destination

    val buttonNavItems = listOf(
        BottomNavItem(
            name = "Home",
            route = Screen.Home.route,
            icon = R.drawable.baseline_home_24
        ),

        BottomNavItem(
            name = "Projects",
            route = Screen.Projects.route,
            icon = R.drawable.projects
        ),

        BottomNavItem(
            name = "Tasks",
            route = Screen.Tasks.route,
            icon = R.drawable.tasks
        ),

        BottomNavItem(
            name = "Exams",
            route = Screen.Exams.route,
            icon = R.drawable.exams
        )
    )

    EStudentTheme {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.1f)
                        .testTag(TEST_TAG_BOTTOM_NAVIGATION_BAR),
                    items = buttonNavItems,
                    currentDestination = currentDestination,
                    onItemClick = {
                        if (it.name == "Home") {
                            navController.navigate(it.route)
                        } else {
                            navController.navigate(it.route + "/${it.name}")
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate(Screen.Add.route) },
                    backgroundColor = MaterialTheme.colors.onBackground,
                    contentColor = MaterialTheme.colors.onBackground,
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 4.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "FAB",
                        tint = MaterialTheme.colors.background
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true
        ) {
            MyNavHost(navController = navController)
        }
    }
}

