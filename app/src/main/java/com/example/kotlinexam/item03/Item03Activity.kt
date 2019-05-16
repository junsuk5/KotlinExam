package com.example.kotlinexam.item03

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.SearchView
import com.example.kotlinexam.R
import kotlinx.android.synthetic.main.activity_item03.*

class Item03Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item03)

        val viewModel = ViewModelProviders.of(this).get(Item03ViewModel::class.java)

        // Observer
        viewModel.filteredLiveData.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                result_text.text = it.reduce { acc, s -> "$acc\n$s" }
            }
        })

        // Event
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.query(newText)
                return true
            }
        })
    }
}
