package com.example.estudent

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.estudent.common.TEST_TAG_BOTTOM_NAVIGATION_BAR
import com.example.estudent.navigation.BottomNavItem
import com.example.estudent.navigation.BottomNavigationBar
import com.example.estudent.navigation.MyNavHost
import com.example.estudent.navigation.Screen
import com.example.estudent.ui.theme.EStudentTheme

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
                    modifier = Modifier.clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                        .testTag(TEST_TAG_BOTTOM_NAVIGATION_BAR),
                    items = buttonNavItems,
                    navController = navController,
                    onItemClick = {
                        navController.navigate(it.route)
                    }
                )
            }
        ) {
            MyNavHost(navController = navController)
        }
    }
}

