package com.emindev.schoolgadgets.gradecalculator.data.gradepreferences

import android.content.Context
import com.emindev.schoolgadgets.gradecalculator.common.util.SharPrefGradeSettingsUtil
import com.emindev.schoolgadgets.library.common.util.SharPrefFileNames
import com.emindev.schoolgadgets.library.data.sharedpreference.SharedPreferenceRepository

class GradeRepo(private val context: Context) {
    private val repository = SharedPreferenceRepository(context)

    fun setFinalPassingGrade(grade: Float) {
        repository.setFloat(SharPrefFileNames.GRADE_SETTINGS, SharPrefGradeSettingsUtil.FINAL_PASSING_GRADE, grade)
    }

    fun setLessonPassingGrade(grade: Float) {
        repository.setFloat(SharPrefFileNames.GRADE_SETTINGS, SharPrefGradeSettingsUtil.LESSON_PASSING_GRADE, grade)
    }

    fun getFinalPassingGrade() = repository.getFloat(SharPrefFileNames.GRADE_SETTINGS, SharPrefGradeSettingsUtil.FINAL_PASSING_GRADE, 35f).toDouble()

    fun getLessonPassingGrade() = repository.getFloat(SharPrefFileNames.GRADE_SETTINGS, SharPrefGradeSettingsUtil.LESSON_PASSING_GRADE, 45f).toDouble()
}
