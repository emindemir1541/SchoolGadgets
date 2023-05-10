package com.emindev.schoolgadgets.library.common.model

import com.google.gson.Gson

data class Comment(val nameSurname: String, val comment: String, val time: Long) {

    val json: String = Gson().toJson(this)

}