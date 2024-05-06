package com.senac.navegacao2024

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.senac.navegacao2024.screens.About
import com.senac.navegacao2024.screens.Profile
import com.senac.navegacao2024.screens.Register
import com.senac.navegacao2024.ui.theme.Navegacao2024Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navegacao2024Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}


private fun isSelected(currentDestination: NavDestination?, route: String) : Boolean {
    return currentDestination?.hierarchy?.any { it.route == route} == true
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.value?.destination
            BottomNavigation {
                BottomNavigationItem(
                    selected = isSelected(currentDestination, "profile"),
                    onClick = { navController.navigate("profile") },
                    icon = {
                        Icon(imageVector = Icons.Filled.AccountBox,
                            contentDescription = "")
                    })

                BottomNavigationItem(
                    selected = isSelected(currentDestination, "register") ,
                    onClick = { navController.navigate("register") },
                    icon = {
                        Icon(imageVector = Icons.Filled.AddCircle,
                            contentDescription = "")
                    })

                BottomNavigationItem(
                    selected = isSelected(currentDestination, "about"),
                    onClick = { navController.navigate("about") },
                    icon = {
                        Icon(imageVector = Icons.Outlined.Info,
                            contentDescription = "")
                    })

            }
        }

    ) {
        Column(modifier = Modifier.padding(it)) {

            NavHost(navController = navController, startDestination = "profile") {
                composable("profile") {
                    Profile(onProfile = {
                        navController.navigate("register")
                    })
                }
                composable("about") {
                    About()
                }
                composable("register") {
                    Register(
                        onAbout = {
                            navController.navigate("about")
                        },
                        onBack = {
                            navController.navigateUp()
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Navegacao2024Theme {
        Greeting("Android")
    }
}