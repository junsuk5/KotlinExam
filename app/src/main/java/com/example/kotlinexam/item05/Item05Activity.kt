package com.example.kotlinexam.item05

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.kotlinexam.R
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_item05.*

class Item05Activity : AppCompatActivity() {

    private lateinit var _observable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item05)

        val add = add_button.clicks()
            .map { +1 }

        val sub = sub_button.clicks()
            .map { -1 }

        _observable = Observable.merge(add, sub)
            .scan(0) { acc: Int, value: Int -> acc + value }
            .subscribe { counter_text.text = it.toString() }
    }

    override fun onDestroy() {
        _observable.dispose()
        super.onDestroy()
    }
}
