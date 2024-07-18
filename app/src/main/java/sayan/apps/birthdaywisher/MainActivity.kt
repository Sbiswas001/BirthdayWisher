package sayan.apps.birthdaywisher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import sayan.apps.birthdaywisher.ui.theme.BirthdayWisherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BirthdayWisherTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "input") {
                    composable("Input") {
                        DisplayName(navController = navController)
                    }
                    composable("BirthdayCard/{name}") { backStackEntry ->
                        BirthdayCard(name = backStackEntry.arguments?.getString("name") ?: "")
                    }
                }
            }
        }
    }
}
