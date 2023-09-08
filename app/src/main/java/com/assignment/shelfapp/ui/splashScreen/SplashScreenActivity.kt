package com.assignment.shelfapp.ui.splashScreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.assignment.shelfapp.R
import com.assignment.shelfapp.data.local.sharedPref.PrefConstants
import com.assignment.shelfapp.data.local.sharedPref.PrefManager
import com.assignment.shelfapp.ui.baseActivity.BaseActivity
import com.assignment.shelfapp.ui.home.HomeScreenActivity
import com.assignment.shelfapp.ui.user.LoginScreenActivity

class SplashScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            if(PrefManager.getStringPrefValue(PrefConstants.IS_LOGIN,"") == "true"){
                startActivity(Intent(this, HomeScreenActivity::class.java))
            }else{
                startActivity(Intent(this, LoginScreenActivity::class.java))
            }

            finish()
        }, 2000)
    }
}