package com.contextu.al.circlevideo

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.Outline
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewOutlineProvider
import android.view.Window
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import com.contextu.al.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CircleVideoGuideBlock(private val activity: Activity): Dialog(activity) {
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setDimAmount(0f)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun show(url: String, cornerRadius:Int, widthPercentage: Int, onClose: () -> Unit){
        setContentView(R.layout.circle_video)
        val container: FrameLayout = findViewById(R.id.container)
        val webView: WebView = findViewById(R.id.webView)
        val btnClose:FloatingActionButton = findViewById(R.id.btnClose)

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true
        webSettings.domStorageEnabled = true
        webSettings.setSupportZoom(false)
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    view?.loadUrl(url)
                }
                return true
            }
        }

        val width = context.resources.displayMetrics.widthPixels
        val widthPx = ((width * widthPercentage)/100)
        container.layoutParams.width = widthPx
        container.layoutParams.height = widthPx
        val radius = widthPx/2
        val cornerRadiusPercentage = cornerRadius.toFloat() // eg. 50%
        val cornerRadiusPx = radius.times(cornerRadiusPercentage / 100)
        println("cornerRadiusNew: $cornerRadiusPx")

        container.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View?, outline: Outline?) {
                outline?.setRoundRect(
                    0,
                    0,
                    view!!.width,
                    view.height,
                    cornerRadiusPx
                )
            }
        }
        container.clipToOutline = true

        webView.loadUrl(url)
        btnClose.setOnClickListener {
            dismiss()
            onClose()
        }
        show()
    }
}