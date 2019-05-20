package com.example.kotlinexam

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun imageUrl(view: ImageView, uri: String) {
    Glide.with(view)
        .load(uri)
        .into(view)
}

