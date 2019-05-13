package com.example.kotlinexam.item08java;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

public class MyCharacter extends android.support.v7.widget.AppCompatImageView {
    private static final String TAG = MyCharacter.class.getSimpleName();

    public MyCharacter(Context context) {
        super(context);
    }

    public MyCharacter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCharacter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "View touch ");
        return true;
    }
}
