package com.example.s1120325

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.s1120325.ui.theme.S1120325Theme

class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            S1120325Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    /*Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*/
                    Main()
                }
            }
        }
    }
}

@Composable
fun Main() {
    val context = LocalContext.current  //取得App的運行環境
    Column {
        Button(
            onClick = {
                var it = Intent(context,MainActivity::class.java)
                context.startActivity(it)
            })
        {
            Text(text = "回到主畫面")
        }
    }
}