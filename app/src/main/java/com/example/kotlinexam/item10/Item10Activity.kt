package com.example.kotlinexam.item10

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_item10.*
import java.util.*


class Item10Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.kotlinexam.R.layout.activity_item10)

        add_event_button.setOnClickListener {
            val beginTime = Calendar.getInstance()
            val endTime = Calendar.getInstance()
            endTime.set(2019, 4, 21, 8, 30)
            val intent = Intent(Intent.ACTION_INSERT)
                .setData(Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.timeInMillis)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.timeInMillis)
                .putExtra(Events.TITLE, "캘린더 테스트")
                .putExtra(Events.DESCRIPTION, "설명 테스트")
                .putExtra(Events.EVENT_LOCATION, "세민직업전문학교")
                .putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY)
            startActivity(intent)
        }

        add_event_button2.setOnClickListener {

        }
    }
}
