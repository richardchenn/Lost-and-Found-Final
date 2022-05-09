package com.example.lostandfound


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    protected lateinit var editTextEmail: EditText
    protected lateinit var editTextPassword: EditText
    private lateinit var auth: FirebaseAuth;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextPassword = findViewById(R.id.editTextPassword)
        editTextEmail = findViewById(R.id.editTextEmail)

        auth = Firebase.auth

    }

    fun userLogin(v: View?){
        val password = editTextPassword.text.toString().trim()
        val email = editTextEmail.text.toString().trim()

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(applicationContext, "You cannot leave an entry empty.",
                Toast.LENGTH_SHORT).show()
            return
        }
        if (!isValidEmail(email)){
            Toast.makeText(applicationContext, "Email is in invalid format",
                Toast.LENGTH_SHORT).show()
            return
        }
        else{
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
                task ->
                if (task.isSuccessful){
                    Toast.makeText(applicationContext, "Login successful!", Toast.LENGTH_LONG).show()
                    finish()
                    startActivity(Intent(this, HubActivity::class.java))
                }else{
                    Toast.makeText(applicationContext,
                        "Login failed! Could not validate credentials", Toast.LENGTH_LONG).show()
                    editTextEmail.text.clear()
                    editTextPassword.text.clear()

                }
            }
        }
    }


    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    //called if user clicks the register button, opens the registration activity
    fun userRegister(v: View?){
        val startRegister = Intent(this, RegisterActivity::class.java)
        startActivity(startRegister)
    }
}