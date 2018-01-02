package vn.asiantech.internship.canvas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.Arrays;
import java.util.Collections;

import vn.asiantech.internship.R;
import vn.asiantech.internship.viewpager.utils.ScreenUtil;

import static android.view.MotionEvent.INVALID_POINTER_ID;

/**
 * Created by phongle on 25/12/2560.
 * Class custom view Chart
 */
public class ChartCustomView extends View {
    private Paint mPaint;
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.f;
    private float mLastTouchX;
    private float mLastTouchY;
    private int mActivePointerId;
    private float mPosX = 0;
    private float mPosY = 0;
    private float mColumnWidth;
    private Integer[] mListA = {10, 4, 6, 8, 6, 7, 4};
    private Integer[] mListB = {5, 1, 7, 7, 10, 9, 8};
    private Integer[] mListC = {8, 10, 3, 4, 5, 8, 5};
    private int mMaxDistanceList;
    private int mMinDistanceList;

    public ChartCustomView(Context context) {
        this(context, null);
    }

    public ChartCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ChartCustomView,
                0, 0);
        try {
            mColumnWidth = a.getFloat(R.styleable.ChartCustomView_column_width, 5);
        } finally {
            a.recycle();
        }
        init();
        getMaxDistance();
        getMinDistance();
    }

    private void init() {
        mPaint = new Paint();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        mScaleGestureDetector.onTouchEvent(event);
        final int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                final int pointerIndex = MotionEventCompat.getActionIndex(event);
                final float x = MotionEventCompat.getX(event, pointerIndex);
                final float y = MotionEventCompat.getY(event, pointerIndex);

                // Remember where we started (for dragging)
                mLastTouchX = x;
                mLastTouchY = y;
                // Save the ID of this pointer (for dragging)
                mActivePointerId = MotionEventCompat.getPointerId(event, 0);
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                // Find the index of the active pointer and fetch its position
                final int pointerIndex =
                        MotionEventCompat.findPointerIndex(event, mActivePointerId);

                final float x = MotionEventCompat.getX(event, pointerIndex);
                final float y = MotionEventCompat.getY(event, pointerIndex);

                // Calculate the distance moved
                final float dx = x - mLastTouchX;
                final float dy = y - mLastTouchY;

                mPosX += dx;
                mPosY += dy;

                invalidate();

                // Remember this touch position for the next move event
                mLastTouchX = x;
                mLastTouchY = y;

                break;
            }

            case MotionEvent.ACTION_UP: {
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_CANCEL: {
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_POINTER_UP: {

                final int pointerIndex = MotionEventCompat.getActionIndex(event);
                final int pointerId = MotionEventCompat.getPointerId(event, pointerIndex);

                if (pointerId == mActivePointerId) {
                    // This was our active pointer going up. Choose a new
                    // active pointer and adjust accordingly.
                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mLastTouchX = MotionEventCompat.getX(event, newPointerIndex);
                    mLastTouchY = MotionEventCompat.getY(event, newPointerIndex);
                    mActivePointerId = MotionEventCompat.getPointerId(event, newPointerIndex);
                }
                break;
            }
        }
        invalidate();
        return true;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(mPosX,mPosY);
        canvas.scale(mScaleFactor, mScaleFactor);
        // Define text
        String maxDistance = mMaxDistanceList + " Km";
        String minDistance = mMinDistanceList + " Km";
        String startDay = "22 Dec";
        String stopDay = "29 Dec";
        // Set up metric
        mPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.text_size));
        float widthMaxDistanceText = mPaint.measureText(maxDistance);
        float widthMinDistanceText = mPaint.measureText(minDistance);
        float widthStopDayText = mPaint.measureText(stopDay);
        float heightText = getResources().getDimension(R.dimen.text_size);
        float verticalAxis = getResources().getDimension(R.dimen.container_padding_vertical) + mPaint.measureText(maxDistance) + getResources().getDimension(R.dimen.text_margin);
        float heightVerticalAxis = (float) (ScreenUtil.getHeightScreen(getContext()) * 0.4 - getResources().getDimension(R.dimen.container_padding_horizontal) * 2);
        float horizontalAxis = heightVerticalAxis + getResources().getDimension(R.dimen.container_padding_horizontal);
        float widthHorizontalAxis = (ScreenUtil.getWidthScreen(getContext()) - verticalAxis - getResources().getDimension(R.dimen.container_padding_vertical));
        float marginChart = (widthHorizontalAxis - mColumnWidth * 3 - getResources().getDimension(R.dimen.column_margin) * 2 - getResources().getDimension(R.dimen.chart_padding_vertical) * 2);
        float mRadius = 5;
        // Draw
        mPaint.setFakeBoldText(true);
        mPaint.setColor(Color.GRAY);
        canvas.drawText(maxDistance, getResources().getDimension(R.dimen.container_padding_vertical),
                getResources().getDimension(R.dimen.container_padding_horizontal) + heightText / 2, mPaint);
        mPaint.setStrokeWidth(getResources().getDimension(R.dimen.width_line));
        mPaint.setColor(Color.LTGRAY);
        canvas.drawLine(verticalAxis,
                getResources().getDimension(R.dimen.container_padding_horizontal),
                verticalAxis + widthHorizontalAxis,
                getResources().getDimension(R.dimen.container_padding_horizontal),
                mPaint);
        canvas.drawLine(verticalAxis,
                getResources().getDimension(R.dimen.container_padding_horizontal) + heightVerticalAxis / 2,
                verticalAxis + widthHorizontalAxis,
                getResources().getDimension(R.dimen.container_padding_horizontal) + heightVerticalAxis / 2,
                mPaint);
        canvas.drawLine(verticalAxis,
                horizontalAxis,
                verticalAxis + widthHorizontalAxis,
                horizontalAxis,
                mPaint);
        mPaint.setColor(Color.GRAY);
        canvas.drawText(minDistance, getResources().getDimension(R.dimen.container_padding_vertical) + (widthMaxDistanceText - widthMinDistanceText),
                horizontalAxis, mPaint);
        canvas.drawText(startDay, verticalAxis,
                horizontalAxis + getResources().getDimension(R.dimen.text_margin) + heightText, mPaint);
        canvas.drawText(stopDay, verticalAxis + widthHorizontalAxis - widthStopDayText,
                horizontalAxis + getResources().getDimension(R.dimen.text_margin) + heightText, mPaint);
        mPaint.setColor(getResources().getColor(R.color.dark_violet));
        mPaint.setAntiAlias(true);
        for (int i = 0; i < 7; i++) {
            float startX = verticalAxis + getResources().getDimension(R.dimen.chart_padding_vertical);
            canvas.drawRoundRect(new RectF(startX + i * marginChart / 6,
                            getResources().getDimension(R.dimen.container_padding_horizontal) + getHeightColumnChart(mListA[i]) * heightVerticalAxis,
                            mColumnWidth + startX + i * marginChart / 6,
                            horizontalAxis),
                    mRadius,
                    mRadius,
                    mPaint);
        }
        mPaint.setColor(getResources().getColor(R.color.blue_dark));
        for (int i = 0; i < 7; i++) {
            float startX = verticalAxis + getResources().getDimension(R.dimen.chart_padding_vertical) + mColumnWidth + getResources().getDimension(R.dimen.column_margin);
            canvas.drawRoundRect(new RectF(startX + i * marginChart / 6,
                            getResources().getDimension(R.dimen.container_padding_horizontal) + getHeightColumnChart(mListB[i]) * heightVerticalAxis,
                            mColumnWidth + startX + i * marginChart / 6,
                            horizontalAxis),
                    mRadius,
                    mRadius,
                    mPaint);
        }
        mPaint.setColor(getResources().getColor(R.color.orange));
        for (int i = 0; i < 7; i++) {
            float startX = verticalAxis + getResources().getDimension(R.dimen.chart_padding_vertical) + mColumnWidth * 2 + getResources().getDimension(R.dimen.column_margin) * 2;
            canvas.drawRoundRect(new RectF(startX + i * marginChart / 6,
                            getResources().getDimension(R.dimen.container_padding_horizontal) + getHeightColumnChart(mListC[i]) * heightVerticalAxis,
                            mColumnWidth + startX + i * marginChart / 6,
                            horizontalAxis),
                    mRadius,
                    mRadius,
                    mPaint);
        }
        canvas.restore();
    }

    // Function convert distance to column in chart
    private float getHeightColumnChart(float distance) {
        return 1 - ((distance - mMinDistanceList) / (mMaxDistanceList - mMinDistanceList));
    }

    private void getMaxDistance() {
        int maxA = Collections.max(Arrays.asList(mListA));
        int maxB = Collections.max(Arrays.asList(mListB));
        int maxC = Collections.max(Arrays.asList(mListC));
        mMaxDistanceList = Math.max(maxA, Math.max(maxB, maxC));
    }

    private void getMinDistance() {
        int minA = Collections.min(Arrays.asList(mListA));
        int minB = Collections.min(Arrays.asList(mListB));
        int minC = Collections.min(Arrays.asList(mListC));
        mMinDistanceList = Math.min(minA, Math.min(minB, minC));
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
