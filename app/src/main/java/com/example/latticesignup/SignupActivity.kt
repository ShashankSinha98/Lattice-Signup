package com.example.latticesignup

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import java.util.regex.Pattern


class SignupActivity : AppCompatActivity() {

    private val TAG : String = "xlr8"

    private lateinit var fullname : TextInputLayout
    private lateinit var address : TextInputLayout
    private lateinit var email : TextInputLayout
    private lateinit var number : TextInputLayout
    private lateinit var password : TextInputLayout
    private lateinit var signup : Button

    private var fullnameValidated = false
    private var addressValidated = false
    private var emailValidated = false
    private var numberValidated = false
    private var passwordValidated = false

    private lateinit var userViewModel : UserViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        fullname = findViewById(R.id.full_name_inp_layout)
        address = findViewById(R.id.address_inp_layout)
        email = findViewById(R.id.email_inp_layout)
        number = findViewById(R.id.number_inp_layout)
        password = findViewById(R.id.password_inp_layout)
        signup = findViewById(R.id.sign_up_btn)

        fullname.editText?.addTextChangedListener(nameWatcher)
        address.editText?.addTextChangedListener(addressWatcher)
        email.editText?.addTextChangedListener(emailWatcher)
        number.editText?.addTextChangedListener(numberWatcher)
        password.editText?.addTextChangedListener(passwordWatcher)

        userViewModel = ViewModelProvider(this).get(UserViewmodel::class.java)

    }



    private val nameWatcher = object : TextWatcher{
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {

            if (count<4){
                fullname.error = "Name must be at least 4 characters"
                fullnameValidated = false
            } else{
                fullname.error = null
                fullnameValidated = true
            }

            signupButtonRefresh()
        }

    }

    private val addressWatcher = object : TextWatcher{
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {

            if (address.editText?.text.toString().length<10){
                address.error = "Address must be at least 10 characters"
                addressValidated = false
            } else{
                address.error = null
                addressValidated = true
            }

            signupButtonRefresh()
        }

    }

    private val emailWatcher = object : TextWatcher{
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(str : CharSequence?, start: Int, before: Int, count: Int) {
            var check : String = str.toString()

            val emailRegex : String = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

            val pat = Pattern.compile(emailRegex)

            if(check.length < 4 || check.length > 40) {
                email.error = "Email Must consist of 4 to 20 characters"
                emailValidated = false
            } else if (!pat.matcher(check).matches() || !check.contains("@") || !check.contains(".")) {
                email.error = "Enter valid email"
                emailValidated = false
            } else {
                email.error = null
                emailValidated = true
            }

            signupButtonRefresh()
        }

    }

    private val numberWatcher = object : TextWatcher{
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {

            if (number.editText?.text.toString().length!=10){
                number.error = "Number must be of 10 characters"
                numberValidated = false
            } else{
                number.error = null
                numberValidated = true
            }

            signupButtonRefresh()
        }

    }

    private val passwordWatcher = object : TextWatcher{
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {

            if (password.editText?.text.toString().length in 8..15){
                password.error = null
                passwordValidated = true
            } else{
                password.error = "Password length must be between 8 and 15 characters"
                passwordValidated = false
            }

            signupButtonRefresh()
        }

    }

    private fun signupButtonRefresh(){
        if(fullnameValidated && addressValidated && emailValidated && numberValidated && passwordValidated){
            signup.background = getDrawable(R.color.colorPrimary)
            signup.isEnabled = true
        } else {
            signup.background = getDrawable(R.color.grey)
            signup.isEnabled = false
        }
    }

    fun signUpUser(view: View) {

        val name = fullname.editText?.text.toString()
        val address = address.editText?.text.toString()
        val email = email.editText?.text.toString()
        val number = number.editText?.text.toString()
        val password = password.editText?.text.toString()

        val user = User(name,address,email,number,password)
        val userJson = Gson().toJson(user)

        //Toast.makeText(this,userJson,Toast.LENGTH_LONG).show()
        Log.d(TAG, userJson)

        val jsonData = UserJson(userJson)
        userViewModel.insert(jsonData)
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)


    }

    fun fab_menu(view: View) {

        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

}