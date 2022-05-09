//package com.example.lostandfound
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.fragment.app.Fragment
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import com.google.firebase.auth.FirebaseAuth
//
//class HomePageActivity: AppCompatActivity() {
//
//    private lateinit var auth: FirebaseAuth
//    private val foundFragment = FoundFragment()
//    private val lostFragment = LostFragment()
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_homepage)
//        replaceFragment(lostFragment)
//
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//
//
//        bottomNavigationView.setOnItemSelectedListener() {
//            when (it.itemId){
//                R.id.foundFragment -> replaceFragment(foundFragment)
//                R.id.lostFragment -> replaceFragment(lostFragment)
//            }
//
//            return@setOnItemSelectedListener true
//        }
//
//    }
//
//    private fun replaceFragment(fragment: Fragment){
//        if (fragment != null){
//            val transaction = supportFragmentManager.beginTransaction()
//                .replace(R.id.fragmentContainerView, fragment)
//            transaction.commit()
//        }
//    }
//
//    override fun onDestroy(){
//        super.onDestroy()
//        auth.signOut()
//        finish()
//        startActivity(Intent(this, MainActivity::class.java))
//
//    }
//
//}