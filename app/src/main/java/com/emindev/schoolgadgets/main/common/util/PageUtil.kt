package com.emindev.schoolgadgets.main.common.util

import com.emindev.schoolgadgets.R


sealed class Page(val name: String, val icon: Int? = null) {
    object Main : Page("MAIN")
    object Grade : Page("GRADE", R.drawable.exam)
}
