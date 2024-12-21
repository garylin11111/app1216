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
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


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
    val cards = listOf(
        R.drawable.banana,
        R.drawable.bottle,
        R.drawable.glass,
        R.drawable.plastic,
        R.drawable.battery
    )
    val messages = listOf(
        "香蕉(廚餘)",
        "寶特瓶(塑膠類回收)",
        "酒瓶(玻璃 回收)",
        "塑膠袋(一般垃圾)",
        "電池(不可回收垃圾)"
    )
    var currentCardIndex by remember { mutableStateOf(0) }
    var message by remember { mutableStateOf(messages[currentCardIndex]) }

    var totalDrag by remember { mutableStateOf(0f) } // 記錄拖曳總距離


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
                Text(
                    text = messages[currentCardIndex],
                    modifier = Modifier.padding(top = 400.dp),
                    fontSize = 40.sp
                )
                Image(
                    painter = painterResource(id = cards[currentCardIndex]),
                    contentDescription = "分類卡圖片",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(250.dp)
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDrag = { change, dragAmount ->
                                    totalDrag += dragAmount.x // 累積水平方向拖曳距離
                                    change.consume() // 消耗拖曳事件
                                },
                                onDragEnd = {
                                    val dragThreshold = 100f // 定義切換圖片的最小拖曳距離
                                    if (totalDrag > dragThreshold) {
                                        currentCardIndex = (currentCardIndex + 1) % cards.size
                                    } else if (totalDrag < -dragThreshold) {
                                        currentCardIndex = if (currentCardIndex - 1 < 0) cards.size - 1 else currentCardIndex - 1
                                    }
                                    message = messages[currentCardIndex]
                                    totalDrag = 0f // 重置拖曳距離
                                },
                                onDragCancel = {
                                    totalDrag = 0f // 重置拖曳距離
                                }
                            )
                        }
                )

            }
        }
    }
}