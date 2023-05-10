package com.emindev.schoolgadgets.library.common.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.provider.Settings.Secure
import kotlin.system.exitProcess


class Helper {
    companion object {
        @SuppressLint("HardwareIds")
        fun getDeviceId(context: Context): String = Secure.getString(context.contentResolver, Secure.ANDROID_ID)
        var deviceId = ""

        fun isOnline(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    addLog("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR", "", "Helper/isOnline()")
                    return true
                }
                else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    addLog("Internet", "NetworkCapabilities.TRANSPORT_WIFI", "", "Helper/isOnline()")
                    return true
                }
                else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    addLog("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET", "", "Helper/isOnline()")
                    return true
                }
            }
            return false
        }

        fun isGpsOpen(context: Context): Boolean {
            val locationManager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val gpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            addLog("is Gps Open", "Gps: $gpsStatus", "", "Helper/isGpsOpen()")
            return gpsStatus
        }

        fun isLocationNetworkOpen(context: Context): Boolean {
            val locationManager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val networkStatus = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            addLog("is Location Network Open", networkStatus.toString(), "", "Helper/isLocationNetworkOpen()")
            return networkStatus
        }

        fun Activity.exitApp() {
            this.finish()
            exitProcess(0)
        }

        fun String.cleanBlanks(): String = this.replace(" ", "")

    }

}


/*
fun turnGPSOn(context: Context) {
    val provider = Settings.Secure.getString(context.contentResolver, Settings.Secure.LOCATION_PROVIDERS_ALLOWED)
    if (!provider.contains("gps")) { //if gps is disabled
        val poke = Intent()
        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider")
        poke.addCategory(Intent.CATEGORY_ALTERNATIVE)
        poke.data = Uri.parse("3")
        context.sendBroadcast(poke)
    }
}

fun turnGPSOff(context: Context) {
    val provider = Settings.Secure.getString(context.contentResolver, Settings.Secure.LOCATION_PROVIDERS_ALLOWED)
    if (provider.contains("gps")) { //if gps is enabled
        val poke = Intent()
        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider")
        poke.addCategory(Intent.CATEGORY_ALTERNATIVE)
        poke.data = Uri.parse("3")
        context.sendBroadcast(poke)
    }
}

fun canToggleGPS(context: Context): Boolean {
    val pacman: PackageManager = context.getPackageManager()
    var pacInfo: PackageInfo? = null
    pacInfo = try {
        pacman.getPackageInfo("com.android.settings", PackageManager.GET_RECEIVERS)
    } catch (e: PackageManager.NameNotFoundException) {
        return false //package not found
    }
    if (pacInfo != null) {
        for (actInfo in pacInfo.receivers) {
            //test if recevier is exported. if so, we can toggle GPS.
            if (actInfo.name == "com.android.settings.widget.SettingsAppWidgetProvider" && actInfo.exported) {
                return true
            }
        }
    }
    return false //default
}*/
