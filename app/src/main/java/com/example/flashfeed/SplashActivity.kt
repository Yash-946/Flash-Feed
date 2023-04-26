package com.example.flashfeed

import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast

class SplashAcitivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_acitivity)

        val conn = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val mobileNwInfo = conn.activeNetworkInfo?.isConnected ?: false
        if (!mobileNwInfo){
            Toast.makeText(applicationContext,"Please Connect to Internet",Toast.LENGTH_SHORT).show()
        }
        else{
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        }
    }
}