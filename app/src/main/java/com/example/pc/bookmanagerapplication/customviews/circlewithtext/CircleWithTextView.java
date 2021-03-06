package com.example.pc.bookmanagerapplication.customviews.circlewithtext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.pc.bookmanagerapplication.customviews.circlewithtext.base.Touchable;

public class CircleWithTextView extends View implements Touchable{

    Paint mPaint;
    private float mCircleX;
    private float mCircleY;
    private int mCircleRadius;

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

    public float getCircleX() {
        return mCircleX;
    }

    public float getCircleY() {
        return mCircleY;
    }

    public int getCircleRadius() {
        return mCircleRadius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mCircleX = this.getWidth()/2;
        mCircleY = this.getHeight()/2;
        mCircleRadius = 300;

        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);

        canvas.drawPaint(mPaint);

        mPaint.setColor(Color.parseColor("#33b5e5"));
        canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mPaint);

        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(60);

        canvas.drawText("Tap this circle",getWidth()/2, getHeight()/2 - 25, mPaint);
        canvas.drawText("to start the App",getWidth()/2, getHeight()/2 + 25, mPaint);
    }

    @Override
   public boolean isTouched(MotionEvent event){

       float touchX = event.getX();
       float touchY = event.getY();
       double deltaX = Math.pow(mCircleX - touchX, 2);
       double deltaY = Math.pow(mCircleY - touchY, 2);

       return deltaX + deltaY < Math.pow(mCircleRadius, 2);
   }
}
