package com.example.firstapplication

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstapplication.ui.theme.FirstApplicationTheme

class ImageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstApplicationTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp, top = 32.dp)
                ) {
                    ImageScreen()
                }
            }
        }
    }
}

@Composable
fun ImageScreen(modifier: Modifier = Modifier) {
    val localContext = LocalContext.current

    var capturedImage by remember {
        mutableStateOf<Bitmap?>(null)
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == android.app.Activity.RESULT_OK) {
            val image = result.data?.extras?.get("data") as Bitmap
            capturedImage = image
        }
    }
    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Image Activity",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)

        ) {
            Button(
                onClick = {
                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    cameraLauncher.launch(cameraIntent)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Capture Image"
                )
            }
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(8F)
                    .fillMaxWidth()
                    .border(4.dp, Color.Black, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                if (capturedImage is Bitmap) {
                    Image(
                        bitmap = capturedImage!!.asImageBitmap(),
                        contentDescription = "Captured Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                } else {
                    Text(
                        text = "Image Preview"
                    )
                }
            }
            Column {
                Button(
                    onClick = {
                        capturedImage = null
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Reset"
                    )
                }
                Button(
                    onClick = {
                        val intent = Intent(localContext, MainActivity::class.java)
                        localContext.startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Main Activity"
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ImageScreenPreview() {
    FirstApplicationTheme {
        ImageScreen()
    }
}