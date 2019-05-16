package com.example.kotlinexam.item01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinexam.R
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.toast

class Item01TargetActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item01_target)

        val person: Person = intent.getSerializableExtra("person") as Person

        debug { person }
        toast(person.toString())
    }
}
