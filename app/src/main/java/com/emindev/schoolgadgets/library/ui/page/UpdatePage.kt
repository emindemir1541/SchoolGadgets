package com.emindev.schoolgadgets.library.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emindev.schoolgadgets.R
import com.emindev.schoolgadgets.library.ui.LocalColor

@Composable
fun UpdatePage(forceUpdate: Boolean = true, show: MutableState<Boolean>) {

    Box(modifier = Modifier.background(LocalColor.updateScreenBackColor)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                if (!forceUpdate)
                    IconButton(onClick = { show.value = false }, modifier = Modifier
                        .size(24.dp))
                    {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "close icon", tint = LocalColor.updateScreeForegroundColor)
                    }
            }

            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(70.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(painter = painterResource(id = R.drawable.update_icon), contentDescription = "updateLesson icon", tint = LocalColor.updateScreeForegroundColor)
                    Text(text = "Time to Update!", color = LocalColor.updateScreeForegroundColor, fontSize = 20.sp)
                    Button(onClick = {/*TODO: go to updateLesson link */ }, colors = ButtonDefaults.buttonColors(containerColor = LocalColor.updateButtonBackColor, contentColor = LocalColor.updateButtonTextColor)) {
                        Text(text = "Update Now")
                    }
                }

            }
        }
    }
}