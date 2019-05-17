package com.example.kotlinexam.survivalcoding06

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.concurrent.timer

class StopWatchViewModel : ViewModel() {
    val time : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    var isRunning = MutableLiveData<Boolean>()

    init {
        time.value = 0
        isRunning.value = false
    }

    private var timerTask: Timer? = null

    private fun pause() {
        timerTask?.cancel()
    }

    private fun start() {
        timerTask = timer(period = 10) {
            // Background에서 LiveData값 갱신할 때는 postValue
            time.postValue(time.value?.plus(1))
        }
    }

    fun onStartButtonClicked() {
        isRunning.value = !isRunning.value!!

        if (isRunning.value!!) {
            start()
        } else {
            pause()
        }
    }

    override fun onCleared() {
        pause()
        timerTask = null
        super.onCleared()
    }
}