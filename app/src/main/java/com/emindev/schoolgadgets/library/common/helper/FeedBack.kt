package com.emindev.schoolgadgets.library.common.helper

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.emindev.schoolgadgets.library.common.model.Error
import com.emindev.schoolgadgets.library.common.model.Resource
import com.emindev.schoolgadgets.library.data.feedbackrepository.FeedBackRepository

@SuppressLint("SuspiciousIndentation")
fun setError(title: String, exception: Exception, errorLocation: String, userMessage: String? = null, description: String? = null, contextForUserMessage: Context? = null): Exception {
    val error = Error(title, exception.localizedMessage?:"", exception.cause.toString(), errorLocation, DateHelper().currentTime,description)
    FeedBackRepository().setError(error) {
        when (it) {
            is Resource.Success -> {}
            is Resource.Error -> {
                addLog("Response", it.message, "Response post error", "Feedback/setError()")
            }
            is Resource.Loading -> {}
        }
    }
    Log.e("ERROR ${error.time}", "Message -->  ${error.localizedMessage} \n" +
            "Description -->  ${error.description.toString()} \n" +
            "Location -->  ${error.errorLocation} \n")

    if (userMessage != null && contextForUserMessage != null)
        Toast.makeText(contextForUserMessage, userMessage, Toast.LENGTH_SHORT).show()

    return exception

}

fun setComment() {
}

fun Context.test(data: Any?) {
    Toast.makeText(this, "TEST--> ${data.toString()}", Toast.LENGTH_LONG).show()
    Log.e("TEST", data.toString())
}

fun Context.test(title: String, data: Any?) {
    Toast.makeText(this, "TEST--> $title :: ${data.toString()}", Toast.LENGTH_LONG).show()
    Log.e("TEST", "$title --> ${data.toString()}")
}

fun logTest(data: Any?) {
    Log.e("TEST", data.toString())
}

var test: Any?
    get() {
        return "test"
    }
    set(value) = logTest(value)


fun logTest(title: String, data: Any?) {
    Log.e(title, "$title --> ${data.toString()}")
}


fun addLog(title: String, data: Any?, description: String = "", location: String = "") {
    if (description.isNotEmpty())
        Log.e(title, "Description --> $description")
    Log.e(title, "Data: --> ${data.toString()} ")
    if (location.isNotEmpty())
        Log.e(title, "Location --> $location")

}


