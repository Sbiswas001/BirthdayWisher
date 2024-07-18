package sayan.apps.birthdaywisher

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Suppress("ktlint:standard:function-naming")
@Composable
fun DisplayName(navController: NavController) {
    val name = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.LightGray,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Birthday Card\n Generator",
                    color = Color.Black,
                    fontSize = 50.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 50.sp,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(bottom = 100.dp),
                )
                OutlinedTextField(
                    value = name.value,
                    onValueChange = { name.value = it },
                    label = {
                        Text(text = "Enter your name", color = Color.Black)
                    },
                    textStyle = TextStyle(color = Color.Black),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        cursorColor = Color.Black
                    ),
                    singleLine = true
                )
                Row(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Button({ navController.navigate("BirthdayCard/${name.value}") }) {
                        Text(text = "Submit")
                    }
                    Button(onClick = {
                        name.value = ""
                        keyboardController?.hide()
                    }) {
                        Text(text = "Clear")
                    }
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun DisplayNamePreview() {
    DisplayName(navController = NavController(context = LocalContext.current))
}
