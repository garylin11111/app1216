package com.example.s1120325

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.s1120325.ui.theme.S1120325Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            S1120325Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    /*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*/
                    FirstScreen()
                    SecondScreen()
                }
            }
        }
    }
}@Composable
fun FirstScreen() {
    val context = LocalContext.current
    val imageModifier = Modifier.size(1000.dp)
    Image(
        painter = painterResource(id = R.drawable.park),
        contentDescription = null,
        contentScale = ContentScale.FillHeight,
        modifier = imageModifier
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            val intent = Intent(context, EducationActivity::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "教育模式")
        }
        Spacer(modifier = Modifier.height(20.dp)) // 按鈕之間的間距
        Button(onClick = {
            val intent = Intent(context, GameActivity::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "遊戲模式")
        }
    }
}

@Composable
fun SecondScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(onClick = {
            val intent = Intent(context, EducationActivity::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "教育模式")
        }
        Spacer(modifier = Modifier.height(20.dp)) // 按鈕之間的間距
        Button(onClick = {
            val intent = Intent(context, GameActivity::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "遊戲模式")
        }
    }
}
