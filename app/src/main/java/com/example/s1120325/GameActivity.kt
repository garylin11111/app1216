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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.s1120325.ui.theme.S1120325Theme
import kotlin.math.roundToInt

class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            S1120325Theme {
                Main()
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
    // 垃圾與垃圾桶的對應關係
    val trashItems = listOf(
        R.drawable.plastic to 0,  // plastic -> trashcan
        R.drawable.bottle to 1,  // bottle -> recyclebin
        R.drawable.glass to 1,   // glass -> recyclebin
        R.drawable.banana to 2,  // banana -> forfruits
        R.drawable.battery to 3  // battery -> cantrecycle
    )

    val trashBins = listOf(
        R.drawable.trashcan,    // trashcan
        R.drawable.recyclebin,  // recyclebin
        R.drawable.forfruits,   // forfruits
        R.drawable.cantrecycle  // cantrecycle
    )

    var currentTrash by remember { mutableStateOf(trashItems.random()) }
    var isTrashVisible by remember { mutableStateOf(true) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val trashBinRects = remember { mutableStateListOf<Rect>() }

    Box(modifier = Modifier.fillMaxSize()
        .background(Color.LightGray)
    ) {
        // 顯示垃圾桶
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            trashBins.chunked(2).forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    row.forEach { binResId ->
                        var binRect by remember { mutableStateOf(Rect()) }
                        Image(
                            painter = painterResource(id = binResId),
                            contentDescription = "Bin",
                            modifier = Modifier
                                .size(100.dp)
                                .offset(x = 40.dp)
                                .onGloballyPositioned {
                                    val bounds = it.boundsInWindow()
                                    binRect = Rect(
                                        bounds.left.roundToInt(),
                                        bounds.top.roundToInt(),
                                        bounds.right.roundToInt(),
                                        bounds.bottom.roundToInt()
                                    )
                                    trashBinRects.add(binRect)
                                }
                        )
                    }
                }
            }
        }

        // 顯示垃圾
        if (isTrashVisible) {
            Image(
                painter = painterResource(id = currentTrash.first),
                contentDescription = "Trash",
                modifier = Modifier
                    .size(100.dp)
                    .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDrag = { change, dragAmount ->
                                change.consume()
                                offsetX += dragAmount.x
                                offsetY += dragAmount.y
                            },
                            onDragEnd = {
                                // 檢查碰撞
                                val trashRect = Rect(
                                    offsetX.roundToInt(),
                                    offsetY.roundToInt(),
                                    (offsetX + 140.dp.toPx()).roundToInt(),
                                    (offsetY + 140.dp.toPx()).roundToInt()
                                )

                                val correctBinIndex = currentTrash.second
                                if (correctBinIndex < trashBinRects.size &&
                                    Rect.intersects(trashRect, trashBinRects[correctBinIndex])
                                ) {
                                    // 碰撞成功
                                    isTrashVisible = false
                                    currentTrash = trashItems.random()
                                    offsetX = 0f
                                    offsetY = 0f
                                    isTrashVisible = true
                                } else {
                                    // 碰撞失敗，重置位置
                                    offsetX = 0f
                                    offsetY = 0f
                                }
                            }
                        )
                    }
            )
        }
        Text(text = "垃圾分類",
            fontSize = 60.sp,
            modifier = Modifier.align(Alignment.Center),
            color = Color.Black
        )
        Image(
            painterResource(id = R.drawable.recyclesymbol),
            contentDescription ="分類",
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}