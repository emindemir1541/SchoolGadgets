package com.emindev.schoolgadgets.gradecalculator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun RowLesson(/*lesson:MutableState<Lesson>,lessonViewModel: LessonViewModel*/) {


/*    val lesson = remember { mutableStateOf(Lesson(name = "Matematik", midtermOne = 100.0, midtermTwo = 50.0, midtermOnePercentage = 5.0)) }
    val midtermOne = remember { mutableStateOf(TextFieldValue(lesson.value.midtermOne.toString())) }
    val midtermTwo = remember { mutableStateOf(TextFieldValue(lesson.value.midtermTwo.toString())) }
    val midtermThree = remember { mutableStateOf(TextFieldValue(lesson.value.midtermThree.toString())) }
    val midtermFour = remember { mutableStateOf(TextFieldValue(lesson.value.midtermFour.toString())) }
    val midtermOnePercentage = remember { mutableStateOf(TextFieldValue(lesson.value.midtermOnePercentage.toString())) }
    val midtermTwoPercentage = remember { mutableStateOf(TextFieldValue(lesson.value.midtermTwoPercentage.toString())) }
    val midtermThreePercentage = remember { mutableStateOf(TextFieldValue(lesson.value.midtermThreePercentage.toString())) }
    val midtermFourPercentage = remember { mutableStateOf(TextFieldValue(lesson.value.midtermFourPercentage.toString())) }
    val final = remember { mutableStateOf(TextFieldValue(lesson.value.midtermOne.toString())) }*/


//    lessonViewModel.setLesson(lesson.value)

    Column(verticalArrangement = Arrangement.Top) {
        Row(modifier = Modifier
            .background(Color.White)
            .padding(vertical = 16.dp)) {
            //  Text(text = lesson.value.name!!)
        }
        Row(modifier = Modifier.background(Color.White), horizontalArrangement = Arrangement.SpaceBetween) {
            /*         TextFieldGrade(text = midtermOne)
                     TextFieldGrade(text = midtermTwo)
                     TextFieldGrade(text = midtermThree)
                     TextFieldGrade(text = midtermFour)
                    TextFieldGrade()*/
        }

    }


}