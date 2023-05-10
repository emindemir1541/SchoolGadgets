package com.emindev.schoolgadgets.gradecalculator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emindev.schoolgadgets.R
import com.emindev.schoolgadgets.gradecalculator.common.calculation.GradeCalculator
import com.emindev.schoolgadgets.gradecalculator.common.model.Grade
import com.emindev.schoolgadgets.library.common.helper.Helper.Companion.cleanBlanks

@Composable
internal fun RowFinal(finalNote: MutableState<String>, percentage: MutableState<String>) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(text = stringResource(id = R.string.final_note) + ": ", fontSize = 24.sp)
            Text(text = finalNote.value.take(5), fontSize = 24.sp, color = Color.Green)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(text = stringResource(id = R.string.percentage) + ": ", fontSize = 24.sp)
            Text(text = percentage.value.take(5), fontSize = 24.sp, color = Color.Blue)
        }

    }
}

@Composable
internal fun RowPassingGrade(
    finalPassingGrade: MutableState<TextFieldValue>, generalPassingGrade: MutableState<TextFieldValue>,
    gradeSituation: MutableState<GradeCalculator>,
    textFieldSize: DpSize = DpSize(80.dp, Dp.Unspecified),
) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val finalPassGradeErr = remember { mutableStateOf(gradeSituation.value == GradeCalculator.CALCULATING && finalPassingGrade.value.text.isEmpty()) }
    val generalPassGradeErr = remember { mutableStateOf((gradeSituation.value == GradeCalculator.CALCULATING && generalPassingGrade.value.text.isEmpty())) }
    if (finalPassGradeErr.value || generalPassGradeErr.value) gradeSituation.value = GradeCalculator.ERROR

    val textSize = DpSize((screenWidth - (textFieldSize.width * 2) - 80.dp) / 2, Dp.Unspecified)

    Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {

        Text(text = stringResource(id = R.string.lesson_passing_grade), Modifier.size(textSize))

        TextFieldStandardNum(modifier = Modifier.size(textFieldSize), text = generalPassingGrade, isError = finalPassGradeErr)

        Text(text = stringResource(id = R.string.final_passing_grade), Modifier.size(textSize))

        TextFieldStandardNum(modifier = Modifier.size(textFieldSize), text = finalPassingGrade, isError = generalPassGradeErr)

    }

}


@Composable
internal fun RowGrade(
    gradeList: SnapshotStateList<Grade>,
    indice: Int,
    gradeSituation: MutableState<GradeCalculator>,
    onDeleteButtonClick: () -> Unit
) {

    val textGrade = remember { mutableStateOf(TextFieldValue("")) }
    val gradeError = remember { mutableStateOf(gradeSituation.value == GradeCalculator.CALCULATING && textGrade.value.text.isEmpty()) }
    val textPercentage = remember { mutableStateOf(TextFieldValue("")) }
    val percentageError = remember { mutableStateOf(gradeSituation.value == GradeCalculator.CALCULATING && textPercentage.value.text.isEmpty()) }

    if (gradeError.value || percentageError.value) gradeSituation.value = GradeCalculator.ERROR

    Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {

        Text(text = stringResource(id = R.string.grade))

        TextFieldStandardNum(text = textGrade, modifier = Modifier.size(DpSize(80.dp, 60.dp)), shape = RoundedCornerShape(20.dp), isError = gradeError) { if (textGrade.value.text.isNotBlank()) gradeList[indice].note = textGrade.value.text.cleanBlanks().toDouble() }

        Text(text = stringResource(id = R.string.percentage))

        TextFieldStandardNum(modifier = Modifier.size(DpSize(80.dp, 60.dp)), text = textPercentage, isError = percentageError) { if (textPercentage.value.text.isNotBlank()) gradeList[indice].percentage = textPercentage.value.text.cleanBlanks().toDouble() }

        if (gradeList.size != 1) IconButton(imageVector = Icons.Default.Delete, description = "Delete", onClick = onDeleteButtonClick)


    }
}