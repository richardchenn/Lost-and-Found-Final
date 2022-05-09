package com.example.lostandfound

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LostItemListActivity: AppCompatActivity() {

    lateinit var itemAdapter: AddItemAdapter
    lateinit var linearLayoutManager : LinearLayoutManager
    lateinit var lostItemBtn: Button
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    var item: String = ""
    var date: String = ""
    var description: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lostitemlist)

        sharedPreferences = this.getSharedPreferences("items", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
//        editor.clear()
//        editor.commit()

        lostItemBtn = findViewById(R.id.btnLostItem)
        val recycler = findViewById<RecyclerView>(R.id.RecyclerView)
        itemAdapter = AddItemAdapter(this,sharedPreferences)
        linearLayoutManager = LinearLayoutManager(this)

        recycler.layoutManager = linearLayoutManager
        recycler.adapter = itemAdapter
        ViewCompat.setNestedScrollingEnabled(recycler, false)

        loadShared()
        lostItemBtn.setOnClickListener {
            Log.i(TAG, "Shared Pref:${sharedPreferences.all}")
            openActivityForResult()
        }
    }

    fun loadShared(){
        if(sharedPreferences != null) {
            var entries: Map<String, String> = sharedPreferences.all as Map<String, String>
            for (entry in entries) {
                var temp = entry.value.split(",")
//                var name = temp[0]
//                var description = temp[1]
//                var date = temp[2]
                itemAdapter.add(LostItem(temp[0], temp[1], temp[2]))
                //Toast.makeText(applicationContext, "The item is: $temp", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun openActivityForResult(){
        val intent = Intent(this, AddItemActivity::class.java)
        resultLauncher.launch(intent)
    }

    val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
            if (result.resultCode == Activity.RESULT_OK){
                val data: Intent? = result.data
                item = data?.extras!!.get("item").toString()
                description = data?.extras!!.get("description").toString()
                date = data?.extras!!.get("date").toString()

                itemAdapter.add(LostItem(item, description, date))
                editor.apply {
                    putString(date, "$item,$description,$date")
                    apply()
                }
                Log.i(TAG, "Shared Pref:${sharedPreferences.all}")
            }
    }

}



