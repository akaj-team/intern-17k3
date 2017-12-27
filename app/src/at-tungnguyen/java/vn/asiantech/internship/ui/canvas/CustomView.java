package vn.asiantech.internship.ui.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.Arrays;
import java.util.Collections;

import vn.asiantech.internship.R;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 25/12/2017.
 */

public class CustomView extends View {
    private Paint mPaint;
    private Paint mPaint2;
    private Paint mPaint3;
    private Paint mPaintText;
    private Paint mPaintMaxLine;
    private Integer listPeoPel[] = {1, 7, 5, 2, 9, 6, 5};
    private Integer listPeoPel2[] = {1, 3, 5, 2, 2, 6, 5};
    private Integer listPeoPel3[] = {1, 8, 5, 2, 11, 6, 5};
    private int mStartX = 60;
    private int mStopX = 60;
    private int mStartY = 50;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;
    private int mFirstX1 = 80;
    private int mFirstX2 = 100;
    private int mFirstX3 = 120;
    private float mDownX;
    private float mMoveX;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    /**
     * getMaxValues from Array
     *
     * @return
     */
    private float getMaxValues() {
        int maxPeople1 = Collections.max(Arrays.asList(listPeoPel));
        int maxPeople2 = Collections.max(Arrays.asList(listPeoPel2));
        int maxPeople3 = Collections.max(Arrays.asList(listPeoPel3));
        float mMax = Math.max(Math.max(maxPeople1, maxPeople2), maxPeople3);
        return mMax;
    }

    /**
     * onTouchEvent
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mScaleDetector.onTouchEvent(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mMoveX = mMoveX + ev.getX() - mDownX;
                mDownX = ev.getX();
                mFirstX1 = (int) (mMoveX + 80);
                mFirstX2 = (int) (mMoveX + 100);
                mFirstX3 = (int) (mMoveX + 120);
                break;
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getX();
                break;
        }
        invalidate();
        return true;
    }

    /**
     * onDraw canvas
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        int mStopY = getHeight() / 2;
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor);
        drawText(canvas);
        drawLine(canvas);
        for (int i = 0; i < listPeoPel.length; i++) {
            float x = 1.5f;
            canvas.drawLine(i * mStartX * x + mFirstX1, getHeight() / 2 - listPeoPel[i] * mStartY, i * mStopX * x + mFirstX1, mStopY, mPaint);
            canvas.drawLine(i * mStartX * x + mFirstX2, getHeight() / 2 - listPeoPel2[i] * mStartY, i * mStopX * x + mFirstX2, mStopY, mPaint2);
            canvas.drawLine(i * mStartX * x + mFirstX3, getHeight() / 2 - listPeoPel3[i] * mStartY, i * mStopX * x + mFirstX3, mStopY, mPaint3);
        }
        canvas.restore();
    }

    /**
     * drawLine with canvas
     *
     * @param canvas
     */
    private void drawLine(Canvas canvas) {
        canvas.drawLine(80, getHeight() / 2, getWidth(), getHeight() / 2, mPaintMaxLine);
        canvas.drawLine(80, getHeight() / 2 - getMaxValues() / 2 * mStartY, getWidth(), getHeight() / 2 - getMaxValues() / 2 * mStartY, mPaintMaxLine);
        canvas.drawLine(80, getHeight() / 2 - getMaxValues() * mStartY, getWidth(), getHeight() / 2 - getMaxValues() * mStartY, mPaintMaxLine);
    }

    /**
     * drawText with canvas
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        canvas.drawText(getResources().getString(R.string.tv_canvas_km), 0, getHeight() / 2, mPaintText);
        canvas.drawText(getResources().getString(R.string.tv_canvas_5km), 0, getHeight() / 2 - getMaxValues() / 2 * mStartY, mPaintText);
        canvas.drawText(getResources().getString(R.string.tv_canvas_10km), 0, getHeight() / 2 - getMaxValues() * mStartY, mPaintText);
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

    /**
     * initPaint
     */
    private void initPaint() {
        //mPaint Red
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10F);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        //mPaint Green
        mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint2.setColor(Color.GREEN);
        mPaint2.setStrokeWidth(10F);
        mPaint2.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint2.setStyle(Paint.Style.FILL);
        //mPaint Blue
        mPaint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint3.setColor(Color.BLUE);
        mPaint3.setStrokeWidth(10F);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint3.setAntiAlias(true);
        mPaint3.setStyle(Paint.Style.FILL);
        //Paint text
        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintText.setStrokeWidth(4F);
        mPaintText.setTextSize(30);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaintText.setColor(Color.GRAY);
        //Paint Max Line
        mPaintMaxLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintMaxLine.setStrokeWidth(2F);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaintMaxLine.setColor(Color.GRAY);
    }
}
