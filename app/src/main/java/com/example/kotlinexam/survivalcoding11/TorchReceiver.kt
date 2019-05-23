package com.example.kotlinexam.survivalcoding11

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.kotlinexam.toast

class TorchReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.toast("손전등 켜짐")
    }
}
