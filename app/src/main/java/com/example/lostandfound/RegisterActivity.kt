package com.example.lostandfound

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity: AppCompatActivity(){

    protected lateinit var editTextEmail: EditText
    protected lateinit var editTextPassword: EditText
    protected lateinit var editTextConfirm: EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        editTextPassword = findViewById(R.id.editTextRegisterPassword)
        editTextEmail = findViewById(R.id.editTextRegisterEmail)
        editTextConfirm = findViewById(R.id.editTextConfirmPassword)

        auth = Firebase.auth

    }

    fun registerUser(v: View?){

        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()
        val confirmPass = editTextConfirm.text.toString().trim()


        if (TextUtils.isEmpty(email)){
            Toast.makeText(applicationContext, "Email cannot be left blank.",
                Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPass)){
            Toast.makeText(applicationContext, "Passwords cannot be left blank.",
                Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPass){
            Toast.makeText(applicationContext, "The passwords do not match.",
                Toast.LENGTH_SHORT).show()
            return
        }

        if (password.length < 6 || confirmPass.length < 6){
            Toast.makeText(applicationContext,
                "Password is too short. It must be at least 6 characters long.",
                Toast.LENGTH_SHORT).show()
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
            task ->
            if (task.isSuccessful){
                Toast.makeText(applicationContext,
                    "Registration successful. Please log in.",
                    Toast.LENGTH_LONG).show()
                finish()
                startActivity(Intent(this, MainActivity::class.java))

            }else{
                Toast.makeText(applicationContext, "Registration failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}