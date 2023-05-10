package com.emindev.schoolgadgets.announcement.data

import android.content.Context
import com.emindev.schoolgadgets.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.InputStream
import java.security.KeyStore
import java.security.cert.CertificateFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

class WebClient(private val context: Context) {

    private val url = "https://www.ikcu.edu.tr/Duyuru/"

    @OptIn(DelicateCoroutinesApi::class)
    fun requestUrl() {
        GlobalScope.launch {

            val certificate: InputStream = context.resources.openRawResource(R.raw.ikcu_edu_tr)
            val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
            keyStore.load(null)
            val certificateFactory = CertificateFactory.getInstance("X.509")
            val cert = certificateFactory.generateCertificate(certificate)
            keyStore.setCertificateEntry("server", cert)
            trustManagerFactory.init(keyStore)
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, trustManagerFactory.trustManagers, null)
            val client = OkHttpClient.Builder()
                .sslSocketFactory(sslContext.socketFactory, trustManagerFactory.trustManagers[0] as X509TrustManager)
                .build()
            val request = Request.Builder()
                .url(url)
                .build()
            //val document: Document = Jsoup.connect(url).get()
            val response: Response = client.newCall(request).execute()
            val content = response.body?.string()
           // test = document.getElementsByClass("col")
        }

    }
}