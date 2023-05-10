package com.emindev.schoolgadgets.gradecalculator.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource

@Composable
internal fun IconButton(imageVector: ImageVector, description: String, modifier: Modifier = Modifier, iconColor: Color = LocalContentColor.current, onClick: () -> Unit) {
    IconButton(modifier = modifier, onClick = onClick) {
        Icon(imageVector = imageVector, contentDescription = description, tint = iconColor)
    }
}

@Composable
internal fun ButtonCalculate(onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier.fillMaxWidth(0.8f)) {
        Text(text = stringResource(id = com.emindev.schoolgadgets.R.string.calculate))
    }
}

@Composable
internal fun ButtonAdd(onClick: () -> Unit) {
    OutlinedButton(modifier = Modifier.fillMaxWidth(0.8f), onClick = onClick) {
        Text(text = stringResource(id = com.emindev.schoolgadgets.R.string.add))
    }
}
