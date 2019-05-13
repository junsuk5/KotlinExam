package com.example.kotlinexam.item08java;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class GameViewGroup extends RelativeLayout {
    private Paint mPaint = new Paint();
    private float mX;
    private float mY;

    public GameViewGroup(Context context) {
        super(context);
    }

    public GameViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GameViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    // 자식한테 터치를 보낼건지 말건지 결정 하는 부분
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mX = ev.getX();
        mY = ev.getY();

        // onDraw를 호출
        invalidate();
        // true = 안 보내, false = 보내, super.dispatchTouchEvent(ev) 원래 흐름에 맡긴다
        return super.dispatchTouchEvent(ev);
    }

    // 화면에 그리기
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mX, mY, 50, mPaint);
    }
}
