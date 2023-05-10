package com.emindev.schoolgadgets

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.emindev.schoolgadgets.announcement.data.WebClient
import com.emindev.schoolgadgets.library.common.helper.Helper
import com.emindev.schoolgadgets.library.common.helper.SystemInfo
import com.emindev.schoolgadgets.library.ui.page.StartPage
import com.emindev.schoolgadgets.main.data.viewmodel.MainViewModel
import com.emindev.schoolgadgets.main.ui.pages.Navigation
import com.emindev.schoolgadgets.main.ui.theme.MainTheme


class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @SuppressLint("HardwareIds")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepVisibleCondition {
                mainViewModel.isLoading.value
            }
        }

        SystemInfo.PACKAGE_NAME = packageName
        Helper.deviceId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

        setContent {
            StartPage {
                MainTheme() {
                    Navigation()
                }
            }
        }

        WebClient(this).requestUrl()

      mainViewModel._isLoading.value = false

    }
}


