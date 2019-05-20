package com.example.kotlinexam

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}

fun Any.logd(text: String) {
    Log.d(this::class.java.simpleName, text)
}