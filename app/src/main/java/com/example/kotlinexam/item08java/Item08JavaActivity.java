package com.example.kotlinexam.item08java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.kotlinexam.R;

public class Item08JavaActivity extends AppCompatActivity {
    private static final String TAG = Item08JavaActivity.class.getSimpleName();

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item08_java);

        mImageView = findViewById(R.id.imageView);
        mImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("mImageView", "onTouch: " + event);
                return true;
            }
        });


        findViewById(R.id.button).setOnClickListener(v -> Log.d(TAG, "onClick: "));
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent: " + ev.toString());
        float x = ev.getX();
        float y = ev.getY() - 212;

        mImageView.setX(x - (mImageView.getWidth() / 2));
        mImageView.setY(y - (mImageView.getHeight() / 2));

        return super.dispatchTouchEvent(ev);
    }
}
