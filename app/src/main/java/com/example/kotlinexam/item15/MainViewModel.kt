package com.example.kotlinexam.item15

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.zomato.photofilters.SampleFilters
import com.zomato.photofilters.imageprocessors.Filter

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val filterList = listOf<Filter>(
        SampleFilters.getAweStruckVibeFilter(),
        SampleFilters.getBlueMessFilter(),
        SampleFilters.getLimeStutterFilter(),
        SampleFilters.getNightWhisperFilter(),
        SampleFilters.getStarLitFilter()
    )

    var bitmap = MutableLiveData<Bitmap>()


}