package com.emindev.schoolgadgets.library.common.helper

import java.lang.Exception
import kotlin.system.exitProcess

fun catchError() {
    Thread.setDefaultUncaughtExceptionHandler { paramThread, paramThrowable ->
        setError("ErrorCatcher", Exception(paramThrowable),"ErrorCatcher/catchError", description = paramThrowable.message)
        exitProcess(2)
    }
}