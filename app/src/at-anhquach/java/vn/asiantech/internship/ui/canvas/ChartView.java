package vn.asiantech.internship.ui.canvas;

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
import vn.asiantech.internship.ui.viewpager_tablayout.ScreenUtil;

/**
 * Created by anh.quach on 12/29/17.
 * Chart View
 */
public class ChartView extends View {
    private boolean dragged = true;
    double distance;
    private float startX = 0f;
    private float startY = 0f;
    private float translateX = 0f;
    private float translateY = 0f;
    private float previousTranslateX = 0f;
    private float previousTranslateY = 0f;

    private int mode;
    private Paint mPaint;
    private float mWidth;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;

    private List<Integer> mDistanceAs = new ArrayList<>();
    private List<Integer> mDistanceBs = new ArrayList<>();
    private List<Integer> mDistanceCs = new ArrayList<>();

    private float mWitdhScreen = ScreenUtil.getWidthScreen(getContext());
    private float mHeightScreen = ScreenUtil.getHeightScreen(getContext());
    private int mOxChart = 130;
    private int mDistanceBetweenCharts = 5;
    private int mEnlarge = 50;

    public ChartView(Context context) {
        this(context, null);
    }

    @SuppressLint("ClickableViewAccessibility")
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
        mDistanceAs.add(2);
        mDistanceAs.add(2);
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
        mDistanceBs.add(7);
        mDistanceBs.add(7);
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
        mDistanceCs.add(4);
        mDistanceCs.add(4);
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
        int DRAG = 1;
        int ZOOM = 2;
        switch (ev.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                mode = DRAG;
                startX = ev.getX() - previousTranslateX;
                startY = ev.getY() - previousTranslateY;
                break;
            case MotionEvent.ACTION_MOVE:
                translateX = ev.getX() - startX;
                translateY = ev.getY() - startY;
                float currX = ev.getX();
                float currY = ev.getY();
                distance = Math.sqrt(Math.pow(currX - (startX + previousTranslateX), 2) +
                        Math.pow(currY - (startY + previousTranslateY), 2));
                if (distance > 0) {
                    dragged = true;
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                mode = ZOOM;
                break;
            case MotionEvent.ACTION_UP:
                mode = 0;
                dragged = false;
                previousTranslateX = translateX;
                previousTranslateY = translateY;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mode = DRAG;
                previousTranslateX = translateX;
                previousTranslateY = translateY;
                break;
        }
        mScaleDetector.onTouchEvent(ev);
        if ((mode == DRAG && mScaleFactor != 1f && dragged) || mode == ZOOM) {
            invalidate();
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor);
        translate(canvas);
        for (int i = 0; i < mDistanceAs.size(); i++) {
            drawRoundRect(canvas, 0, mDistanceAs.get(i), R.color.colorPurple800, i, mPaint);
            drawRoundRect(canvas, mWidth + mDistanceBetweenCharts, mDistanceBs.get(i), R.color.colorCyanA700, i, mPaint);
            drawRoundRect(canvas, 2 * mWidth + mDistanceBetweenCharts * 2, mDistanceCs.get(i), R.color.colorOrange500, i, mPaint);
        }
        mPaint.setColor(getResources().getColor(R.color.colorWhite));
        drawRect(canvas, 0, mPaint);
        drawRect(canvas, mWitdhScreen - 50, mPaint);
        mPaint.setColor(getResources().getColor(R.color.colorGrayDark));
        mPaint.setTextSize(getResources().getDimension(R.dimen.textsize40));
        drawText(canvas, getContext().getString(R.string.distance_km, ((int) maxLists())), maxLists(), mPaint);
        drawLine(canvas, 0, mPaint);
        drawLine(canvas, maxLists(), mPaint);
        drawText(canvas, getContext().getString(R.string.distance_km, ((int) 0)), 0, mPaint);
        drawLine(canvas, maxLists() / 2, mPaint);
        canvas.restore();
    }

    private void translate(Canvas canvas) {
        if ((translateX * -1) < 0) {
            translateX = 0;
        } else if ((translateX * -1) > (mScaleFactor - 1) * getWidth()) {
            translateX = (1 - mScaleFactor) * getWidth();
        }
        if (translateY * -1 < 0) {
            translateY = 0;
        } else if ((translateY * -1) > (mScaleFactor - 1) * getHeight()) {
            translateY = (1 - mScaleFactor) * getHeight();
        }
        canvas.translate(translateX / mScaleFactor, translateY / mScaleFactor);
    }

    private void drawText(Canvas canvas, String str, float y, Paint paint) {
        canvas.drawText(str, 0, mHeightScreen / 2 - y * mEnlarge, paint);
    }

    private void drawLine(Canvas canvas, float y, Paint paint) {
        canvas.drawLine(mOxChart, mHeightScreen / 2 - y * mEnlarge, mWitdhScreen - 50, mHeightScreen / 2 - y * mEnlarge, paint);
    }

    private void drawRoundRect(Canvas canvas, float posInit, int height, int color, int posInArray, Paint paint) {
        paint.setColor(getResources().getColor(color));
        float distanceBetweenGroupChart = 5 * mWidth + mDistanceBetweenCharts * 2;
        float left = mOxChart + posInit + distanceBetweenGroupChart * posInArray;
        canvas.drawRoundRect(new RectF(left, mHeightScreen / 2 - height * mEnlarge,
                left + mWidth, mHeightScreen / 2), 10, 5, paint);
    }

    private void drawRect(Canvas canvas, float left, Paint paint) {
        canvas.drawRect(left, mHeightScreen / 2 - maxLists() * mEnlarge, left + 130, mHeightScreen / 2, paint);
    }

    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            float MIN_ZOOM = 1F;
            float MAX_ZOOM = 5F;
            mScaleFactor = Math.max(MIN_ZOOM, Math.min(mScaleFactor, MAX_ZOOM));
            return true;
        }
    }
}
