package sayan.apps.birthdaywisher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import sayan.apps.birthdaywisher.ui.theme.BirthdayWisherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BirthdayWisherTheme {
                DisplayName()
            }
        }
    }
}
