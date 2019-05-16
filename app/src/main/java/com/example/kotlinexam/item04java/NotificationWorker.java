package com.example.kotlinexam.item04java;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.kotlinexam.R;

import java.util.concurrent.TimeUnit;

public class NotificationWorker extends Worker {
    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String title = getInputData().getString("title");
        String text = getInputData().getString("text");
        int id = getInputData().getInt("id", 0);

        sendNotification(title, text, id);
        return Result.success();
    }

    private void sendNotification(String title, String text, int id) {
        Intent intent = new Intent(getApplicationContext(), Item04JavaActivity.class);

        intent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("id", id);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        NotificationManager notificationManager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel("default", "default", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), "default")
                .setContentTitle(title)
                .setContentText(text)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true);

        notificationManager.notify(id, notification.build());
    }

    public static void scheduleReminder(long duration, Data data, String tag) {
        OneTimeWorkRequest notificationWork = new OneTimeWorkRequest.Builder(NotificationWorker.class)
                .setInitialDelay(duration, TimeUnit.MILLISECONDS).addTag(tag)
                .setInputData(data).build();

        WorkManager.getInstance().enqueue(notificationWork);
    }

    public static void cancelReminder(String tag) {
        WorkManager.getInstance().cancelAllWorkByTag(tag);
    }
}
