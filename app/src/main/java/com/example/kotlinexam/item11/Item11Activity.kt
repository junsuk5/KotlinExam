package com.example.kotlinexam.item11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinexam.R
import com.example.kotlinexam.toast
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

data class Todo(
    var userId: Int = 0,
    var id: Int = 0,
    var title: String = "",
    var completed: Boolean = false
)

interface JsonPlaceHolderApi {
    @GET("todos/{id}")
    fun getTodo(@Path("id") id: Int): Deferred<Todo>

    @GET("todos/")
    fun getTodos(): Deferred<List<Todo>>
}

class Item11Activity : AppCompatActivity(),
    CoroutineScope by CoroutineScope(Dispatchers.IO) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item11)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(JsonPlaceHolderApi::class.java)

        launch {
            val todos = service.getTodos().await()
            val todo = service.getTodo(1).await()
            launch(Dispatchers.Main) {
                toast(todos.toString())

                delay(2000)

                toast(todo.toString())
            }
        }
    }
}
