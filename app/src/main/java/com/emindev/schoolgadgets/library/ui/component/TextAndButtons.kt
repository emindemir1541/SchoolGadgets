package com.emindev.schoolgadgets.library.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.emindev.schoolgadgets.gradecalculator.ui.components.IconButton

@Composable
fun TextFieldButton(
    modifier: Modifier = Modifier.size(width = 160.dp, height = 60.dp),
    editMode: MutableState<Boolean> = remember { mutableStateOf(false) },
    text: MutableState<TextFieldValue>,
    borderStroke: BorderStroke = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
    shape: Shape = RoundedCornerShape(20.dp),
    doneButton: () -> Unit = {},
    addButton: () -> Unit = {}
) {
    if (editMode.value)
        Row(modifier = Modifier.border(borderStroke, shape = shape), horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
            TextField(value = text.value, onValueChange = { text.value = it }, colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent, focusedIndicatorColor = Color.Transparent, disabledIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent), modifier = modifier, textStyle = TextStyle(textAlign = TextAlign.Center))
            IconButton(imageVector = Icons.Default.Done, description = "Done") { doneButton();editMode.value = false }
        }
    else
        TextButton(onClick = { addButton(); editMode.value = true }, modifier = modifier, shape = shape) { Text(text = text.value.text) }
}