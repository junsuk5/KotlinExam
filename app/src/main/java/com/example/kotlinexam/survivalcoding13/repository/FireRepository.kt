package com.example.kotlinexam.survivalcoding13.repository

import com.example.kotlinexam.survivalcoding13.db.Todo
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore

class FireRepository : Repository {
    val db = FirebaseFirestore.getInstance()

    override fun getAll(): List<Todo> {
        val taskResult = Tasks.await(db.collection("todos").get())
        return taskResult.toObjects(Todo::class.java)
    }

    override fun insert(todo: Todo) {
        // id 생성
        db.collection("todos").document().apply {
            todo.uid = id
        }
        Tasks.await(db.collection("todos").document(todo.uid).set(todo))
    }

    override fun update(todo: Todo) {
        Tasks.await(db.collection("todos").document(todo.uid).update(todo.toMap()))
    }

    override fun delete(todo: Todo) {
        Tasks.await(db.collection("todos").document(todo.uid).delete())
    }

}