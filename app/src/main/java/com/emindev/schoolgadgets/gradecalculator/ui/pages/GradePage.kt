package com.emindev.schoolgadgets.gradecalculator.ui.pages

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.emindev.schoolgadgets.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.emindev.schoolgadgets.gradecalculator.common.calculation.GradeCalculator
import com.emindev.schoolgadgets.gradecalculator.common.model.Grade
import com.emindev.schoolgadgets.gradecalculator.data.gradepreferences.GradeRepo
import com.emindev.schoolgadgets.gradecalculator.ui.components.*
import com.emindev.schoolgadgets.gradecalculator.ui.components.ButtonCalculate
import com.emindev.schoolgadgets.gradecalculator.ui.components.IconButton
import com.emindev.schoolgadgets.library.common.helper.Helper.Companion.cleanBlanks


@Composable
fun GradePage(navController: NavController) {

    val context = LocalContext.current
    val gradeRepo = GradeRepo(context)

    Box(modifier = Modifier
        .fillMaxSize()) {
        Column(modifier = Modifier
            .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {


            val gradeList: SnapshotStateList<Grade> = remember { mutableStateListOf(Grade(0.0, 0.0)) }
            val gradeSituation = remember { mutableStateOf(GradeCalculator.CALCULATED) }
            val finalPassingGrade = remember { mutableStateOf(TextFieldValue(gradeRepo.getFinalPassingGrade().toString())) }
            val lessonPassingGrade = remember { mutableStateOf(TextFieldValue(gradeRepo.getLessonPassingGrade().toString())) }
            val finalNote = remember { mutableStateOf("") }
            val finalPercentage = remember { mutableStateOf("") }

            SmallTopAppBar(
                title = { Text(text = stringResource(id = R.string.grade_calculator)) },
                modifier = Modifier,
                colors = TopAppBarDefaults.smallTopAppBarColors(),
                navigationIcon = {
                    IconButton(Icons.Default.ArrowBack, "Back", modifier = Modifier.padding(end = 16.dp)) {
                        navController.popBackStack()
                    }
                },
                actions = {
                    IconButton(imageVector = Icons.Default.Add, description = Icons.Default.Add.name) {
                        if (gradeList.size > 5)
                            Toast.makeText(context, context.getString(R.string.add_grade_warning), Toast.LENGTH_SHORT).show()
                        else {
                            gradeList.add(Grade())
                        }
                    }
                }
            )

            RowFinal(finalNote = finalNote, percentage = finalPercentage)


            LazyColumn(verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {


                item {
                    RowPassingGrade(finalPassingGrade = finalPassingGrade, generalPassingGrade = lessonPassingGrade, gradeSituation = gradeSituation)
                }

                items(gradeList.size) { indice ->
                    RowGrade(gradeList = gradeList, indice, gradeSituation = gradeSituation) {
                        gradeList.remove(gradeList[indice])
                    }
                }


                item {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        ButtonCalculate {
                            gradeSituation.value = GradeCalculator.CALCULATING
                            if (gradeSituation.value == GradeCalculator.CALCULATING) {
                                finalPercentage.value = GradeCalculator.finalPercentage(gradeList).toString()
                                finalNote.value = GradeCalculator.calculate(gradeList, lessonPassingGrade.value.text.cleanBlanks().toDouble(), finalPassingGrade.value.text.cleanBlanks().toDouble()).toString()
                                gradeRepo.setFinalPassingGrade(finalPassingGrade.value.text.cleanBlanks().toFloat())
                                gradeRepo.setLessonPassingGrade(lessonPassingGrade.value.text.cleanBlanks().toFloat())
                                gradeSituation.value = GradeCalculator.CALCULATED
                            }
                        }
                    }
                }

                item {
                    Box(modifier = Modifier.padding(10.dp))
                }

            }


        }
    }
}




