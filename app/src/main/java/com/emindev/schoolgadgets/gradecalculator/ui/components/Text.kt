package com.emindev.schoolgadgets.gradecalculator.ui.components

import android.view.KeyEvent.ACTION_DOWN
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun OutlinedTextField(label: String, text: MutableState<TextFieldValue>, keyboardType: KeyboardType = KeyboardType.Text, isError: MutableState<Boolean> = remember { mutableStateOf(false) }) {
    OutlinedTextField(value = text.value, onValueChange = { text.value = it }, label = { Text(text = label) }, keyboardOptions = KeyboardOptions(keyboardType = keyboardType), modifier = Modifier.fillMaxWidth(), isError = isError.value, colors = TextFieldDefaults.outlinedTextFieldColors(), trailingIcon = { if (isError.value) Icon(Icons.Filled.Warning, "error", tint = MaterialTheme.colorScheme.error) })
}


@Composable
@Preview
internal fun TextFieldEditable(
    modifier: Modifier = Modifier,
    editMode: MutableState<Boolean> = remember { mutableStateOf(true) },
    text: MutableState<TextFieldValue> = remember { mutableStateOf(TextFieldValue("kjjhn")) },
    borderStroke: BorderStroke = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
    isError: MutableState<Boolean> = remember { mutableStateOf(true) },
    onValueChange: () -> Unit = {}
) {
    if (editMode.value) TextFieldStandard(text = text, modifier = modifier, shape = RoundedCornerShape(20.dp), isError = isError)
    else
        Box(modifier = modifier./*border(borderStroke, shape = RoundedCornerShape(6.dp)).*/padding(8.dp), contentAlignment = Alignment.Center) {
            Text(text = text.value.text)
        }
}


@Composable
internal fun TextFieldStandard(text: MutableState<TextFieldValue>, modifier: Modifier, shape: Shape = RoundedCornerShape(20.dp), isError: MutableState<Boolean> = remember { mutableStateOf(false) }, keyboardOptions: KeyboardOptions = KeyboardOptions(), onValueChange: () -> Unit = {}) {
    TextField(
        value = text.value,
        onValueChange = { text.value = it; onValueChange() },
        shape = shape,
        colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor = Color.Transparent, disabledIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent),
        modifier = modifier,
        isError = isError.value,
        keyboardOptions = keyboardOptions

    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun TextFieldStandardNum(text: MutableState<TextFieldValue>, modifier: Modifier, shape: Shape = RoundedCornerShape(20.dp), isError: MutableState<Boolean> = remember { mutableStateOf(false) }, onValueChange: () -> Unit = {}, enterButtonAction: () -> Unit = {}) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = text.value,
        onValueChange = { text.value = it; onValueChange() },
        shape = shape,
        colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor = Color.Transparent, disabledIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent),
        modifier = modifier
            .onPreviewKeyEvent {
                if (it.key == Key.Tab && it.nativeKeyEvent.action == ACTION_DOWN) {
                    focusManager.moveFocus(FocusDirection.Next)
                    true
                }
                else
                    false
            },
        isError = isError.value,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Next) }
        ),
        singleLine = true

    )
}

