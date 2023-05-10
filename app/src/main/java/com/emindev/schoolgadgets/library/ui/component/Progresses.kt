package com.emindev.schoolgadgets.library.ui

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.emindev.schoolgadgets.library.common.util.Progress

@SuppressLint("SuspiciousIndentation")
@Composable
fun BoxProgress(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    progress: MutableState<String> = remember { mutableStateOf(Progress.NOT_STARTED) },
    content: @Composable BoxScope.() -> Unit,
) {
    val transition = updateTransition(targetState = progress, null)
    val transitionColor by transition.animateColor(
        transitionSpec = { tween(400) },
        label = "color",
        targetValueByState = { isProgressing -> if (isProgressing.value == Progress.PROGRESSING) if (isSystemInDarkTheme()) LocalColor.transparentBlack else LocalColor.transparentWhite else Color.Transparent })

    Box(modifier = modifier, contentAlignment = contentAlignment, propagateMinConstraints = propagateMinConstraints) {

        if (progress.value == Progress.PROGRESSING) {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxSize()
                .background(transitionColor)) {

                ProgressCircle()
            }
        }
        else
            content()


    }
}


@Composable
fun ProgressCircle(color: Color = MaterialTheme.colorScheme.primary) {
    CircularProgressIndicator(color = color)
}

@Composable
@Preview
private fun Preview() {
    ProgressCircle()
}
