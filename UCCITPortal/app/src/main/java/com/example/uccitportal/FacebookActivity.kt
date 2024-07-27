package com.example.uccitportal

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.uccitportal.databinding.ActivityFacebookBinding
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
// Creating the class to display facebook
class FacebookActivity : AppCompatActivity() {

    // Variable for view binding
    private lateinit var binding: ActivityFacebookBinding

    // The method when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setting up the view binding
        binding = ActivityFacebookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setting the web view client
        binding.wvFacebook.webViewClient = CustomWebViewClient2(this)
        // Loading the URL in the web view
        binding.wvFacebook.loadUrl("https://www.facebook.com/uccjamaica")

        // Enabling settings
        val webSettings = binding.wvFacebook.settings
        webSettings.javaScriptEnabled = true

        // Setting the listener for when the back button is pressed
        binding.backButton.setOnClickListener {
            // Returning to the main activity
            finish()
        }

    }
}

//Allows external website to load within WebView element
class CustomWebViewClient2 internal constructor(private val activity: Activity) :
    WebViewClient() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val url: String = request?.url.toString()
        val host: String? = Uri.parse(url).host

        // Handling fb:// URLs specifically to fix page loading issues
        if (!TextUtils.isEmpty(host) && url.startsWith("fb://") && host?.contains("profile") == true) {
            // Opening the URL in the appropriate app or browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            activity.startActivity(intent)
            return true
        } else {
            // Load other URLs in the WebView
            view?.loadUrl(url)
        }
        return true
    }

    override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
        val host: String? = Uri.parse(url).host

        // Handling the  fb:// URLs specifically because of issues
        if (!TextUtils.isEmpty(host) && url.startsWith("fb://") && host?.contains("profile") == true) {
            // Opening the URL in the appropriate app or browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            activity.startActivity(intent)
            return true
        } else {
            // Load other URLs in the WebView
            webView.loadUrl(url)
        }
        return true
    }

    override fun onReceivedError(
        view: WebView,
        request: WebResourceRequest,
        error: WebResourceError
    ) {
        super.onReceivedError(view, request, error)
        Toast.makeText(activity, "Error! $error", Toast.LENGTH_SHORT).show()
    }
}