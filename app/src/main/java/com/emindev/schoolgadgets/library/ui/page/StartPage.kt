package com.emindev.schoolgadgets.library.ui.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.emindev.schoolgadgets.BuildConfig
import com.emindev.schoolgadgets.library.common.helper.addLog
import com.emindev.schoolgadgets.library.common.helper.catchError
import com.emindev.schoolgadgets.library.common.model.Resource
import com.emindev.schoolgadgets.library.data.update.RemoteRepository
import com.emindev.schoolgadgets.library.data.update.RemoteSettings

@Composable
fun StartPage(pages: @Composable () -> Unit) {

    catchError()

    val context = LocalContext.current
    val remoteSettings = RemoteSettings(context)
    val remoteData = remoteSettings.getRemoteData()
    val hasUpdate = remember { mutableStateOf(remoteData.hasUpdate) }
    val isUpdateForce = remember { mutableStateOf(remoteData.forceUpdate) }
    val isAppLocked = remember { mutableStateOf(remoteData.isLocked) }
    val showUpdatePage = remember { mutableStateOf(true) }


    RemoteRepository().remoteData {
        when (it) {
            is Resource.Success -> {
                isAppLocked.value = it.data!!.isLocked
                isUpdateForce.value = it.data.forceUpdate
                hasUpdate.value = it.data.hasUpdate
                remoteSettings.setRemoteData(it.data)
                addLog("RemoteSource", it.data, "", "StartPage()")
                addLog("RemoteSource", BuildConfig.VERSION_CODE, "Current Version Code", "StartPage()")
            }
            is Resource.Error -> {}
            is Resource.Loading -> {}
        }
    }

    if (isAppLocked.value) {
        LockPage()
    }
    else if (hasUpdate.value) {
        if (showUpdatePage.value)
            UpdatePage(isUpdateForce.value, show = showUpdatePage)
        else
            pages()
    }
    else
        pages()


}