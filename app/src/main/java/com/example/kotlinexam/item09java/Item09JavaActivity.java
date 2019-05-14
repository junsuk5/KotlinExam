package com.example.kotlinexam.item09java;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.kotlinexam.R;

public class Item09JavaActivity extends AppCompatActivity
implements SensorEventListener {

    private static final String TAG = "Sensor";
    private SensorManager sensorManager;
    private Sensor mSensor;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item09_java);

        mTextView = findViewById(R.id.textView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

//        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            // Success! There's a magnetometer.
            mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        } else {
            // Failure! No magnetometer.
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append(event.values[0]);
        sb.append(", ");
        sb.append(event.values[1]);
        sb.append(", ");
        sb.append(event.values[2]);


        mTextView.setText(sb.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
