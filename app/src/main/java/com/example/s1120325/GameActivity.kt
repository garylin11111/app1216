package com.example.s1120325

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.s1120325.ui.theme.S1120325Theme
import kotlinx.coroutines.delay
import kotlin.math.roundToInt
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned



class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            S1120325Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
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
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = {
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "回到主畫面")
            }
        }

        DragAndDropGame()
    }
}
    @Composable
    fun DragAndDropGame() {
        val trashItems = listOf(
            R.drawable.plastic to 0,
            R.drawable.bottle to 1,
            R.drawable.banana to 2,
            R.drawable.battery to 3,
            R.drawable.glass to 4
        )

        val trashBinMappings = listOf(
            Pair(R.drawable.recyclebin, 0),
            Pair(R.drawable.trashcan, 1),
            Pair(R.drawable.cantrecycle, 2),
            Pair(R.drawable.forfruits, 3)
        )

        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }
        var currentTrash by remember { mutableStateOf(trashItems.random()) } // 包含垃圾圖與正確索引
        var isTrashVisible by remember { mutableStateOf(true) }

        val binPositions = remember { mutableStateOf(mutableMapOf<Int, Rect>()) }

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                trashBinMappings.chunked(2).forEach { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        rowItems.forEach { (binImage, index) ->
                            Image(
                                painter = painterResource(id = binImage),
                                contentDescription = "Bin",
                                modifier = Modifier
                                    .size(100.dp)
                                    .onGloballyPositioned { layoutCoordinates ->
                                        val bounds = layoutCoordinates.boundsInWindow()
                                        binPositions.value[index] = Rect(
                                            bounds.left.roundToInt(),
                                            bounds.top.roundToInt(),
                                            bounds.right.roundToInt(),
                                            bounds.bottom.roundToInt()
                                        )
                                    }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            if (isTrashVisible) {
                Image(
                    painter = painterResource(id = currentTrash.first),
                    contentDescription = "Trash",
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center)
                        .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDrag = { change, dragAmount ->
                                    change.consume()
                                    offsetX += dragAmount.x
                                    offsetY += dragAmount.y
                                },
                                onDragEnd = {
                                    val trashRect = Rect(
                                        offsetX.roundToInt(),
                                        offsetY.roundToInt(),
                                        (offsetX + 100).roundToInt(),
                                        (offsetY + 100).roundToInt()
                                    )

                                    val correctBinIndex = currentTrash.second
                                    val binRect = binPositions.value[correctBinIndex]

                                    if (binRect != null && Rect.intersects(trashRect, binRect)) {
                                        // 正確拖曳
                                        isTrashVisible = false
                                        offsetX = 0f
                                        offsetY = 0f
                                        currentTrash = trashItems.random() // 換下一個垃圾

                                    } else {
                                        // 重置位置
                                        offsetX = 0f
                                        offsetY = 0f
                                    }
                                }
                            )
                        }
                )
            }
        }
    }
