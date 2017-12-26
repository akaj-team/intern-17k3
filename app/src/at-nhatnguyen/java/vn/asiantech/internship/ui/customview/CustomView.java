package vn.asiantech.internship.ui.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;

/**
 * Created by hoangnhat on 25/12/2017.
 * The CustomView for CanvasActivity
 */
public class CustomView extends View {
    // Declare field for new ScaleListener
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;
    private List<Integer> resultPeople1;
    private List<Integer> resultPeople2;
    private List<Integer> resultPeople3;
    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint3;
    private Paint mPaint;
    private float mMoveX;
    private float mTouchX;
    private float size;
    private int mMarginTop;
    private int mRange;
    private int mStart1;
    private int mStart2;
    private int mStart3;
    private int mStartLine;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // New obj of ScaleListener
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        // Get attrs from file xml
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);
        size = typedArray.getInteger(R.styleable.CustomView_stroke_with, 10);
        mRange = (int) size * 5;
        mMarginTop = 100;
        mStart1 = 170;
        mStart2 = 200;
        mStart3 = 230;
        mStartLine = 150;
        init();
        initDatas();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mScaleDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() == 1) {
                    mMoveX =event.getX()- mTouchX + mMoveX;
                    mTouchX = event.getX();
                    mStart1 = (int)(170+ mMoveX);
                    mStart2 = (int)(200+ mMoveX);
                    mStart3 = (int)(230+ mMoveX);
                    mStartLine = (int)(150+ mMoveX);
                }
                break;
            case MotionEvent.ACTION_DOWN:
                mTouchX = event.getX();
                break;
        }
        invalidate();
        return true;
    }

    private void init() {
        mPaint1 = new Paint();
        mPaint1.setColor(Color.BLUE);
        mPaint1.setStrokeWidth(size);
        mPaint1.setStrokeCap(Paint.Cap.ROUND);
        mPaint1.setAntiAlias(true);
        mPaint2 = new Paint();
        mPaint2.setColor(Color.RED);
        mPaint2.setStrokeWidth(size);
        mPaint2.setStrokeCap(Paint.Cap.ROUND);
        mPaint2.setAntiAlias(true);
        mPaint3 = new Paint();
        mPaint3.setColor(Color.YELLOW);
        mPaint3.setStrokeWidth(size);
        mPaint3.setStrokeCap(Paint.Cap.ROUND);
        mPaint3.setAntiAlias(true);
        mPaint = new Paint();
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(2F);
        mPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.text_size));
    }

    private void initDatas() {
        resultPeople1 = new ArrayList<>();
        resultPeople2 = new ArrayList<>();
        resultPeople3 = new ArrayList<>();
        resultPeople1.add(1);
        resultPeople1.add(7);
        resultPeople1.add(5);
        resultPeople1.add(2);
        resultPeople1.add(9);
        resultPeople1.add(6);
        resultPeople1.add(5);
        resultPeople2.add(1);
        resultPeople2.add(3);
        resultPeople2.add(5);
        resultPeople2.add(2);
        resultPeople2.add(2);
        resultPeople2.add(6);
        resultPeople2.add(5);
        resultPeople3.add(1);
        resultPeople3.add(8);
        resultPeople3.add(5);
        resultPeople3.add(2);
        resultPeople3.add(11);
        resultPeople3.add(6);
        resultPeople3.add(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        int mMax = 0;
        int oneKm = 30;
        int border = 13;
        canvas.scale(mScaleFactor, mScaleFactor);
        for (int i = 0; i < resultPeople3.size(); i++) {
            if (resultPeople1.get(i) > mMax) {
                mMax = resultPeople1.get(i);
            }
            if (resultPeople2.get(i) > mMax) {
                mMax = resultPeople2.get(i);
            }
            if (resultPeople3.get(i) > mMax) {
                mMax = resultPeople3.get(i);
            }
        }
        canvas.drawLine(mStartLine, mMarginTop - border, resultPeople1.size() * mRange + 120, mMarginTop - border, mPaint);
        canvas.drawLine(mStartLine, (mMax * oneKm) / 2 + mMarginTop - border, resultPeople1.size() * mRange + 120, (mMax * oneKm) / 2 + mMarginTop - border, mPaint);
        canvas.drawLine(mStartLine, mMax * oneKm + mMarginTop + border, resultPeople1.size() * mRange + 120, mMax * oneKm + mMarginTop + border, mPaint);
        for (int i = 0; i < resultPeople3.size(); i++) {
            canvas.drawLine(i * mRange + mStart1, (mMax * oneKm + mMarginTop) - resultPeople1.get(i) * oneKm, i * mRange + mStart1, mMax * oneKm + mMarginTop, mPaint1);
            canvas.drawLine(i * mRange + mStart2, (mMax * oneKm + mMarginTop) - resultPeople2.get(i) * oneKm, i * mRange + mStart2, mMax * oneKm + mMarginTop, mPaint2);
            canvas.drawLine(i * mRange + mStart3, (mMax * oneKm + mMarginTop) - resultPeople3.get(i) * oneKm, i * mRange + mStart3, mMax * oneKm + mMarginTop, mPaint3);
        }
        canvas.drawText(mMax + " km", 10, mMarginTop - border + (mPaint.getTextSize() / 2), mPaint);
        canvas.drawText((float) mMax / 2 + " km", 10, (mMax * oneKm) / 2 + mMarginTop - border + (mPaint.getTextSize() / 2), mPaint);
        canvas.drawText("24 Jul", 130, mMax * oneKm + mMarginTop + 100, mPaint);
        canvas.drawText("9 Oct", resultPeople1.size() * mRange + 30, mMax * oneKm + mMarginTop + 100, mPaint);
        canvas.restore();
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));
            invalidate();
            return true;
        }
    }
}
