package com.example.kotlinexam.survivalcoding13.repository

import com.example.kotlinexam.survivalcoding13.db.Todo

interface Repository {
    fun getAll(): List<Todo>
    fun insert(todo: Todo)
    fun update(todo: Todo)
    fun delete(todo: Todo)
}


