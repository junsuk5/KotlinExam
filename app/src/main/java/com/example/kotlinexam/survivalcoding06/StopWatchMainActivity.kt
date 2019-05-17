package com.example.kotlinexam.survivalcoding06

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinexam.R
import kotlinx.android.synthetic.main.activity_stop_watch_main.*

class StopWatchMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stop_watch_main)

        val viewModel = ViewModelProviders.of(this).get(StopWatchViewModel::class.java)

        viewModel.time.observe(this, Observer { time ->
            val sec = time / 100
            val milli = time % 100

            sec_text.text = "$sec"
            milli_text.text = "$milli"
        })

        viewModel.isRunning.observe(this, Observer { isRunning ->
            if (isRunning) {
                start_button.text = "Pause"
            } else {
                start_button.text = "Start"
            }
        })

        start_button.setOnClickListener {
            viewModel.onStartButtonClicked()
        }
    }

}
