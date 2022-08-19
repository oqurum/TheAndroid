package io.oqurum.reader

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewClientCompat
import io.oqurum.reader.MainActivity.Companion.server_ip


class Webby : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_webby)

        val webView: WebView = findViewById(R.id.webview)

        webView.settings.javaScriptEnabled = true
        webView.settings.allowFileAccess = false
        webView.settings.allowContentAccess = false
        webView.settings.allowFileAccessFromFileURLs = false
        webView.settings.allowUniversalAccessFromFileURLs = false

        val assetLoader = WebViewAssetLoader.Builder()
            .setDomain(server_ip)
            .addPathHandler("/", WebViewAssetLoader.AssetsPathHandler(this))
            .build()

        webView.webViewClient = LocalContentWebViewClient(assetLoader)
        webView.webChromeClient = object : WebChromeClient() {
            override fun onConsoleMessage(message: ConsoleMessage): Boolean {
                Log.d(
                    "Oqurum",
                    "${message.message()} | line ${message.lineNumber()} from ${message.sourceId()}"
                )
                return true
            }
        }

        webView.loadUrl("${server_ip}/index.html")
    }
}

private class LocalContentWebViewClient(private val assetLoader: WebViewAssetLoader) : WebViewClientCompat() {
    override fun shouldInterceptRequest(
        view: WebView,
        request: WebResourceRequest
    ): WebResourceResponse? {
        return assetLoader.shouldInterceptRequest(request.url)
    }

    @Deprecated("Deprecated in Java")
    override fun shouldInterceptRequest(
        view: WebView,
        url: String
    ): WebResourceResponse? {
        return assetLoader.shouldInterceptRequest(Uri.parse(url))
    }
}


//class BetterAssetPathHandler : PathHandler {
//    private var mcontext: Context
//
//    constructor(context: Context) {
//        mcontext = context
//    }
//
//    @WorkerThread
//    override fun handle(path: String): WebResourceResponse? {
//        return try {
//            val `is` = mcontext.openAsset(path)
//            val mimeType = AssetHelper.guessMimeType(path)
//            WebResourceResponse(mimeType, null, `is`)
//        } catch (e: IOException) {
//            Log.e(WebViewAssetLoader.TAG, "Error opening asset path: $path", e)
//            WebResourceResponse(null, null, null)
//        }
//    }
//}