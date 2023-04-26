package com.example.flashfeed

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NewsDetailActivity : AppCompatActivity() {
//    private var title: String? = null
//    private var desc: String? = null
//    private var content: String? = null
//    private var imageURL: String? = null
//    private var url: String? = null

    private lateinit var newsWebView: WebView
    private lateinit var share:ImageButton

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val title = intent.getStringExtra("title")
//        imageURL = intent.getStringExtra("image")
//        desc = intent.getStringExtra("desc")
//        content = intent.getStringExtra("content")
        val newurl =intent.getStringExtra("url")

        share = findViewById(R.id.imageButton2)
        newsWebView = findViewById(R.id.news_webview)

        newsWebView.apply {
            settings.apply {
                javaScriptEnabled = true
            }
            webViewClient = WebViewClient()
        }

        if (newurl != null) {
            newsWebView.loadUrl(newurl)
        }

       share.setOnClickListener(View.OnClickListener {
           val intent = Intent(Intent.ACTION_SEND)
           intent.type = "text/plain"
           intent.putExtra(Intent.EXTRA_TEXT, "$title, $newurl")
           val chooserIntent = Intent.createChooser(intent, "Share this news..")
           startActivity(chooserIntent)
       })


    }
}