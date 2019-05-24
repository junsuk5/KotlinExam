package com.example.kotlinexam.survivalcoding13

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.kotlinexam.survivalcoding13.db.AppDatabase
import com.example.kotlinexam.survivalcoding13.db.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoListViewModel(application: Application) : AndroidViewModel(application) {
    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            application,
            AppDatabase::class.java, "todoList"
        ).build()
    }

    fun getAll(): LiveData<List<Todo>> {
        return db.todoDao().getAll()
    }

    fun insert(todo: Todo) {
        CoroutineScope(Dispatchers.IO).launch {
            db.todoDao().insert(todo)
        }
    }

    fun update(todo: Todo) {
        CoroutineScope(Dispatchers.IO).launch {
            db.todoDao().update(todo)
        }
    }

    fun delete(todo: Todo) {
        CoroutineScope(Dispatchers.IO).launch {
            db.todoDao().delete(todo)
        }
    }

}