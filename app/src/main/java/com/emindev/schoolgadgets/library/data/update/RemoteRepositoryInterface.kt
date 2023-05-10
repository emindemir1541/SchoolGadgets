package com.emindev.schoolgadgets.library.data.update

import com.emindev.schoolgadgets.library.common.model.RemoteData
import com.emindev.schoolgadgets.library.common.model.Resource

interface RemoteRepositoryInterface {
    fun remoteData(listener: (Resource<RemoteData>) -> Unit)
}