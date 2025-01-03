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
import androidx.compose.ui.unit.sp
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
}
@Composable
fun FirstScreen() {
    val context = LocalContext.current
    val imageModifier = Modifier.size(1000.dp)
    Image(
        painter = painterResource(id = R.drawable.park),
        contentDescription = null,
        contentScale = ContentScale.FillHeight,
        modifier = imageModifier.background(Color.White.copy(alpha = 0.7f))
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "環保小幫手",
            fontSize = 60.sp,
            modifier = Modifier.padding(top = 16.dp),
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(80.dp))

        Button(onClick = {
            val intent = Intent(context, EducationActivity::class.java)
            context.startActivity(intent)
        },
            colors = buttonColors(Color.Red)) {
            Text(text = "教育模式")
            Image(
                painterResource(id = R.drawable.educationbutton),
                contentDescription ="button icon",
                modifier = Modifier.size(60.dp)
            )

        }
        Spacer(modifier = Modifier.height(70.dp))
        Button(onClick = {
            val intent = Intent(context, GameActivity::class.java)
            context.startActivity(intent)
        },
            colors = buttonColors(Color.Red)) {
            Text(text = "遊戲模式")
            Image(
                painterResource(id = R.drawable.gamebutton),
                contentDescription ="button icon",
                modifier = Modifier.size(60.dp)
            )

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
    ) {
        Text(
            text = "環保小幫手",
            fontSize = 60.sp,
            modifier = Modifier.padding(top = 16.dp),
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(80.dp))

        Button(onClick = {
            val intent = Intent(context, EducationActivity::class.java)
            context.startActivity(intent)
        },
            colors = buttonColors(Color.Red)) {
            Text(text = "教育模式")
            Image(
                    painterResource(id = R.drawable.educationbutton),
            contentDescription ="button icon",
            modifier = Modifier.size(60.dp)
            )

        }
        Spacer(modifier = Modifier.height(70.dp))
        Button(onClick = {
            val intent = Intent(context, GameActivity::class.java)
            context.startActivity(intent)
        },
            colors = buttonColors(Color.Red)) {
            Text(text = "遊戲模式")
            Image(
                    painterResource(id = R.drawable.gamebutton),
            contentDescription ="button icon",
            modifier = Modifier.size(60.dp)
            )
        }
        }
    }

/*
@Composable
fun ThirdScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "環保小幫手",
            fontSize = 60.sp,
            modifier = Modifier.padding(top = 16.dp),
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(80.dp))

        Button(onClick = {
            val intent = Intent(context, EducationActivity::class.java)
            context.startActivity(intent) },
            colors = buttonColors(Color.Red)
        ) {
            Text(text = "教育模式")
            Image(
                painterResource(id = R.drawable.educationbutton),
                contentDescription ="button icon",
                modifier = Modifier.size(60.dp)
            )
        }
        Spacer(modifier = Modifier.height(70.dp))
        Button(onClick = {
            val intent = Intent(context, GameActivity::class.java)
            context.startActivity(intent)
        },
            colors = buttonColors(Color.Red)) {
            Text(text = "遊戲模式")
            Image(
                painterResource(id = R.drawable.gamebutton),
                contentDescription ="button icon",
                modifier = Modifier.size(60.dp)
            )

        }
        Spacer(modifier = Modifier.height(70.dp))
        Button(onClick = {
            val intent = Intent(context, TestActivity::class.java)
            context.startActivity(intent)
        },
            colors = buttonColors(Color.Red)) {
            Text(text = "測驗模式")
            Image(
                painterResource(id = R.drawable.testbutton),
                contentDescription ="button icon",
                modifier = Modifier.size(60.dp)
            )

        }
    }
}*/