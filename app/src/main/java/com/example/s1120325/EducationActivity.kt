package com.example.s1120325

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.s1120325.ui.theme.S1120325Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.geometry.Offset



class EducationActivity : ComponentActivity() {
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
                    Education()
                }
            }
        }
    }
}
@Composable
fun Education() {
    val context = LocalContext.current  // 取得App的運行環境
    var msg by remember { mutableStateOf("垃圾分類知識卡") }
    var offset1 by remember { mutableStateOf(Offset.Zero) }
    var offset2 by remember { mutableStateOf(Offset.Zero) }
    var card = arrayListOf(R.drawable.card1, R.drawable.card2, R.drawable.card3, R.drawable.card4, R.drawable.card5)
    var Number by remember { mutableStateOf(1) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {

        Button(
            onClick = {
                val it = Intent(context, MainActivity::class.java)
                context.startActivity(it)
            }
        ) {
            Text(text = "回到主畫面")
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "\n$msg"
            )

            Box(
                modifier = Modifier.size(700.dp), // 圖片重疊區域大小
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.edbackground),
                    contentDescription = "分類卡背景",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(500.dp)
                )

                Image(
                    painter = painterResource(id= card[Number]),
                    contentDescription = "分類卡圖片",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(200.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = {
                                    if (msg == "垃圾分類知識卡"){
                                    msg = "香蕉(廚餘)"
                                }
                                else{
                                    msg = "垃圾分類知識卡"
                                }
                                }
                                ,
                            )
                            detectDragGesturesAfterLongPress(
                                onDrag = { change, dragAmount -> offset2+=dragAmount},
                                onDragStart = {
                                    offset1 = it
                                    offset2 = it },
                                onDragEnd = {
                                    if (offset2.x >= offset1.x){
                                    msg = "長按後向右拖曳"
                                    Number ++
                                    if (Number>5){Number=0}
                                }
                                else{
                                    msg = "長按後向左拖曳"
                                    Number --
                                    if (Number<0){Number=5}
                                }}
                            )

                        }

                )
            }
        }
    }
}
