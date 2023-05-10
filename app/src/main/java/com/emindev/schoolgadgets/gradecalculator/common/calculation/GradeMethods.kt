package com.emindev.schoolgadgets.gradecalculator.common.calculation

import com.emindev.schoolgadgets.gradecalculator.common.model.Grade

val Grade.isNotZero: Boolean
    get() = !(note == 0.0 || percentage == 0.0)