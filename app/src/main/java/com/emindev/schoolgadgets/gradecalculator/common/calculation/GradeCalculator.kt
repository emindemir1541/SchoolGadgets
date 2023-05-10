package com.emindev.schoolgadgets.gradecalculator.common.calculation

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.emindev.schoolgadgets.gradecalculator.common.model.Grade
import com.emindev.schoolgadgets.library.common.helper.setError

enum class GradeCalculator {
    CALCULATING,
    CALCULATED,
    ERROR;

    companion object {

        fun calculate(gradeList: SnapshotStateList<Grade>, lessonPassingGrade: Double, finalPassingGrade: Double): Double {
            val final: Double
            var totalMidterm = 0.0

            try {

                gradeList.forEach { grade ->
                    totalMidterm += (grade.note * grade.percentage) / 100
                }


                final = ((lessonPassingGrade - totalMidterm) * 100) / finalPercentage(gradeList)


                return if (final > finalPassingGrade) final else finalPassingGrade


            } catch (e: Exception) {
                setError("Grade Calculation ERROR", e, "GradeCalculator/Lesson.calculateFinal()", description = "ERROR While CALCULATING final Note")
                return 0.0
            }
        }

        fun finalPercentage(gradeList: SnapshotStateList<Grade>): Double {
            var totalPercentage = 0.0
            gradeList.forEach { grade ->
                totalPercentage += grade.percentage
            }
            return 100 - totalPercentage
        }

    }

}




