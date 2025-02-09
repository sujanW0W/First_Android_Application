package com.example.firstapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstapplication.ui.theme.FirstApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstApplicationTheme {
                StudentCard(
                    fullName = stringResource(R.string.sujan_maharjan),
                    studentId = stringResource(R.string.student_id),
                    modifier = Modifier.padding()
                )
            }
        }
    }
}

@Composable
fun StudentCard(fullName: String, studentId: String, modifier: Modifier = Modifier) {
    Surface(color = MaterialTheme.colorScheme.background, contentColor = Color.Green, modifier = modifier.fillMaxSize()) {
        Box(modifier) {
            AvatarIcon()
            CardDetails(fullName, studentId, modifier = modifier)
        }
    }
}

@Composable
fun CardDetails(fullName: String, studentId: String, modifier: Modifier) {
        Column(modifier = Modifier
            .padding(24.dp)
            .fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Hello $fullName!",
                fontSize = 44.sp,
                lineHeight = 48.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = studentId,
                fontSize = 36.sp,
                lineHeight = 44.sp,
                modifier = modifier.padding(top = 8.dp)
            )
            Text(
                text = "NDSU",
                modifier = modifier.align(Alignment.End)
            )
        }
}

@Composable
fun AvatarIcon() {
    val image = painterResource(R.drawable.background_android)
    Image(
        painter = image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        alpha = 0.5F
    )
}

@Preview(showBackground = true)
@Composable
fun StudentCardPreview() {
    FirstApplicationTheme {
        StudentCard(stringResource(R.string.sujan_maharjan), stringResource(R.string.student_id))
    }
}