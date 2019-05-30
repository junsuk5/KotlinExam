package com.example.kotlinexam.survivalcoding13

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.kotlinexam.survivalcoding13.db.Todo
import com.example.kotlinexam.survivalcoding13.repository.FireRepository
import com.example.kotlinexam.survivalcoding13.repository.Repository

class TodoListViewModel(application: Application) : AndroidViewModel(application) {

    val items = MutableLiveData<List<Todo>>()

    private val repository: Repository by lazy {
//        RoomRepository(application)
        FireRepository()
    }

    fun getAll() {
        items.postValue(repository.getAll())
    }

    fun insert(todo: Todo) {
        repository.insert(todo)
    }

    fun update(todo: Todo) {
        repository.update(todo)
    }

    fun delete(todo: Todo) {
        repository.delete(todo)
    }

}