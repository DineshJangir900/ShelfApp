package com.assignment.shelfapp.ui.user

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.assignment.shelfapp.data.local.sharedPref.PrefConstants
import com.assignment.shelfapp.data.local.sharedPref.PrefManager
import com.assignment.shelfapp.databinding.ActivityLoginScreenBinding
import com.assignment.shelfapp.ui.home.HomeScreenActivity
import com.assignment.shelfapp.utils.Toaster

class LoginScreenActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityLoginScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.loginBtn.setOnClickListener {
            validUserDetails()
        }

        mBinding.tvSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }


    //Function to validate user details
    private fun validUserDetails() {
        val name = mBinding.etName.text.toString()
        val password = mBinding.etPassword.text.toString()

        if (name.isNullOrEmpty()) {
            Toaster.show(this, "Name Can not be Empty.")
            return
        }

        if (password.isNullOrEmpty()) {
            Toaster.show(this, "Password Can not be Empty.")
            return
        }

        val registeredUserName = PrefManager.getStringPrefValue(PrefConstants.USER_NAME, "")
        val registeredUserPassword = PrefManager.getStringPrefValue(PrefConstants.USER_PASSWORD, "")
        if (name == registeredUserName && password == registeredUserPassword) {

            PrefManager.saveStringPrefValue(PrefConstants.USER_NAME, name)
            PrefManager.saveStringPrefValue(PrefConstants.USER_PASSWORD, password)
            PrefManager.saveStringPrefValue(PrefConstants.IS_LOGIN, "true")

            startActivity(Intent(this, HomeScreenActivity::class.java))
            finish()
        } else {
            Toaster.show(this, "Please Sign Up.")
        }
    }
}