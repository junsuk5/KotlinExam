package com.example.kotlinexam.survivalcoding13.repository

import android.content.Context
import androidx.room.Room
import com.example.kotlinexam.survivalcoding13.db.AppDatabase
import com.example.kotlinexam.survivalcoding13.db.Todo

class RoomRepository(context: Context) : Repository {
    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java, "todoList"
        ).build()
    }

    override fun getAll(): List<Todo> {
        return db.todoDao().getAll()
    }

    override fun insert(todo: Todo) {
        db.todoDao().insert(todo)
    }

    override fun update(todo: Todo) {
        db.todoDao().update(todo)
    }

    override fun delete(todo: Todo) {
        db.todoDao().delete(todo)
    }

}