package com.example.estudent.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.estudent.ui.theme.mBackgroundBlack

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit,
) {
    var selected: Boolean
    val currentRoute = currentDestination?.route?.split('/')?.first()

    BottomNavigation(
        modifier = modifier,
        elevation = 1.dp,
        backgroundColor = Color.Transparent
    ) {
        items.forEachIndexed { index, item ->

            selected = currentDestination?.hierarchy?.any {
                currentRoute == item.route
            } == true

            // Empty navigation item to avoid fab button overlapping other items
            if (index == 2) {
                BottomNavigationItem(
                    selected = false,
                    onClick = { },
                    icon = {},
                )
            }

            BottomNavigationItem(
                onClick = { onItemClick(item) },
                selected = selected,
                selectedContentColor = Color.White,
                unselectedContentColor = MaterialTheme.colors.onBackground,
                icon = {
                    Column(
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = "Navigation icon ${item.name}",
                            modifier = Modifier.size(32.dp)
                        )

                        if (selected) {
                            Text(text = item.name)
                        }
                    }
                }
            )
        }
    }
}
