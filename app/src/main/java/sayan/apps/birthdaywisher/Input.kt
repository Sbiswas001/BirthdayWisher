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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Suppress("ktlint:standard:function-naming")
@Composable
fun DisplayName() {
    val name = remember { mutableStateOf("") }
    val answer = remember { mutableStateOf("") }
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
                OutlinedTextField(
                    value = name.value,
                    onValueChange = { name.value = it },
                    label = {
                    Text(text = "Enter your name", color = Color.Black)
                    },
                    textStyle = TextStyle(color = Color.Black)
                )
                Row(
                    modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Button(onClick = { answer.value = "Hi, " + name.value }) {
                        Text(text = "Submit")
                    }
                    Button(onClick = {
                        answer.value = "";
                        name.value = "";
                        keyboardController?.hide()
                    }) {
                        Text(text = "Clear")
                    }
                }
                Text(text = answer.value)
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun DisplayNamePreview() {
    DisplayName()
}
