package com.example.kotlinexam.item15

import android.graphics.Bitmap
import com.zomato.photofilters.imageprocessors.Filter

data class FilterItem(
    val bitmap: Bitmap,
    val filter: Filter
)