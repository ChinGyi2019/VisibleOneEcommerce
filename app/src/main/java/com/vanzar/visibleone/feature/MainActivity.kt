package com.vanzar.visibleone.feature

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.vanzar.visibleone.core.design.theme.VisibleOneTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import androidx.navigation.toRoute
import com.vanzar.visibleone.core.design.theme.Black
import com.vanzar.visibleone.core.design.theme.Orange
import com.vanzar.visibleone.core.design.theme.White
import com.vanzar.visibleone.domain.shoe.Shoe
import com.vanzar.visibleone.feature.detail.DetailScreen
import com.vanzar.visibleone.feature.home.HomeScreen
import com.vanzar.visibleone.feature.start.StartScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VisibleOneTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val items = listOf(
        Screen.Home,
        Screen.Search,
        Screen.Profile
    )

    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            val currentRoute = currentRoute(navController)
            if (currentRoute in items.map { it.route }) {
                BottomNavigationBar(navController, items)
            }
        }

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Start.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    goToDetail = { id ->
                        navController.navigate("${Screen.Detail.route}/$id")
                    }
                )
            }
            composable(Screen.Profile.route) { ProfileScreen() }
            composable(Screen.Detail.route + "/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")
                Log.e("id", id.toString())
                id?.let {
                    DetailScreen(
                        navController, id, clickFavourite = {}
                    )
                }
            }
            composable(Screen.Search.route) { SearchScreen() }
            composable(Screen.Start.route) {
                StartScreen(goToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController, items: List<Screen>) {
    NavigationBar(
        containerColor = White,
        contentColor = Black
    ) {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.label,
                    )
                },
                // label = { Text(screen.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Orange,
                    indicatorColor = Color.Transparent
                ),
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun SearchScreen() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Text("Search Screen", modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun ProfileScreen() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Text("Profile Screen", modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    data object Home : Screen("home", "Home", Icons.Filled.Home)
    data object Detail : Screen("detail", "Detail", Icons.Filled.Home)
    data object Search : Screen("search", "Search", Icons.Filled.Search)
    data object Profile : Screen("profile", "Profile", Icons.Filled.Person)
    data object Start : Screen("start", "start", Icons.Filled.Person)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}
