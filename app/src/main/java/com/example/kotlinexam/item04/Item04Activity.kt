package com.example.kotlinexam.item04

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.work.Data
import com.example.kotlinexam.R
import kotlinx.android.synthetic.main.activity_item04.*
import java.util.*

class Item04Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item04)

        set_alert_button.setOnClickListener {
            val tag = UUID.randomUUID().toString()

            val minuteBeforeAlert = alert_time.text.toString().toInt()
            val alertTime = getAlertTime(minuteBeforeAlert) - System.currentTimeMillis()

            val id = (Math.random() * 50 + 1).toInt()

            // Data
            val data = Data.Builder()
                .putString("title", "제목")
                .putString("text", "내용")
                .putInt("id", id)
                .build()

            scheduleReminder(alertTime, data, tag)
        }
    }

    private fun getAlertTime(userInput: Int): Long {
        val cal = Calendar.getInstance()
        cal.add(Calendar.MINUTE, userInput)
        return cal.timeInMillis
    }
}
