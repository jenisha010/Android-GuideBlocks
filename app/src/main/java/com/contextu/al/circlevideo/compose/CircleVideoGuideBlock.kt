package com.contextu.al.circlevideo.compose

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.contextu.al.R
import kotlin.math.min

class CircleVideoGuideBlockC(private val url: String, private val cornerRadius: Int, private val widthPercentage: Int, private val onClose: () -> Unit ) {
    @Composable
    fun Content() {
        showDialog()
    }

    @Composable
    fun showDialog() {
        val dialog = remember { mutableStateOf(true) }
        if (dialog.value) {
            Dialog(
                onDismissRequest = { dialog.value = false }
            ) {
                val context = LocalContext.current
                val screenWidth = context.resources.displayMetrics.widthPixels.toFloat()
                val minDimension = min(screenWidth, context.resources.displayMetrics.heightPixels.toFloat())
                val widthPx = ((minDimension * widthPercentage)/100).dp
                val radius = (screenWidth * widthPercentage / 200)
                val cornerRadiusPercentage = cornerRadius.toFloat() // eg. 50%
                val cornerRadiusPx = radius.times(cornerRadiusPercentage / 100)
                Surface(
                    shape = RoundedCornerShape(cornerRadiusPx),
                ) {
                    Box(
                        modifier = Modifier
                            .width(widthPx)
                            .aspectRatio(1f), // for square shape
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularWebView(url = url)
                        }
                        SmallFloatingActionButton(
                            onClick = {
                                dialog.value = false
                                onClose()
                            },
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                                .align(Alignment.BottomCenter)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_close),
                                contentDescription = "CLOSE"
                            )
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun CircularWebView(url: String) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.domStorageEnabled = true
                settings.setSupportZoom(false)
                settings.builtInZoomControls = true
                settings.displayZoomControls = false
                settings.mediaPlaybackRequiresUserGesture = false
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                        if (url != null) {
                            view?.loadUrl(url)
                        }
                        return true
                    }
                }
                loadUrl(url)
            }
        },
        modifier = Modifier.fillMaxSize(),
        update = { webView ->
        }
    )
}


