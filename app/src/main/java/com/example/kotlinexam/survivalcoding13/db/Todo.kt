package com.example.kotlinexam.survivalcoding13.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Todo(
    var title: String = "",
    var date: Long = 0L,
    @PrimaryKey(autoGenerate = true) var id: Long = 0L,
    var uid: String = ""
) : Serializable {

    fun toMap(): Map<String, Any> {
        return mapOf(
            "title" to title,
            "date" to date
        )
    }
}