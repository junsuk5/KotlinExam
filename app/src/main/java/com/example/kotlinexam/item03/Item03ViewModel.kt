package com.example.kotlinexam.item03

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.kotlinexam.item03.repository.MovieRepository

class Item03ViewModel : ViewModel() {
    private val repository = MovieRepository()
    val filteredLiveData = MutableLiveData<List<String>>()

    init {
        filteredLiveData.value = repository.items
    }

    fun query(newText: String?) {
        newText?.let {
            filteredLiveData.value = repository.items.filter {
                it.toLowerCase().contains(newText.toLowerCase())
            }
        }
    }
}