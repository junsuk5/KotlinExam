package com.example.kotlinexam.item03

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SearchView
import com.example.kotlinexam.R
import kotlinx.android.synthetic.main.activity_item03.*

class Item03Activity : AppCompatActivity() {

    val items = listOf(
        "어벤져스",
        "배트맨",
        "슈퍼맨"
    )

    val filteredLiveData = MutableLiveData<List<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item03)

        filteredLiveData.value = items

        filteredLiveData.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                result_text.text = it.reduce { acc, s -> acc + "\n" + s }
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    filteredLiveData.value = items.filter {
                        it.toLowerCase().contains(newText.toLowerCase())
                    }
                }
                return true
            }
        })
    }
}
