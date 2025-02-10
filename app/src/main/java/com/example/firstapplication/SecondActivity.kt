package com.example.firstapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstapplication.ui.theme.FirstApplicationTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Challenges()
                }
            }
        }
    }
}

@Composable
fun Challenges(modifier: Modifier = Modifier) {
    val localContext = LocalContext.current
    val challenges = listOf(
        "Device Fragmentation",
        "OS Fragmentation",
        "Unstable and Dynamic environment",
        "Rapid Changes",
        "Low Tolerance",
        "Tool Support"
    )
    Box(
        modifier
            .background(Color.Gray)
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Mobile Software Engineering Challenges",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 32.sp,
                    textDecoration = TextDecoration.Underline
                )
                ChallengesList(challenges)
            }
            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                Button(
                    onClick = {
                        val intent = Intent(
                            localContext,
                            MainActivity::class.java
                        )
//                        val intent = Intent().apply {
//                            action = "android.intent.action.MAIN"
//                            putExtra("ExtraType", "Main Activity")
//                        }
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
fun ChallengesList(challenges: List<String>) {
    LazyColumn(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(challenges.size, key = { index -> index }) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.AutoMirrored.Rounded.ArrowForward, contentDescription = null)
                Text(
                    text = challenges[it],
                    fontSize = 22.sp,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstApplicationTheme {
        Challenges()
    }
}