package com.example.kotlinexam.item04java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import androidx.work.Data;

import com.example.kotlinexam.R;

import java.util.Calendar;
import java.util.UUID;

public class Item04JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item04_java);

        EditText alertTimeEditText = findViewById(R.id.alert_time);

        findViewById(R.id.set_alert_button).setOnClickListener(v -> {
            String tag = UUID.randomUUID().toString();

            int minuteBeforeAlert = Integer.valueOf(alertTimeEditText.getText().toString());
            long alertTime = getAlertTime(minuteBeforeAlert) - System.currentTimeMillis();

            int id = (int) (Math.random() * 50 + 1);

            // Data
            Data data = new Data.Builder()
                    .putString("title", "제목")
                    .putString("text", "내용")
                    .putInt("id", id)
                    .build();

            NotificationWorker.scheduleReminder(alertTime, data, tag);
        });
    }

    private long getAlertTime(int userInput) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, userInput);
        return cal.getTimeInMillis();
    }
}
