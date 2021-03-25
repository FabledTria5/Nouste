package com.example.nouste.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.nouste.R
import com.jaeger.library.StatusBarUtil

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.setTranslucent(this, 0)
        }

        startApp()
    }

    private fun startApp() = Handler(Looper.getMainLooper()).postDelayed({
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }, 1200)

}