package vn.asiantech.internship.ui.canvas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import vn.asiantech.internship.R;

/**
 * Created by anh.quach on 12/29/17.
 * Chart View
 */
public class ChartView extends View {
    private static final int OFFSET100 = 100;

    private boolean mDragged = true;
    private boolean mIsMoveToRight;

    private float mStartX = 0f;
    private float mStartY = 0f;
    private float mTranslateX = 0f;
    private float mTranslateY = 0f;
    private float mPreviousTranslateX = 0f;
    private float mPreviousTranslateY = 0f;
    private float mScaleFactor = 1.f;
    private float mLefts[];
    private float mPrevXMove;
    private float mXDown;
    private float mV;
    private float mOffsetX;
    private float mMaxDistance;

    private int mColumnWidth;
    private int mOxChart = getResources().getDimensionPixelSize(R.dimen.ox_chart);
    private int mColumnCornerRadius = getResources().getDimensionPixelSize(R.dimen.column_corner_radius);
    private int mColumnMarginHorizontal = getResources().getDimensionPixelSize(R.dimen.columns_margin_horizontal);
    private int mMode;
    private long mTimeDown;

    private Paint mPaintText;
    private Paint mPaintLine;
    private Paint mPaintColumnCyanA700;
    private Paint mPaintColumnPurple800;
    private Paint mPaintColumnOrange500;
    private Paint mPaintRect;

    private ScaleGestureDetector mScaleDetector;

