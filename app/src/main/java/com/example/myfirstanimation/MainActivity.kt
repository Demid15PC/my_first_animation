package com.example.myfirstanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val fontFamily = FontFamily(
                Font(R.font.unbounded_black, FontWeight.Black),
                Font(R.font.unbounded_bold, FontWeight.Bold),
                Font(R.font.unbounded_extrabold, FontWeight.ExtraBold),
                Font(R.font.unbounded_light, FontWeight.Light),
                Font(R.font.unbounded_medium, FontWeight.Medium),
                Font(R.font.unbounded_extralight, FontWeight.ExtraLight),
                Font(R.font.unbounded_regular, FontWeight.Normal),
                Font(R.font.unbounded_semibold, FontWeight.SemiBold),
            )

            var sizeState by remember {
                mutableStateOf(200.dp)
            }
            val size by animateDpAsState(
                targetValue = sizeState,
//                tween(
//                    durationMillis = 3000,
//                    delayMillis = 300,
//                    easing = FastOutSlowInEasing
//                )

                spring(
                    Spring.DampingRatioLowBouncy
                )
            )
            val infiniteTransition = rememberInfiniteTransition()
            val color by infiniteTransition.animateColor(
                initialValue = Color.DarkGray,
                targetValue = Color.Blue,
                animationSpec = infiniteRepeatable(
                    tween(durationMillis = 2000),
                    repeatMode = RepeatMode.Reverse
                )
            )
            Box(
                modifier = Modifier
                    .size(size)
                    .background(color)
                    .clickable {
                        if (sizeState == 400.dp)
                            sizeState -= 200.dp
                        else
                            sizeState += 200.dp
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Press for zoom",
                    fontFamily = fontFamily,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
    }
}