package com.emindev.schoolgadgets.library.data.update

import android.content.Context
import com.emindev.schoolgadgets.BuildConfig
import com.emindev.schoolgadgets.library.common.model.RemoteData
import com.emindev.schoolgadgets.library.common.util.SharPrefFileNames
import com.emindev.schoolgadgets.library.common.util.SharPrefRemoteSettUtil
import com.emindev.schoolgadgets.library.data.sharedpreference.SharedPreferenceRepository

class RemoteSettings(private val context: Context) {
    private val repository = SharedPreferenceRepository(context)
    private val currentVersionCode = BuildConfig.VERSION_CODE

    fun setRemoteData(remoteData: RemoteData) {
        repository.setLong(SharPrefFileNames.APP_SETTINGS, SharPrefRemoteSettUtil.VERSION_CODE, remoteData.versionCode)
        repository.setBoolean(SharPrefFileNames.APP_SETTINGS, SharPrefRemoteSettUtil.FORCE_UPDATE, remoteData.forceUpdate)
        repository.setBoolean(SharPrefFileNames.APP_SETTINGS, SharPrefRemoteSettUtil.Lock, remoteData.isLocked)
    }

    fun getRemoteData(): RemoteData {
        return RemoteData(
            repository.getLong(SharPrefFileNames.APP_SETTINGS, SharPrefRemoteSettUtil.VERSION_CODE, currentVersionCode.toLong()),
            repository.getBoolean(SharPrefFileNames.APP_SETTINGS, SharPrefRemoteSettUtil.FORCE_UPDATE, false),
            repository.getBoolean(SharPrefFileNames.APP_SETTINGS, SharPrefRemoteSettUtil.Lock, false)
        )

    }

}