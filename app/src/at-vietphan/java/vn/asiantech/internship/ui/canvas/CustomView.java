package vn.asiantech.internship.ui.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
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
    private Paint mPaintA;
    private Paint mPaintB;
    private Paint mPaintC;
    private Paint mPaintNumber;
    private Paint mPaintMonth;
    private Paint mPaintLineMaxDistance;
    private Paint mPaintLineMinDistance;
    private Paint mPaintLineBetweenDistance;
    private int[] mPersonA;
    private int[] mPersonB;
    private int[] mPersonC;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews();
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    private void initViews() {
//        mPaintA
        mPaintA = new Paint();
        mPaintA.setColor(Color.RED);
        mPaintA.setStrokeWidth(18);
        mPaintA.setStrokeCap(Paint.Cap.ROUND);
        mPaintA.setAntiAlias(true);
//        mPaintB
        mPaintB = new Paint();
        mPaintB.setColor(Color.BLUE);
        mPaintB.setStrokeWidth(18);
        mPaintB.setStrokeCap(Paint.Cap.ROUND);
        mPaintB.setAntiAlias(true);
//        mPaintC
        mPaintC = new Paint();
        mPaintC.setColor(Color.GREEN);
        mPaintC.setStrokeWidth(18);
        mPaintC.setStrokeCap(Paint.Cap.ROUND);
        mPaintC.setAntiAlias(true);
//        mPaintNumber
        mPaintNumber = new Paint();
        mPaintNumber.setColor(Color.BLACK);
        mPaintNumber.setTextSize(22);
//        mPaintMonth
        mPaintMonth = new Paint();
        mPaintMonth.setColor(Color.BLACK);
        mPaintMonth.setTextSize(22);
//        mPaintLineMaxDistance
        mPaintLineMaxDistance = new Paint();
        mPaintLineMaxDistance.setStrokeWidth(2);
        mPaintLineMaxDistance.setColor(Color.GRAY);
//        mPaintLineMinDistance
        mPaintLineMinDistance = new Paint();
        mPaintLineMinDistance.setStrokeWidth(2);
        mPaintLineMinDistance.setColor(Color.GRAY);
//        mPaintLineBetweenDistance
        mPaintLineBetweenDistance = new Paint();
        mPaintLineBetweenDistance.setStrokeWidth(2);
        mPaintLineBetweenDistance.setColor(Color.GRAY);
    }

    private int setStartX(int startX) {
        return startX * 80 + 100;
    }

    private int setStartY() {
        return this.getHeight() / 2;
    }

    private int setStopX(int stopX) {
        return stopX * 80 + 100;
    }

    private int setStopY(int stopY) {
        return stopY * 30;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor);
        mPersonA = getResources().getIntArray(R.array.person_one);
        mPersonB = getResources().getIntArray(R.array.person_two);
        mPersonC = getResources().getIntArray(R.array.person_three);
        getMaxValueDistance();
        getMinValueDistance();
        Log.d("l", "list: " + mPersonA.length);
        for (int i = 0; i < mPersonA.length; i++) {
//            Draw mPersonA
            Log.d("3", "1: " + mPersonA[i]);
            canvas.drawLine(setStartX(i), setStartY(), setStopX(i), setStartY() - setStopY(mPersonA[i]), mPaintA);
            canvas.drawText(String.valueOf(mPersonA[i]), setStartX(i) - 5, setStartY() + 30, mPaintNumber);
//            Draw mPersonB
            Log.d("3", "2: " + mPersonB[i]);
            canvas.drawLine(setStartX(i) + 20, setStartY(), setStopX(i) + 20, setStartY() - setStopY(mPersonB[i]), mPaintB);
            canvas.drawText(String.valueOf(mPersonB[i]), setStartX(i) + 20 - 5, setStartY() + 30, mPaintNumber);
//            Draw mPersonC
            Log.d("3", "3: " + mPersonC[i]);
            canvas.drawLine(setStartX(i) + 40, setStartY(), setStopX(i) + 40, setStartY() - setStopY(mPersonC[i]), mPaintC);
            canvas.drawText(String.valueOf(mPersonC[i]), setStartX(i) + 40 - 5, setStartY() + 30, mPaintNumber);
//            Draw lineDistance max, min, between
            canvas.drawLine(100, setStartY() - setStopY(getMaxValueDistance()), getWidth(), setStartY() - setStopY(getMaxValueDistance()), mPaintLineMaxDistance);
            canvas.drawLine(100, setStartY(), getWidth(), setStartY(), mPaintLineMinDistance);
            canvas.drawLine(100, setStartY() - setStopY(getMaxValueDistance()) / 2, getWidth(), setStartY() - setStopY(getMaxValueDistance()) / 2, mPaintLineBetweenDistance);
//            Draw mPaintMonth
            if (i == 0) {
                canvas.drawText("24 Jul", setStartX(0) - 5, setStartY() + 70, mPaintMonth);
            }
            if (i == mPersonA.length - 1) {
                canvas.drawText("9 Oct", setStartX(mPersonA.length - 1), setStartY() + 70, mPaintMonth);
            }
        }
        canvas.drawText("0 Km", 10, setStartY(), mPaintMonth);
        canvas.drawText("9 Km", 10, setStartY() - setStopY(getMaxValueDistance()), mPaintMonth);
        canvas.restore();
    }

    //Find maximum (largest) value in array using array sort
    private int getMaxValueDistance() {
        Arrays.sort(mPersonA);
        Arrays.sort(mPersonB);
        Arrays.sort(mPersonC);
        int maxA = mPersonA[mPersonA.length - 1];
        int maxB = mPersonB[mPersonB.length - 1];
        int maxC = mPersonC[mPersonC.length - 1];
        int maxDistance = Math.max(maxA, Math.max(maxB, maxC));
        Log.d("max", "getMaxValueDistance: " + maxDistance);
        return maxDistance;
    }

    //Find maximum (largest) value in array using array sort
    private int getMinValueDistance() {
        Arrays.sort(mPersonA);
        Arrays.sort(mPersonB);
        Arrays.sort(mPersonC);
        int minA = mPersonA[0];
        int minB = mPersonB[0];
        int minC = mPersonC[0];
        int minDistance = Math.min(minA, Math.min(minB, minC));
        Log.d("max", "getMinValueDistance: " + minDistance);
        return minDistance;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Let the ScaleGestureDetector inspect all events.
        mScaleDetector.onTouchEvent(ev);
        return true;
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
