package com.emindev.schoolgadgets.main.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import com.emindev.schoolgadgets.R

@Composable
internal fun TextWelcome() {

    Text(text = stringResource(id = R.string.welcome), fontSize = 48.sp, fontStyle = FontStyle.Italic)

}