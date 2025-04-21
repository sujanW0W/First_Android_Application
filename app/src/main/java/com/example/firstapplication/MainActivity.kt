package com.example.firstapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.firstapplication.ui.theme.FirstApplicationTheme

private const val customPermission = "com.example.firstapplication.MSE712"
private var permissionRequestCode = 1

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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == permissionRequestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startSecondActivityExplicitly(this)
            } else {
                Toast.makeText(this, "Permission Denied!\nPlease allow it from settings.", Toast.LENGTH_SHORT).show()
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
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        IdBlock(fullName, studentId)
        ActivityButtons()
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
                Text(text = stringResource(R.string._phone))
            }
        }
    }
}

@Composable
fun IdBlock(fullName: String, studentId: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AvatarIcon()
        CardDetails(fullName, studentId, Modifier.padding(top = 8.dp))
    }
}

@Composable
fun ActivityButtons() {
    val localContext = LocalContext.current
    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        Button(
            onClick = {
                if (ContextCompat.checkSelfPermission(
                        localContext,
                        customPermission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        localContext as Activity,
                        arrayOf(customPermission),
                        permissionRequestCode
                    )
                } else {
                    startSecondActivityExplicitly(
                        localContext
                    )
                }
            },
            Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Start Activity Explicitly"
            )
        }
        Button(
            onClick = {
                if (ContextCompat.checkSelfPermission(
                        localContext,
                        customPermission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        localContext as Activity,
                        arrayOf(customPermission),
                        permissionRequestCode
                    )
                } else {
                    val intent = Intent().apply {
                        action = "android.intent.action.SecondActivity"
                        putExtra("ExtraData", "SecondActivity")
                    }
                    localContext.startActivity(intent)
                }
            },
            Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Start Activity Implicitly"
            )
        }
        Button(

            onClick = {
                val intent = Intent(localContext, ImageActivity::class.java)
                localContext.startActivity(intent)
            },
            Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            Text(
                text = "View Image Activity"
            )
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
fun CardDetails(fullName: String, studentId: String, modifier: Modifier = Modifier) {
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

fun startSecondActivityExplicitly(localContext: Context) {
    val intent = Intent(localContext, SecondActivity::class.java)
    localContext.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun StudentCardPreview() {
    FirstApplicationTheme {
        StudentCard(stringResource(R.string.sujan_maharjan), stringResource(R.string.student_id))
    }
}