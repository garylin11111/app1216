package com.example.s1120325

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.s1120325.ui.theme.S1120325Theme
import kotlin.math.roundToInt

class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            S1120325Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main()
                }
            }
        }
    }
}

@Composable
fun Main() {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "回到主畫面")
        }

        DragAndDropGame()
    }
}
@Composable
fun DragAndDropGame() {
    // 記錄圖片的位置和拖曳狀態
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var targetPositions by remember { mutableStateOf(listOf<IntOffset>()) }
    var resultMessage by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        // 三個目標區域
        val areas = listOf(R.drawable.recyclebin, R.drawable.trashcan, "Area 3")
        targetPositions = areas.mapIndexed { index, _ ->
            var position by remember { mutableStateOf(IntOffset(0, 0)) }
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Green)
                    .offset(y = (index * 150).dp)
                    .onGloballyPositioned {
                        position = IntOffset(
                            it.boundsInWindow().left.roundToInt(),
                            it.boundsInWindow().top.roundToInt()
                        )
                    }
            ) {

            }
            position
        }

        // 可拖曳的圖片
        Image(
            painter = painterResource(id = R.drawable.card1),
            contentDescription = "Draggable Image",
            modifier = Modifier
                .size(100.dp)
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y

                        // 檢查是否拖到任一目標區域
                        val matchedIndex = targetPositions.indexOfFirst { target ->
                            offsetX.roundToInt() in target.x..target.x + 100 &&
                                    offsetY.roundToInt() in target.y..target.y + 100
                        }

                        resultMessage = when (matchedIndex) {
                            0 -> "對"
                            1 -> "錯"
                            2 -> "對"
                            else -> ""
                        }
                    }
                }
        )

        // 顯示結果
        if (resultMessage.isNotEmpty()) {
            BasicText(
                text = resultMessage,
                modifier = Modifier
            )
        }
    }
}