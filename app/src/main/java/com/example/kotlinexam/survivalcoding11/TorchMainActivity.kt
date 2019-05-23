package com.example.kotlinexam.survivalcoding11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinexam.R
import com.example.kotlinexam.toast
import kotlinx.android.synthetic.main.activity_torch_main.*

class TorchMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_torch_main)

        button.setOnClickListener {
            toast("손전등 켜짐")
        }
    }
}
