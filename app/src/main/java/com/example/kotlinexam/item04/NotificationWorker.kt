package com.example.kotlinexam.item04

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.*
import com.example.kotlinexam.R
import java.util.concurrent.TimeUnit

class NotificationWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        val title = inputData.getString("title")!!
        val text = inputData.getString("text")!!
        val id = inputData.getInt("id", 0)

        sendNotification(title, text, id)
        return Result.success()
    }

    private fun sendNotification(title: String, text: String, id: Int) {
        val intent = Intent(applicationContext, Item04Activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra("id", id)

        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("default", "default", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(applicationContext, "default")
            .setContentTitle(title)
            .setContentText(text)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)

        notificationManager.notify(id, notification.build())
    }
}

fun scheduleReminder(duration: Long, data: Data, tag: String) {
    val notificationWork = OneTimeWorkRequestBuilder<NotificationWorker>()
        .setInitialDelay(duration, TimeUnit.MILLISECONDS).addTag(tag)
        .setInputData(data).build()

    WorkManager.getInstance().enqueue(notificationWork)
}

fun cancelReminder(tag: String) {
    WorkManager.getInstance().cancelAllWorkByTag(tag)
}