package com.example.firstapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier
                .background(Color.Gray)
                .padding(20.dp)
        ) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CardTop(Modifier.weight(1F))
                CardMiddle(fullName, studentId, Modifier.weight(8F))
                CardBottom(Modifier.weight(1F))
            }
        }
    }
}

@Composable
fun CardTop(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize()) {
        Text(
            text = "North Dakota State University",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}

@Composable
fun CardMiddle(fullName: String, studentId: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AvatarIcon()
        CardDetails(fullName, studentId, Modifier.padding(top = 8.dp))
    }
}

@Composable
fun CardBottom(modifier: Modifier = Modifier) {
    Box(modifier) {
        Column(
            modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(Icons.Rounded.Email, contentDescription = null)
                Text(text = stringResource(R.string.sujan_maharjan_1_ndsu_edu))
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Icon(Icons.Rounded.Phone, contentDescription = null)
                Text(text = stringResource(R.string._415_819_3043))
            }
        }
    }
}

@Composable
fun AvatarIcon() {
    val image = painterResource(R.drawable.android_logo)
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier
            .height(100.dp)
            .border(4.dp, Color.Black, RoundedCornerShape(8.dp))
            .padding(4.dp)
    )
}

@Composable
fun CardDetails(fullName: String, studentId: String, modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = fullName,
            fontSize = 32.sp,
            lineHeight = 32.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = studentId,
            fontSize = 20.sp,
            lineHeight = 20.sp,
            modifier = Modifier.padding(top = 8.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun StudentCardPreview() {
    FirstApplicationTheme {
        StudentCard(stringResource(R.string.sujan_maharjan), stringResource(R.string.student_id))
    }
}