package vn.asiantech.internship.ui.canvas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.Arrays;

import vn.asiantech.internship.R;

/**
 * Created by vietphan on 25/12/2017.
 * Class CustomView
 */
public class CustomView extends View {
    private static final int STROKE_WIDTH_LINE = 28;
    private static final int STROKE_WIDTH_LINE_DISTANCE = 2;
    private static final int START_LINE_DISTANCE = 120;
    private static final int START_TEXT_DISTANCE = 10;
    private static final int TEXT_BETWEEN_LINE = 10;
    private static final int RESIDUAL_DISTANCE = 13;
    private Paint mPaintA;
    private Paint mPaintB;
    private Paint mPaintC;
    private Paint mPaintMonth;
    private Paint mPaintDistance;
    private Paint mPaintLineDistance;
    private Paint mPaintLeftBox;
    private int[] mPersonA;
    private int[] mPersonB;
    private int[] mPersonC;
    private float mMoveX;
    private float mTouchX;
    private int mStartXPersonA;
    private int mStartXPersonB;
    private int mStartXPersonC;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        initViews();
        initData();
    }

    private void initViews() {
//        mPaintA
        mPaintA = new Paint();
        mPaintA.setColor(Color.RED);
        mPaintA.setStrokeWidth(STROKE_WIDTH_LINE);
        mPaintA.setStrokeCap(Paint.Cap.ROUND);
        mPaintA.setAntiAlias(true);
//        mPaintB
        mPaintB = new Paint();
        mPaintB.setColor(Color.BLUE);
        mPaintB.setStrokeWidth(STROKE_WIDTH_LINE);
        mPaintB.setStrokeCap(Paint.Cap.ROUND);
        mPaintB.setAntiAlias(true);
//        mPaintC
        mPaintC = new Paint();
        mPaintC.setColor(Color.GREEN);
        mPaintC.setStrokeWidth(STROKE_WIDTH_LINE);
        mPaintC.setStrokeCap(Paint.Cap.ROUND);
        mPaintC.setAntiAlias(true);
//        mPaintMonth
        mPaintMonth = new Paint();
        mPaintMonth.setColor(Color.DKGRAY);
        mPaintMonth.setTextSize(getResources().getDimensionPixelSize(R.dimen.text_size_chart));
//        mPaintDistance
        mPaintDistance = new Paint();
        mPaintDistance.setColor(Color.DKGRAY);
        mPaintDistance.setTextSize(getResources().getDimensionPixelSize(R.dimen.text_size_chart));
//        mPaintLineDistance
        mPaintLineDistance = new Paint();
        mPaintLineDistance.setStrokeWidth(STROKE_WIDTH_LINE_DISTANCE);
        mPaintLineDistance.setColor(Color.GRAY);
//        mPaintLeftBox
        mPaintLeftBox = new Paint();
        mPaintLeftBox.setColor(Color.WHITE);
    }

    private void initData() {
        mStartXPersonA = 100;
        mStartXPersonB = 130;
        mStartXPersonC = 160;
        mPersonA = getResources().getIntArray(R.array.person_one);
        mPersonB = getResources().getIntArray(R.array.person_two);
        mPersonC = getResources().getIntArray(R.array.person_three);
    }

    private int setStartX(int startX) {
        return startX * 150;
    }

    private int setStartY() {
        return this.getHeight() / 2;
    }

    private int setStopY(int stopY) {
        return stopY * 50;
    }

    private int setStartYText() {
        return setStartY() + 60;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor);
        drawLineDistance(canvas);
        drawLinePerson(canvas);
        drawLeftBox(canvas);
        drawText(canvas);
        canvas.restore();
    }

    //    Draw left box for hint chart left to left screen
    private void drawLeftBox(Canvas canvas) {
        canvas.drawRect(0, 0, 120, getHeight(), mPaintLeftBox);
    }

    //  Draw line person A, B, C
    private void drawLinePerson(Canvas canvas) {
        for (int i = 0; i < mPersonA.length; i++) {
            int startXA = mStartXPersonA + setStartX(i);
            int startXB = mStartXPersonB + setStartX(i);
            int startXC = mStartXPersonC + setStartX(i);
            int startY = setStartY() - RESIDUAL_DISTANCE;
            int stopYA = setStartY() - setStopY(mPersonA[i]) + RESIDUAL_DISTANCE;
            int stopYB = setStartY() - setStopY(mPersonB[i]) + RESIDUAL_DISTANCE;
            int stopYC = setStartY() - setStopY(mPersonC[i]) + RESIDUAL_DISTANCE;
            canvas.drawLine(startXA, startY, startXA, stopYA, mPaintA);
            canvas.drawLine(startXB, startY, startXB, stopYB, mPaintB);
            canvas.drawLine(startXC, startY, startXC, stopYC, mPaintC);
        }
    }

    //   Draw lineDistance max, min, between
    private void drawLineDistance(Canvas canvas) {
        int maxDistance = getMaxValueDistance();
        int startYLineTop = setStartY() - setStopY(maxDistance);
        int startYLineBetween = setStartY() - setStopY(maxDistance) / 2;
        canvas.drawLine(START_LINE_DISTANCE, startYLineTop, getWidth(), startYLineTop, mPaintLineDistance);
        canvas.drawLine(START_LINE_DISTANCE, startYLineBetween, getWidth(), startYLineBetween, mPaintLineDistance);
        canvas.drawLine(START_LINE_DISTANCE, setStartY(), getWidth(), setStartY(), mPaintLineDistance);
    }

    private void drawText(Canvas canvas) {
        canvas.drawText("24 Jul", START_LINE_DISTANCE, setStartYText(), mPaintMonth);
        canvas.drawText("9 Oct", getWidth() - 90, setStartYText(), mPaintMonth);
        canvas.drawText("0 Km", START_TEXT_DISTANCE, setStartY() + TEXT_BETWEEN_LINE, mPaintDistance);
        canvas.drawText(getMaxValueDistance() + " Km", START_TEXT_DISTANCE,
                setStartY() - setStopY(getMaxValueDistance()) + TEXT_BETWEEN_LINE, mPaintDistance);
    }

    /**
     * Find maximum (largest) value in array using array sort
     */
    private int getMaxValueDistance() {
        Arrays.sort(mPersonA);
        Arrays.sort(mPersonB);
        Arrays.sort(mPersonC);
        int maxA = mPersonA[mPersonA.length - 1];
        int maxB = mPersonB[mPersonB.length - 1];
        int maxC = mPersonC[mPersonC.length - 1];
        return Math.max(maxA, Math.max(maxB, maxC));
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mScaleDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() == 1) {
                    mMoveX = event.getX() - mTouchX + mMoveX;
                    mTouchX = event.getX();
                    updateStartXPersonLine(mMoveX);
                }
                break;
        }
        invalidate();
        return true;
    }

    private void updateStartXPersonLine(float moveX) {
        mStartXPersonA = (int) (100 + moveX);
        mStartXPersonB = (int) (130 + moveX);
        mStartXPersonC = (int) (160 + moveX);
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
