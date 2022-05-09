package com.example.lostandfound

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HubActivity: AppCompatActivity() {

    //protected lateinit var lostBtn: Button
    //protected lateinit var foundBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hub)


    }

    fun lostButton(v: View?){
        val intent = Intent(this, LostItemListActivity::class.java)
        startActivity(intent)
    }

    fun foundButton(v:View?){
        //val intent = Intent(this, FoundItemListActivity::class.java)
    }



}