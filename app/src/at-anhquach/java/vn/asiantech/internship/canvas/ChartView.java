package vn.asiantech.internship.canvas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vn.asiantech.internship.R;

/**
 * Created by anh.quach on 12/25/17.
 * Custom  Chart
 */
public class ChartView extends View {
    private Paint mPaint;
    private float mWidth;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;
    private List<Integer> mDistanceAs = new ArrayList<>();
    private List<Integer> mDistanceBs = new ArrayList<>();
    private List<Integer> mDistanceCs = new ArrayList<>();
    private float mCorner;
    private float mDistanceMove;
    private float mWitdhScreen = ScreenUtil.getWidthScreen(getContext());
    private float mHeightScreen = ScreenUtil.getHeightScreen(getContext());
    private int mOxChart = 130;

    public ChartView(Context context) {
        this(context, null);
    }

    public ChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.ChartView, 0, 0
        );
        try {
            mWidth = a.getInteger(R.styleable.ChartView_line_width, 20);
        } finally {
            a.recycle();
        }
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        initDistanceA();
        initDistanceB();
        initDistanceC();
        mPaint = new Paint();
    }

    private void initDistanceA() {
        mDistanceAs.add(1);
        mDistanceAs.add(7);
        mDistanceAs.add(5);
        mDistanceAs.add(2);
        mDistanceAs.add(9);
        mDistanceAs.add(6);
        mDistanceAs.add(6);
        mDistanceAs.add(9);
        mDistanceAs.add(2);
    }

    private void initDistanceB() {
        mDistanceBs.add(1);
        mDistanceBs.add(3);
        mDistanceBs.add(5);
        mDistanceBs.add(2);
        mDistanceBs.add(2);
        mDistanceBs.add(6);
        mDistanceBs.add(6);
        mDistanceBs.add(4);
        mDistanceBs.add(7);
    }

    private void initDistanceC() {
        mDistanceCs.add(1);
        mDistanceCs.add(8);
        mDistanceCs.add(5);
        mDistanceCs.add(2);
        mDistanceCs.add(11);
        mDistanceCs.add(6);
        mDistanceCs.add(12);
        mDistanceCs.add(5);
        mDistanceCs.add(4);
    }

    private float maxLists() {
        int max1 = Collections.max(mDistanceAs);
        int max2 = Collections.max(mDistanceBs);
        int max3 = Collections.max(mDistanceCs);
        return (float) Math.max(Math.max(max1, max2), max3);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mScaleDetector.onTouchEvent(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mCorner = (ev.getX() - mDistanceMove) + mCorner;
                mDistanceMove = ev.getX();
                break;
            case MotionEvent.ACTION_DOWN:
                mDistanceMove = ev.getX();
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor);
        mPaint.setColor(getResources().getColor(R.color.colorGrayDark));
        mPaint.setTextSize(getResources().getDimension(R.dimen.textsize40));
        drawText(canvas, getContext().getString(R.string.distance_km, ((int) maxLists())), maxLists(), mPaint);
        drawLine(canvas, 0, mPaint);
        drawLine(canvas, maxLists(), mPaint);
        drawText(canvas, getContext().getString(R.string.distance_km, ((int) 0)), 0, mPaint);
        drawLine(canvas, maxLists() / 2, mPaint);
        for (int i = 0; i < mDistanceAs.size(); i++) {
            drawRect(canvas, 0, mDistanceAs.get(i), R.color.colorPurple800, i, mPaint);
            drawRect(canvas, mWidth + 5, mDistanceBs.get(i), R.color.colorCyanA700, i, mPaint);
            drawRect(canvas, 2 * mWidth + 5, mDistanceCs.get(i), R.color.colorOrange500, i, mPaint);
        }
        canvas.restore();
    }

    private void drawText(Canvas canvas, String str, float y, Paint paint) {
        canvas.drawText(str, 0, mHeightScreen / 2 - y * 50, paint);
    }

    private void drawLine(Canvas canvas, float y, Paint paint) {
        canvas.drawLine(mOxChart, mHeightScreen / 2 - y * 50, mWitdhScreen, mHeightScreen / 2 - y * 50, paint);
    }

    private void drawRect(Canvas canvas, float posInit, int height, int color, int posInArray, Paint paint) {
        paint.setColor(getResources().getColor(color));
        float left = mOxChart + posInit + (5 * mWidth + 10) * posInArray + mCorner;
        canvas.drawRoundRect(new RectF(left, mHeightScreen / 2 - height * 50,
                left + mWidth, mHeightScreen / 2), 10, 5, paint);
    }

    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
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
