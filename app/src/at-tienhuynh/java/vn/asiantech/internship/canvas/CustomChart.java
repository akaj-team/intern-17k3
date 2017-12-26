package vn.asiantech.internship.canvas;

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

import java.util.Collections;

import vn.asiantech.internship.R;
import vn.asiantech.internship.canvas.utils.ChartValues;

/**
 * Created at 2017
 * Created by jackty on 25/12/2017.
 */
public class CustomChart extends View {

    private static final int NUM_DAY = 7;
    private static final int SCALE_Y = 50;
    private static final int SCALE_X = 3;
    private static final int TOTAL_PEOPLE = 3;
    private static final int NUM_O = 0;
    private float mStokeWidth = 40F;
    private int mLengthOneColumn = 40;
    private Paint mPaintPeople1;
    private Paint mPaintPeople2;
    private Paint mPaintPeople3;
    private Paint mPaintMaxLine;
    private Paint mPaintText;
    private float mMax = 0;
    private float scaleFactor = 1f;
    private ScaleGestureDetector detector;
    // move
    private float mPointOX = 0;
    private float mPointDownX = 0;

    public CustomChart(Context context) {
        this(context, null);
    }

    public CustomChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // Get attrs from XML file
        attributeSet(context, attrs);
        // Get Max Values
        getMaxValues();
        // Init Paint
        init();
        //scale
        detector = new ScaleGestureDetector(getContext(), new ScaleListener());
    }

    /**
     * Get attrs from XML file
     * NOTICE mStokeWidth should be the same as mLengthOneColumn
     *
     * @param attrs
     */
    private void attributeSet(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomChart, 0, 0);
        try {
            mStokeWidth = typedArray.getFloat(R.styleable.CustomChart_stoke_column, 40);
            mLengthOneColumn = typedArray.getInteger(R.styleable.CustomChart_column_width, 40);
        } finally {
            typedArray.recycle();
        }
    }

    /**
     * Get Max Values
     */
    private void getMaxValues() {
        int maxPeople1 = Collections.max(ChartValues.people1Values());
        int maxPeople2 = Collections.max(ChartValues.people2Values());
        int maxPeople3 = Collections.max(ChartValues.people3Values());
        mMax = Math.max(Math.max(maxPeople1, maxPeople2), maxPeople3);
    }

    /**
     * Init Paint
     */
    private void init() {
        // mPaintPeople1
        mPaintPeople1 = new Paint();
        mPaintPeople1.setColor(getResources().getColor(R.color.colorPurple800));
        mPaintPeople1.setStrokeWidth(mStokeWidth);
        mPaintPeople1.setStrokeCap(Paint.Cap.BUTT);
        mPaintPeople1.setAntiAlias(true);
        // mPaintPeople2
        mPaintPeople2 = new Paint();
        mPaintPeople2.setColor(getResources().getColor(R.color.colorCyanA700));
        mPaintPeople2.setStrokeWidth(mStokeWidth);
        mPaintPeople2.setStrokeCap(Paint.Cap.BUTT);
        mPaintPeople2.setAntiAlias(true);
        // mPaintPeople3
        mPaintPeople3 = new Paint();
        mPaintPeople3.setColor(getResources().getColor(R.color.colorOrange500));
        mPaintPeople3.setStrokeWidth(mStokeWidth);
        mPaintPeople3.setStrokeCap(Paint.Cap.BUTT);
        mPaintPeople3.setAntiAlias(true);
        // mPaintMaxLine
        mPaintMaxLine = new Paint();
        mPaintMaxLine.setColor(Color.GRAY);
        mPaintMaxLine.setStrokeWidth(3f);
        mPaintMaxLine.setAntiAlias(true);
        // mPainText
        mPaintText = new Paint();
        mPaintText.setColor(Color.GRAY);
        mPaintText.setTextSize(getResources().getDimension(R.dimen.tv_value_chart));
        // Scale
    }

    /**
     * OnTouch Event
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() == 1) {
                    mPointOX = (event.getX() - mPointDownX) + mPointOX;
                    mPointDownX = event.getX();
                }
                break;
            case MotionEvent.ACTION_DOWN:
                mPointDownX = event.getX();
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // scale
        canvas.save();
        canvas.scale(scaleFactor, scaleFactor);
        //Draw Line
        drawLine(canvas);
        // Draw chart
        drawChart(canvas);
        //Draw text
        drawText(canvas);
        // scale
        canvas.restore();
        // update
        invalidate();
    }

    /**
     * Draw Text
     */
    private void drawText(Canvas canvas) {
        //Draw Text Max Values
        canvas.drawText(String.valueOf(mMax).concat(getResources().getString(R.string.tv_canvas_km)), NUM_O, getHeight() / 2 - mMax * SCALE_Y, mPaintText);
        //Draw Text Middle Values
        canvas.drawText(String.valueOf(mMax / 2).concat(getResources().getString(R.string.tv_canvas_km)), NUM_O, getHeight() / 2 - (mMax / 2) * SCALE_Y, mPaintText);
    }

    /**
     * Draw Line
     */
    private void drawLine(Canvas canvas) {
        //Draw Max Line
        canvas.drawLine(mPaintText.measureText(String.valueOf(mMax).concat(getResources().getString(R.string.tv_canvas_km))) + SCALE_X * 5, getHeight() / 2 - mMax * SCALE_Y, getWidth(), getHeight() / 2 - mMax * SCALE_Y, mPaintMaxLine);
        //Draw End Line
        canvas.drawLine(NUM_O, getHeight() / 2, getWidth(), getHeight() / 2, mPaintMaxLine);
        //Draw Middle Line
        canvas.drawLine(mPaintText.measureText(String.valueOf(mMax).concat(getResources().getString(R.string.tv_canvas_km))), getHeight() / 2 - mMax / 2 * SCALE_Y, getWidth(), getHeight() / 2 - mMax / 2 * SCALE_Y, mPaintMaxLine);
    }

    /**
     * Draw Chart
     */
    private void drawChart(Canvas canvas) {
        for (int i = NUM_O; i < NUM_DAY; i++) {
            int y1 = ChartValues.people1Values().get(i) * SCALE_Y;
            int y2 = ChartValues.people2Values().get(i) * SCALE_Y;
            int y3 = ChartValues.people3Values().get(i) * SCALE_Y;
            canvas.drawLine(mPointOX + getWidth() / 2 + i * (mLengthOneColumn * (TOTAL_PEOPLE + 1)) - ((NUM_DAY * TOTAL_PEOPLE + (NUM_DAY - 1)) * mLengthOneColumn) / 2, getHeight() / 2 - y1, mPointOX + getWidth() / 2 + i * (mLengthOneColumn * (TOTAL_PEOPLE + 1)) - ((NUM_DAY * TOTAL_PEOPLE + (NUM_DAY - 1)) * mLengthOneColumn) / 2, getHeight() / 2, mPaintPeople1);
            canvas.drawLine(mPointOX + getWidth() / 2 + i * (mLengthOneColumn * (TOTAL_PEOPLE + 1)) + mLengthOneColumn + SCALE_X - ((NUM_DAY * TOTAL_PEOPLE + (NUM_DAY - 1)) * mLengthOneColumn) / 2, getHeight() / 2 - y2, mPointOX + getWidth() / 2 + i * (mLengthOneColumn * (TOTAL_PEOPLE + 1)) + mLengthOneColumn + SCALE_X - ((NUM_DAY * TOTAL_PEOPLE + (NUM_DAY - 1)) * mLengthOneColumn) / 2, getHeight() / 2, mPaintPeople2);
            canvas.drawLine(mPointOX + getWidth() / 2 + i * (mLengthOneColumn * (TOTAL_PEOPLE + 1)) + mLengthOneColumn * 2 + SCALE_X * 2 - ((NUM_DAY * TOTAL_PEOPLE + (NUM_DAY - 1)) * mLengthOneColumn) / 2, getHeight() / 2 - y3, mPointOX + getWidth() / 2 + i * (mLengthOneColumn * (TOTAL_PEOPLE + 1)) + mLengthOneColumn * 2 + SCALE_X * 2 - ((NUM_DAY * TOTAL_PEOPLE + (NUM_DAY - 1)) * mLengthOneColumn) / 2, getHeight() / 2, mPaintPeople3);
            //text Days
            canvas.drawText("" + (i + 2), mPointOX + getWidth() / 2 + i * (mLengthOneColumn * (TOTAL_PEOPLE + 1)) + mLengthOneColumn + SCALE_X - ((NUM_DAY * TOTAL_PEOPLE + (NUM_DAY - 1)) * mLengthOneColumn) / 2, getHeight() / 2 + SCALE_Y, mPaintText);
        }
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();
            float minZoom = 1f;
            float maxZoom = 3f;
            scaleFactor = Math.max(minZoom, Math.min(scaleFactor, maxZoom));
            invalidate();
            return true;
        }
    }
}
