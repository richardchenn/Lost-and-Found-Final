package com.example.lostandfound

import android.content.Intent
import java.util.*

class LostItem {

    lateinit var item : String
    lateinit var date: String
    lateinit var desc: String


    internal constructor(item: String, desc: String,  date:String) {
        this.item = item
        this.desc = desc
        this.date = date
    }


    companion object {


        const val ITEM = "item"
        const val DESC = "description"
        const val DATE = "date"


        fun packageIntent(intent: Intent, item: String, desc: String,  date: String) {

            intent.putExtra(ITEM, item)
            intent.putExtra(DESC, desc)
            intent.putExtra(DATE, date)

        }

    }




}