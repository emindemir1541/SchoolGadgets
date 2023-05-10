package com.emindev.schoolgadgets.library.common.model

import com.emindev.schoolgadgets.BuildConfig

data class RemoteData(val versionCode: Long, val forceUpdate: Boolean, val isLocked: Boolean) {
    val hasUpdate = versionCode > BuildConfig.VERSION_CODE
}