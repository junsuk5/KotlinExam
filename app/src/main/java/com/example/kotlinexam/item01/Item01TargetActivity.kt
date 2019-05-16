package com.example.kotlinexam.item01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinexam.R

class Item01TargetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item01_target)

        val person: Person = intent.getSerializableExtra("person") as Person

//        debug { person }
//        toast(person.toString())
    }
}
