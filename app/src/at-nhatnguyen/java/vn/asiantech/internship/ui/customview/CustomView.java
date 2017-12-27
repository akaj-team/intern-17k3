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
    private static final int BORDER = 13;
    private static final int ONE_KM = 30;
    // Declare field for new ScaleListener
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;
    private List<Integer> resultPeople1;
    private List<Integer> resultPeople2;
    private List<Integer> resultPeople3;
    private Paint mPaintPeople1;
    private Paint mPaintPeople2;
    private Paint mPaintPeople3;
    private Paint mPaintText;
    private Paint mPaintHide;
    private float mMoveX;
    private float mTouchX;
    private float sizeRow;
    private int mMarginTop;
    private int mRange;
    private int mStartPeople1;
    private int mStartPeople2;
    private int mStartPeople3;
    private int mStartLine;
    private int mStopLine;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // New obj of ScaleListener
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        // Get attrs from file xml
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);
        sizeRow = typedArray.getInteger(R.styleable.CustomView_stroke_with, 10);
        initFirstValues();
        initPaint();
        initDatas();
    }

    private void initFirstValues() {
        mRange = (int) sizeRow * 5;
        mMarginTop = 100;
        mStartPeople1 = 170;
        mStartPeople2 = 200;
        mStartPeople3 = 230;
        mStartLine = 150;
        mStopLine = 1200;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mScaleDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() == 1) {
                    mMoveX = event.getX() - mTouchX + mMoveX;
                    mTouchX = event.getX();
                    update(mMoveX);
                }
                break;
            case MotionEvent.ACTION_DOWN:
                mTouchX = event.getX();
                break;
        }
        invalidate();
        return true;
    }

    private void update(float moveX) {
        mStartPeople1 = (int) (170 + moveX);
        mStartPeople2 = (int) (200 + moveX);
        mStartPeople3 = (int) (230 + moveX);
    }

    private void initPaint() {
        mPaintPeople1 = new Paint();
        mPaintPeople1.setColor(Color.BLUE);
        mPaintPeople1.setStrokeWidth(sizeRow);
        mPaintPeople1.setStrokeCap(Paint.Cap.ROUND);
        mPaintPeople1.setAntiAlias(true);
        mPaintPeople2 = new Paint();
        mPaintPeople2.setColor(Color.RED);
        mPaintPeople2.setStrokeWidth(sizeRow);
        mPaintPeople2.setStrokeCap(Paint.Cap.ROUND);
        mPaintPeople2.setAntiAlias(true);
        mPaintPeople3 = new Paint();
        mPaintPeople3.setColor(Color.YELLOW);
        mPaintPeople3.setStrokeWidth(sizeRow);
        mPaintPeople3.setStrokeCap(Paint.Cap.ROUND);
        mPaintPeople3.setAntiAlias(true);
        mPaintText = new Paint();
        mPaintText.setColor(Color.GRAY);
        mPaintText.setStrokeWidth(2F);
        mPaintText.setTextSize(getResources().getDimensionPixelSize(R.dimen.text_size));
        mPaintHide = new Paint();
        mPaintHide.setColor(Color.WHITE);
        mPaintHide.setStrokeWidth(2F);
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
        resultPeople1.add(5);
        resultPeople1.add(5);
        resultPeople1.add(5);
        resultPeople2.add(1);
        resultPeople2.add(3);
        resultPeople2.add(5);
        resultPeople2.add(2);
        resultPeople2.add(2);
        resultPeople2.add(2);
        resultPeople2.add(6);
        resultPeople2.add(6);
        resultPeople2.add(6);
        resultPeople2.add(5);
        resultPeople3.add(1);
        resultPeople3.add(8);
        resultPeople3.add(5);
        resultPeople3.add(5);
        resultPeople3.add(2);
        resultPeople3.add(11);
        resultPeople3.add(6);
        resultPeople3.add(5);
        resultPeople3.add(5);
        resultPeople3.add(11);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        int max = 0;
        canvas.scale(mScaleFactor, mScaleFactor);
        for (int i = 0; i < resultPeople3.size(); i++) {
            if (resultPeople1.get(i) > max) {
                max = resultPeople1.get(i);
            }
            if (resultPeople2.get(i) > max) {
                max = resultPeople2.get(i);
            }
            if (resultPeople3.get(i) > max) {
                max = resultPeople3.get(i);
            }
        }
        drawLineResult(max, canvas);
        drawGraph(max, canvas);
        // This rect start at (0,0) and stop at right = 150
        canvas.drawRect(0, 0, 150, max * ONE_KM + mMarginTop + 100, mPaintHide);
        // The line is start x = 10
        canvas.drawText(max + getContext().getString(R.string.km), 10,
                mMarginTop - BORDER + (mPaintText.getTextSize() / 2), mPaintText);
        canvas.drawText((float) max / 2 + getContext().getString(R.string.km), 10,
                (max * ONE_KM) / 2 + mMarginTop - BORDER + (mPaintText.getTextSize() / 2), mPaintText);
        canvas.restore();
    }

    /**
     * Draw lines of result
     */
    private void drawLineResult(int max, Canvas canvas) {
        canvas.drawLine(mStartLine, mMarginTop - BORDER, mStopLine, mMarginTop - BORDER, mPaintText);
        canvas.drawLine(mStartLine, (max * ONE_KM) / 2 + mMarginTop - BORDER, mStopLine, (max * ONE_KM) / 2 + mMarginTop - BORDER, mPaintText);
        canvas.drawLine(mStartLine, max * ONE_KM + mMarginTop + BORDER, mStopLine, max * ONE_KM + mMarginTop + BORDER, mPaintText);
    }

    /**
     * Draw graph with values of peoples
     */
    private void drawGraph(int max, Canvas canvas) {
        for (int i = 0; i < resultPeople3.size(); i++) {
            canvas.drawLine(i * mRange + mStartPeople1,
                    (max * ONE_KM + mMarginTop) - resultPeople1.get(i) * ONE_KM,
                    i * mRange + mStartPeople1, max * ONE_KM + mMarginTop, mPaintPeople1);
            canvas.drawLine(i * mRange + mStartPeople2,
                    (max * ONE_KM + mMarginTop) - resultPeople2.get(i) * ONE_KM,
                    i * mRange + mStartPeople2, max * ONE_KM + mMarginTop, mPaintPeople2);
            canvas.drawLine(i * mRange + mStartPeople3,
                    (max * ONE_KM + mMarginTop) - resultPeople3.get(i) * ONE_KM,
                    i * mRange + mStartPeople3, max * ONE_KM + mMarginTop, mPaintPeople3);
            canvas.drawText(convertDay(i), i * mRange + mStartPeople2, max * ONE_KM + mMarginTop + 100, mPaintText);
        }
    }

    private String convertDay(int i) {
        int result = i % 7 + 2;
        String day = "";
        switch (result) {
            case 2:
                day = String.valueOf(2);
                break;
            case 3:
                day = String.valueOf(3);
                break;
            case 4:
                day = String.valueOf(4);
                break;
            case 5:
                day = String.valueOf(5);
                break;
            case 6:
                day = String.valueOf(6);
                break;
            case 7:
                day = String.valueOf(7);
                break;
            case 8:
                day = "CN";
                break;
        }
        return day;
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
