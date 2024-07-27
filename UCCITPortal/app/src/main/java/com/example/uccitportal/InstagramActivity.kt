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
import com.example.uccitportal.databinding.ActivityInstagramBinding

// Creating the class to display instagram
class InstagramActivity : AppCompatActivity() {
    
    // Variable for view binding
    private lateinit var binding: ActivityInstagramBinding

    // The method when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Setting up the view binding
        binding = ActivityInstagramBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Creating the web view client
        binding.wvInstagram.webViewClient = CustomWebViewClient(this)
        // Loading the URL in the web view
        binding.wvInstagram.loadUrl("https://www.instagram.com/uccjamaica")

        // Enabling the settings
        val webSettings = binding.wvInstagram.settings
        webSettings.javaScriptEnabled = true

        // Setting the listener for when the back button is pressed
        binding.backButton.setOnClickListener {
            // Returning to the main activity
            finish()
        }

    }
}

//Allows external website to load within WebView element
class CustomWebViewClient internal constructor(private val activity: Activity) :
    WebViewClient() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldOverrideUrlLoading(
        view: WebView?,
        request: WebResourceRequest?
    ): Boolean {
        val url: String = request?.url.toString();
        view?.loadUrl(url)
        return true
    }
    override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
        webView.loadUrl(url)
        return true
    }
    override fun onReceivedError(
        view: WebView,
        request: WebResourceRequest,
        error: WebResourceError
    ) {
        Toast.makeText(activity, "Error! $error", Toast.LENGTH_SHORT).show()
    }
}