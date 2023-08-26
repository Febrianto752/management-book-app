package com.example.managebookapp

import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.managebookapp.screens.BookDetail
import com.example.managebookapp.screens.BookListCard
import com.example.managebookapp.screens.LoginScreen
import com.example.managebookapp.screens.Registration
import com.example.managebookapp.ui.theme.ManageBookAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ManageBookAppTheme {
                MainScreen()
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
    ManageBookAppTheme {
        Greeting("Android")
    }
}


@ExperimentalMaterial3Api
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(Routes.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(Routes.Register.route) {
            Registration(navController = navController)
        }

        composable(Routes.Home.route) {
            BookListCard(navController = navController)
        }

        composable(Routes.BookDetail.route) { backStackEntry ->
            BookDetail(
                navController = navController,
                backStackEntry.arguments?.getString("bookIndex")?.toIntOrNull()
            )
        }

    }
}