package com.example.pc.bookmanagerapplication.models;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CircleWithTextView extends View {

    Paint mPaint;

    public CircleWithTextView(Context context) {
        super(context);
        init(null);
    }

    public CircleWithTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CircleWithTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init (@Nullable AttributeSet attributeSet) {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int x = this.getWidth()/2;
        int y = this.getHeight()/2;
        int radius = 200;

        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);

        canvas.drawPaint(mPaint);

        mPaint.setColor(Color.parseColor("#33b5e5"));
        canvas.drawCircle(x, y, radius, mPaint);

        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(45);

        canvas.drawText("Tap this circle",getWidth()/2, getHeight()/2 - 25, mPaint);
        canvas.drawText("to start the App",getWidth()/2, getHeight()/2 + 25, mPaint);
    }
}
