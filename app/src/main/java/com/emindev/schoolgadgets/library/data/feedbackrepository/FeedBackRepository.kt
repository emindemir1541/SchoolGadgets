package com.emindev.schoolgadgets.library.data.feedbackrepository

import com.google.gson.Gson
import com.emindev.schoolgadgets.library.common.*
import com.emindev.schoolgadgets.library.common.util.ResponseFeedbackModule.ERRORS
import com.emindev.schoolgadgets.library.common.util.ResponseFeedbackModule.baseUrl
import com.emindev.schoolgadgets.library.common.util.ResponseFeedbackModule.div
import com.emindev.schoolgadgets.library.common.util.ResponseFeedbackModule.jsonStr
import com.emindev.schoolgadgets.library.common.helper.Helper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okio.IOException
import com.emindev.schoolgadgets.library.common.helper.SystemInfo
import com.emindev.schoolgadgets.library.common.helper.addLog
import com.emindev.schoolgadgets.library.common.model.Comment
import com.emindev.schoolgadgets.library.common.model.Error
import com.emindev.schoolgadgets.library.common.model.Resource
import com.emindev.schoolgadgets.library.common.util.CoroutineUtil
import okhttp3.RequestBody.Companion.toRequestBody

@OptIn(DelicateCoroutinesApi::class)
class FeedBackRepository() : FeedBackRepositoryInterface {

    private val coroutineScope = CoroutineScope(newSingleThreadContext(CoroutineUtil.feedBackResponseContext))
    private val urlBase = baseUrl + SystemInfo.packageNameWithoutDat() + div
    private val errorUrl = urlBase + ERRORS + div + Helper.deviceId + div


    override fun setError(error: Error, result: (Resource<Error>) -> Unit) {
        coroutineScope.launch {
            val requestBody = error.json.toRequestBody("application/jsonStr".toMediaTypeOrNull())
            val request = Request.Builder().url(errorUrl + error.time + jsonStr).put(requestBody).build()
            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    result.invoke(Resource.Error(e.localizedMessage))
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.body != null) {
                        val responseBody: String = response.body!!.string()
                        result.invoke(Resource.Success(Gson().fromJson(responseBody, Error::class.java)))
                        addLog("FirebaseFeedBack", response.body.toString(), "Data sent to server successfully", "FeedBackRepository/setError()")
                    }
                    else {
                        result.invoke(Resource.Error("null"))
                    }
                }
            })
        }
    }

    override fun setComment(comment: Comment, result: (Resource<Comment>) -> Unit) {
        TODO("Not yet implemented")
    }

}