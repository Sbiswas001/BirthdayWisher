package sayan.apps.birthdaywisher

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Suppress("ktlint:standard:function-naming")
@Composable
fun BirthdayCard() {
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
                    text = "Happy Birthday\nto you",
                    fontSize = 36.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 50.sp,
                    color = Color.DarkGray,
                    fontStyle = FontStyle.Italic,
                )
                Row(
                    modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "ChatGPT",
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 50.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Black,
                    )
                    Text(
                        text = "Gemini",
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 50.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Black,
                    )
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    BirthdayCard()
}