    private List<Integer> mDistancePurple800s;
    private List<Integer> mDistanceCyanA700s;
    private List<Integer> mDistanceOrange500s;
    private List<Float> mPaths;
    private List<Long> mTimes;

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
            mColumnWidth = a.getDimensionPixelSize(R.styleable.ChartView_column_width, getResources().getDimensionPixelSize(R.dimen.defValueChartView));
        } finally {
            a.recycle();
        }
        initData();
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        maxLists();
        initPaint();
    }

    private void maxLists() {
        float max1 = Collections.max(mDistancePurple800s);
        float max2 = Collections.max(mDistanceCyanA700s);
        float max3 = Collections.max(mDistanceOrange500s);
        mMaxDistance = Math.max(Math.max(max1, max2), max3);
    }

    private void initData() {
        mDistancePurple800s = new ArrayList<>(Arrays.asList(2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9));
        mDistanceCyanA700s = new ArrayList<>(Arrays.asList(1, 2, 5, 2, 7, 8, 9, 7, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9));
        mDistanceOrange500s = new ArrayList<>(Arrays.asList(3, 9, 5, 6, 11, 8, 9, 2, 4, 4, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9));
        mPaths = new ArrayList<>();
        mTimes = new ArrayList<>();
        mLefts = new float[mDistancePurple800s.size()];

    }

    private void initPaint() {
        mPaintText = new Paint();
        mPaintLine = new Paint();
        mPaintRect = new Paint();
        mPaintColumnCyanA700 = new Paint();
        mPaintColumnPurple800 = new Paint();
        mPaintColumnOrange500 = new Paint();

        mPaintText.setColor(getResources().getColor(R.color.colorGrayDark));
        mPaintText.setTextSize(getResources().getDimension(R.dimen.textsize13sp));
        mPaintText.setAntiAlias(true);

        mPaintRect.setColor(Color.WHITE);

        mPaintLine.setColor(getResources().getColor(R.color.colorGrayDark));
        mPaintLine.setAntiAlias(true);

        mPaintColumnPurple800.setColor(getResources().getColor(R.color.colorPurple800));
        mPaintColumnPurple800.setAntiAlias(true);

        mPaintColumnCyanA700.setColor(getResources().getColor(R.color.colorCyanA700));
        mPaintColumnCyanA700.setAntiAlias(true);

        mPaintColumnOrange500.setColor(getResources().getColor(R.color.colorOrange500));
        mPaintColumnOrange500.setAntiAlias(true);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int DRAG = 1;
        int ZOOM = 2;
        switch (ev.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mMode = DRAG;

                mStartX = ev.getX();
                mStartY = ev.getY();
                mStartX = ev.getX() - mPreviousTranslateX;
                mStartY = ev.getY() - mPreviousTranslateY;

                mPaths.clear();
                mTimes.clear();
                mPrevXMove = ev.getX();
                mXDown = mPrevXMove;
                mTimeDown = System.currentTimeMillis();

                mPaths.add(mPrevXMove);
                mTimes.add(mTimeDown);
                break;
            case MotionEvent.ACTION_MOVE:
                float currX = ev.getX();
                mTranslateX = ev.getX() - mStartX;
                mTranslateY = ev.getY() - mStartY;
                mPaths.add(currX);
                mTimes.add(System.currentTimeMillis());

                if (mPrevXMove == currX) {
                    mXDown = currX;
                    mTimeDown = System.currentTimeMillis();
                    mPaths.clear();
                    mTimes.clear();
                }
                mIsMoveToRight = mPrevXMove <= currX;
                if (ev.getPointerCount() == 1) {
                    //Check if the last column at right move to limit
                    if (!mIsMoveToRight && mOffsetX == 0) {
                        return true;
                    }
                    //Check if the last column at left move to limit
                    if (mIsMoveToRight && mOffsetX <= mLefts[mDistancePurple800s.size() - 1] - getPaddingLeft() - OFFSET100) {
                        return true;
                    }
                    //Update offsetX
                    mOffsetX += mPrevXMove - currX;

                    //If move to left and and the last column exceed the limit, must block it
                    if (!mIsMoveToRight && mOffsetX > 0) {
                        mOffsetX = 0;
                    }
                    //If move to right and and the first column exceed the limit, must block it
                    if (mIsMoveToRight && mOffsetX <= mLefts[mDistancePurple800s.size() - 1] - getPaddingLeft() - OFFSET100) {
                        mOffsetX = mLefts[mDistancePurple800s.size() - 1] - getPaddingLeft() - OFFSET100;
                    }
                }
                //Update latest x coordinate
                mPrevXMove = currX;
                invalidate();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                mMode = ZOOM;
                break;
            case MotionEvent.ACTION_UP:
                mMode = 0;
                mDragged = false;
                mPreviousTranslateX = mTranslateX;
                mPreviousTranslateY = mTranslateY;
                //Find the latest corner, 1 2 3 4 5 6 5 4 3 2 1, corner is 6
                int size = mPaths.size();
                for (int i = size - 1; i > 0; i--) {
                    if ((mIsMoveToRight && mPaths.get(i - 1) > mPaths.get(i)) || (!mIsMoveToRight && mPaths.get(i - 1) < mPaths.get(i))) {
                        mXDown = mPaths.get(i);
                        mTimeDown = mTimes.get(i);
                        break;
                    }
                    if (i - 1 == 0) {
                        mXDown = mPaths.get(0);
                        mTimeDown = mTimes.get(0);
                    }
                }
                //Check if the last column at left move to limit
                if (mIsMoveToRight && mOffsetX <= mLefts[mDistancePurple800s.size() - 1] - getPaddingLeft() - OFFSET100) {
                    return true;
                }
                //Check if the last column at right move to limit
                if (!mIsMoveToRight && mOffsetX == 0) {
                    return true;
                }

                //Calculate the move speed to handle scroll after fling
                long time = System.currentTimeMillis() - mTimeDown;
                final float s = ev.getX() - mXDown;
                if (time > 0) {
                    mV = Math.abs(s / time);
                    mV = mV * 50 / 3F;
                    if (mV > 0) {
                        final android.os.Handler handler = new android.os.Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Check if the first column at left move to limit
                                if (mIsMoveToRight) {
                                    mOffsetX -= mV;
                                    if (mOffsetX < mLefts[mDistancePurple800s.size() - 1] - getPaddingLeft() - OFFSET100) {
                                        mOffsetX = mLefts[mDistancePurple800s.size() - 1] - getPaddingLeft() - OFFSET100;
                                        mV = 0;
                                    }
                                } else {//Check if the last column at right move to limit
                                    mOffsetX += mV;
                                    if (mOffsetX > 0) {
                                        mOffsetX = 0;
                                        mV = 0;
                                    }
                                }
                                invalidate();
                                //Scroll, scroll, scroll...
                                if (mV-- > 0) {
                                    handler.postDelayed(this, 1);
                                }
                            }
                        }, 1);
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mMode = DRAG;
                mPreviousTranslateX = mTranslateX;
                mPreviousTranslateY = mTranslateY;
                break;
        }
        mScaleDetector.onTouchEvent(ev);
        if ((mMode == DRAG && mScaleFactor != 1f && mDragged) || mMode == ZOOM) {
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

        drawChart(canvas);

        drawRect(canvas, 0, mPaintRect);
        drawRect(canvas, getWidth() - 50, mPaintRect);

        drawText(canvas, mMaxDistance + getContext().getString(R.string.distance_km), 0, mMaxDistance, mPaintText);
        drawText(canvas, mMaxDistance / 2 + getContext().getString(R.string.distance_km), 0, mMaxDistance / 2, mPaintText);
        drawText(canvas, getContext().getString(R.string.day), mOxChart, -1, mPaintText);
        drawText(canvas, getContext().getString(R.string.month), getWidth() - OFFSET100, -1, mPaintText);

        drawLine(canvas, 0, mPaintLine);
        drawLine(canvas, mMaxDistance, mPaintLine);

        drawText(canvas, getContext().getString(R.string.distance_km, ((int) 0)), 0, 0, mPaintText);

        drawLine(canvas, mMaxDistance / 2, mPaintLine);
        canvas.restore();
    }

    private void drawChart(Canvas canvas) {
        float leftRect = getWidth() - getPaddingRight() - mColumnMarginHorizontal - mColumnWidth - OFFSET100 - mOffsetX;
        for (int index = 0; index < mDistancePurple800s.size(); index++) {
            canvas.drawRoundRect(new RectF(leftRect - OFFSET100 * index, getTop(mDistancePurple800s.get(index)), leftRect - OFFSET100 * index + mColumnWidth,
                    getHeight() / 2 - getPaddingBottom()), mColumnCornerRadius, mColumnCornerRadius, mPaintColumnPurple800);
            canvas.drawRoundRect(new RectF(leftRect - OFFSET100 * index - mColumnWidth - 5, getTop(mDistanceCyanA700s.get(index)), leftRect - OFFSET100 * index - 5,
                    getHeight() / 2 - getPaddingBottom()), mColumnCornerRadius, mColumnCornerRadius, mPaintColumnCyanA700);
            canvas.drawRoundRect(new RectF(leftRect - OFFSET100 * index - mColumnWidth * 2 - 10, getTop(mDistanceOrange500s.get(index)), leftRect - OFFSET100 * index - mColumnWidth - 10,
                    getHeight() / 2 - getPaddingBottom()), mColumnCornerRadius, mColumnCornerRadius, mPaintColumnOrange500);
            //Save the first left of all columns
            if (mOffsetX == 0) {
                mLefts[index] = leftRect;
            }
            leftRect -= mColumnWidth + mColumnMarginHorizontal;
        }
    }

    private float getTop(int value) {
        return getHeight() / 2 - value * 50;
    }

    private void translate(Canvas canvas) {
        if ((mTranslateX * -1) < 0) {
            mTranslateX = 0;
        } else if ((mTranslateX * -1) > (mScaleFactor - 1) * getWidth()) {
            mTranslateX = (1 - mScaleFactor) * getWidth();
        }
        if (mTranslateY * -1 < 0) {
            mTranslateY = 0;
        } else if ((mTranslateY * -1) > (mScaleFactor - 1) * getHeight()) {
            mTranslateY = (1 - mScaleFactor) * getHeight();
        }
        canvas.translate(mTranslateX / mScaleFactor, mTranslateY / mScaleFactor);
    }

    private void drawText(Canvas canvas, String str, float x, float y, Paint paint) {
        canvas.drawText(str, x, getHeight() / 2 - y * 50, paint);
    }

    private void drawLine(Canvas canvas, float y, Paint paint) {
        canvas.drawLine(mOxChart, getHeight() / 2 - y * 50, getWidth() - 50, getHeight() / 2 - y * 50, paint);
    }

    private void drawRect(Canvas canvas, float left, Paint paint) {
        canvas.drawRect(left, getHeight() / 2 - mMaxDistance * 50, left + 130, getHeight() / 2, paint);
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
