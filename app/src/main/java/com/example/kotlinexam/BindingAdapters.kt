package com.example.kotlinexam

import android.widget.CalendarView
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("imageUrl")
fun imageUrl(view: ImageView, uri: String) {
    Glide.with(view)
        .load(uri)
        .into(view)
}

@BindingAdapter("date")
fun longDateToString(view: TextView, date: Long) {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN)
    view.text = sdf.format(Date(date))
}

@BindingAdapter("setDate")
fun longDateBindToCalendarView(view: CalendarView, date: Long) {
    view.date = date
}