package com.example.kotlinexam.survivalcoding09

import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinexam.R

class MyGalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_gallery)

        getAllPhotos();
    }

    private fun getAllPhotos() {
        val cursor = contentResolver
            .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                "${MediaStore.Images.ImageColumns.DATE_TAKEN} DESC")
    }
}
