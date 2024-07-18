package sayan.apps.birthdaywisher

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun Dp.toPx(): Float = (this.value * Resources.getSystem().displayMetrics.density)

@Suppress("ktlint:standard:function-naming")
@Composable
fun BirthdayCard(name: String) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(ImageDecoderDecoder.Factory())
        }
        .build()

    val context = LocalContext.current
    val view = LocalView.current
    var showShareButton by remember { mutableStateOf(true) }
    val shareButtonHeight = 80.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            Image(
                painter = rememberAsyncImagePainter(R.drawable.confetti, imageLoader),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = shareButtonHeight), // Make space for the button
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Happy Birthday\nto you!",
                    fontSize = 36.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 50.sp,
                    color = Color.DarkGray,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(bottom = 30.dp),
                )

                Text(
                    text = name,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 50.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Black,
                )
                Image(
                    painter = painterResource(id = R.drawable.cake),
                    contentDescription = stringResource(id = R.string.cake_description),
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                )
            }
        }

        if (showShareButton) {
            Button(
                onClick = {
                    showShareButton = false
                    view.post {
                        val shareButtonHeightPx = shareButtonHeight.toPx().toInt()
                        val bitmap = captureScreenshot(view, shareButtonHeightPx)
                        if (bitmap != null) {
                            shareBitmap(context, bitmap)
                        } else {
                            Toast.makeText(
                                context,
                                "Failed to capture screenshot",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        showShareButton = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("Share")
            }
        }
    }
}

private fun captureScreenshot(view: View, shareButtonHeight: Int): Bitmap? {
    return try {
        val fullBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(fullBitmap)
        view.draw(canvas)

        // Crop out the share button
        val croppedBitmap = Bitmap.createBitmap(
            fullBitmap,
            0, 0,
            view.width,
            view.height - shareButtonHeight
        )

        fullBitmap.recycle() // Recycle the original bitmap to free up memory
        croppedBitmap
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

private fun shareBitmap(context: Context, bitmap: Bitmap) {
    val file = File(context.cacheDir, "images")
    file.mkdirs()
    val fileName = "shared_image.png"
    val imageFile = File(file, fileName)
    val fileOutputStream: FileOutputStream
    try {
        fileOutputStream = FileOutputStream(imageFile)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        fileOutputStream.flush()
        fileOutputStream.close()

        val contentUri: Uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider", // Ensure this matches the provider authority
            imageFile
        )

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, contentUri)
            type = "image/png"
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        context.startActivity(
            Intent.createChooser(shareIntent, "Share image via")
        )
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    BirthdayCard(name = "Sayan Biswas")
}
