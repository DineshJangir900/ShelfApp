package com.assignment.shelfapp.ui.user

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.assignment.shelfapp.data.local.sharedPref.PrefConstants
import com.assignment.shelfapp.data.local.sharedPref.PrefManager
import com.assignment.shelfapp.databinding.ActivitySignUpBinding
import com.assignment.shelfapp.ui.home.HomeScreenActivity
import com.assignment.shelfapp.utils.AppData
import com.assignment.shelfapp.utils.Toaster


class SignUpActivity : AppCompatActivity() {

    lateinit var mBinding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setCountrySpinner()

        mBinding.tvSignIn.setOnClickListener {
            startActivity(Intent(this, LoginScreenActivity::class.java))
            finish()
        }

        mBinding.btnSignUp.setOnClickListener {
            validUserDetails()
        }
    }

    private fun setCountrySpinner() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            AppData.getCountryList(this)
        )
        mBinding.spCountry.adapter = adapter
    }


    //Function to validate user details
    private fun validUserDetails() {
        val name: String? = mBinding.etName.text.toString()
        val password: String? = mBinding.etPassword.text.toString()
        val country: String? = mBinding.spCountry.selectedItem.toString()

        if (name.isNullOrEmpty()) {
            Toaster.show(this, "Name Can not be Empty.")
            return
        }

        if (password.isNullOrEmpty()) {
            Toaster.show(this, "Password Can not be Empty.")
            return
        }

        if (!validatePassword(password)) {
            return
        }

        if (country.isNullOrEmpty()) {
            Toaster.show(this, "Please Select Country.")
            return
        }

        if (country == "Select Country") {
            Toaster.show(this, "Please Select Country.")
            return
        }

        PrefManager.saveStringPrefValue(PrefConstants.USER_NAME, name)
        PrefManager.saveStringPrefValue(PrefConstants.USER_PASSWORD, password)
        PrefManager.saveStringPrefValue(PrefConstants.USER_COUNTRY, country)

        Toaster.show(this, "Account Create Successfully.")

        startActivity(Intent(this, LoginScreenActivity::class.java))
        finish()
    }


    fun validatePassword(password: String): Boolean {
        if (password.length < 8) {
            Toaster.show(
                this,
                "The password must be at least 8 characters long."
            )
            return false
        }

        val hasNumber = password.any { it.isDigit() }
        if (!hasNumber) {
            Toaster.show(
                this,
                "The password must contain at least one number."
            )
            return false
        }

        val specialCharacters = setOf('!', '@', '#', '$', '%', '&', '(', ')')
        val hasSpecialCharacter = password.any { it in specialCharacters }
        if (!hasSpecialCharacter) {
            Toaster.show(
                this,
                "The password must contain at least one special character."
            )
            return false
        }

        val hasLowercaseLetter = password.any { it.isLowerCase() }
        if (!hasLowercaseLetter) {
            Toaster.show(
                this,
                "The password must contain at least one lowercase letter."
            )
            return false
        }

        val hasUppercaseLetter = password.any { it.isUpperCase() }
        if (!hasUppercaseLetter) {
            Toaster.show(
                this,
                "The password must contain at least one uppercase letter."
            )
            return false
        }

        return true
    }

}