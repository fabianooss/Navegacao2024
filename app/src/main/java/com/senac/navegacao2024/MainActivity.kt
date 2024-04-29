package com.senac.navegacao2024

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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

@Composable
fun MyApp() {
    val navController = rememberNavController()

    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            Text(text = "Texto fixo")



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