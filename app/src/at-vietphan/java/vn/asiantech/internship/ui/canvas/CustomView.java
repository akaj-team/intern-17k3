package vn.asiantech.internship.ui.canvas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import vn.asiantech.internship.R;

public class CustomView extends View {
    private static final int START_X_COLUMN_A = 0;
    private static final int START_X_COLUMN_B = 24;
    private static final int START_X_COLUMN_C = START_X_COLUMN_B * 2;
    private static final int WIDTH_BOX = 120;
    private Paint mPaintText = new Paint();
    private Paint mPaintRect = new Paint();
    private Paint mPaintLine = new Paint();
    private Paint mPaintColumnRed = new Paint();
    private Paint mPaintColumnPrimary = new Paint();
    private Paint mPaintColumnYellow = new Paint();
    private int mColumnWidth;
    private int mColumnCornerRadius;
    private int mColumnMarginHorizontal;
    private List<Integer> mDataRed;
    private List<Integer> mDataPrimary;
    private List<Integer> mDataYellow;
    private int mSizeData;
    private int mMax;
    //To save the first left of all columns
    private float mLefts[];
    //Prev x coordinate in action move
    private float mPrevXMove;
    //x coordinate
    //If move from left to right or right to left, it's the x coordinate in action down
    //If move from left to right, to left..., it's the x coordinate in the latest corner
    private float mXDown;
    //Same with #mXDown but it's time
    private long mTimeDown;
    //Move speed after move and fling
    private float mV;
    //To handle move column function
    private float mOffsetX;
    //Check if swipe to left or right
    private boolean mIsMoveToRight;
    //Save x coordinate of columns
    private List<Float> mPaths;
    //Same with #path but it's mTimes
    private List<Long> mTimes;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        // Get attrs from XML file
        attributeSet(context, attrs);
        init();
        initData();
    }

    /**
     * Get attrs from XML file
     * NOTICE mStokeWidth should be the same as mLengthOneColumn
     *
     * @param attrs attrs
     */
    private void attributeSet(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomView, 0, 0);
        try {
            mColumnWidth = typedArray.getDimensionPixelSize(R.styleable.CustomView_rect_width, getResources().getDimensionPixelSize(R.dimen.custom_view_default_column_width));
        } finally {
            typedArray.recycle();
        }
    }

    private void init() {
        mPaintText.setColor(Color.GRAY);
        mPaintText.setTextSize(getResources().getDimension(R.dimen.tv_value_chart));
        //Draw line distance
        mPaintLine.setColor(Color.GRAY);
        mPaintLine.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.line_stroke_width));
        mPaintLine.setAntiAlias(true);
        // Draw column person A, B C
        mPaintColumnRed.setColor(Color.RED);
        mPaintColumnRed.setAntiAlias(true);
        mPaintColumnPrimary.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        mPaintColumnPrimary.setAntiAlias(true);
        mPaintColumnYellow.setColor(Color.YELLOW);
        mPaintColumnYellow.setAntiAlias(true);
        // Draw box left, right
        mPaintRect.setColor(Color.WHITE);
        mColumnWidth = getResources().getDimensionPixelSize(R.dimen.column_width);
        mColumnCornerRadius = getResources().getDimensionPixelSize(R.dimen.column_corner_radius);
        mColumnMarginHorizontal = getResources().getDimensionPixelSize(R.dimen.columns_margin_horizontal);
        mPaths = new ArrayList<>();
        mTimes = new ArrayList<>();
    }

    private void initData() {
        mDataRed = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        mDataPrimary = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7, 8, 9, 11, 12, 3, 4, 5, 6, 7, 8, 9, 11, 12,
                3, 4, 5, 6, 7, 8, 9, 11, 12, 3, 4, 5, 6, 7, 8, 9, 11, 12,
                3, 4, 5, 6, 7, 8, 9, 11, 12, 3, 4, 5, 6, 7, 8, 9, 11, 12,
                3, 4, 5, 6, 7, 8, 9, 11, 12, 3, 4, 5, 6, 7, 8, 9, 11, 12,
                3, 4, 5, 6, 7, 8, 9, 11, 12, 3, 4, 5, 6, 7, 8, 9, 11, 12));
        mDataYellow = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                2, 3, 4, 5, 6, 7, 8, 9, 10, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                2, 3, 4, 5, 6, 7, 8, 9, 10, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                2, 3, 4, 5, 6, 7, 8, 9, 10, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                2, 3, 4, 5, 6, 7, 8, 9, 10, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        mSizeData = mDataRed.size();
        mMax = Math.max(Collections.max(mDataRed), Math.max(Collections.max(mDataPrimary), Collections.max(mDataYellow)));
        mLefts = new float[mSizeData];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Scale canvas
        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor);
        // Draw Line
        drawLine(canvas);
        // DrawChart
        drawChart(canvas);
        // DrawRect
        drawRect(canvas);
        // DrawText
        drawText(canvas);
        // Restore canvas
        canvas.restore();
    }

    /**
     * Draw Line
     *
     * @param canvas canvas
     */
    private void drawLine(Canvas canvas) {
        //Draw Max Line
        canvas.drawLine(mPaintText.measureText(String.valueOf(mMax).concat(getResources().getString(R.string.tv_canvas_km))),
                getTop(mMax), getWidth(), getTop(mMax), mPaintLine);
        //Draw End Line
        canvas.drawLine(0, getHeight() - getPaddingBottom(), getWidth(), getHeight() - getPaddingBottom(), mPaintLine);
        //Draw Middle Line
        canvas.drawLine(0, getTop((float) mMax / 2), getWidth(), getTop((float) mMax / 2), mPaintLine);
    }

    /**
     * Draw Chart
     *
     * @param canvas canvas
     */
    private void drawChart(Canvas canvas) {
        float leftRect = getWidth() - getPaddingRight() - mColumnMarginHorizontal - mColumnWidth - WIDTH_BOX - mOffsetX;
        for (int i = 0; i < mSizeData; i++) {
            RectF rectFA = new RectF(getLeft(leftRect, i, START_X_COLUMN_A), getTop(mDataRed.get(i)), getLeft(leftRect, i, START_X_COLUMN_A) + mColumnWidth, getHeight() - getPaddingBottom());
            RectF rectFB = new RectF(getLeft(leftRect, i, START_X_COLUMN_B), getTop(mDataPrimary.get(i)), getLeft(leftRect, i, START_X_COLUMN_B) + mColumnWidth, getHeight() - getPaddingBottom());
            RectF rectFC = new RectF(getLeft(leftRect, i, START_X_COLUMN_C), getTop(mDataYellow.get(i)), getLeft(leftRect, i, START_X_COLUMN_C) + mColumnWidth, getHeight() - getPaddingBottom());
            canvas.drawRoundRect(rectFA, mColumnCornerRadius, mColumnCornerRadius, mPaintColumnRed);
            canvas.drawRoundRect(rectFB, mColumnCornerRadius, mColumnCornerRadius, mPaintColumnPrimary);
            canvas.drawRoundRect(rectFC, mColumnCornerRadius, mColumnCornerRadius, mPaintColumnYellow);
            //Save the first left of all columns
            if (mOffsetX == 0) {
                mLefts[i] = leftRect;
            }
            leftRect -= mColumnWidth + mColumnMarginHorizontal;
        }
    }

    /**
     * Draw Rect
     *
     * @param canvas canvas
     */
    private void drawRect(Canvas canvas) {
        canvas.drawRect(0, 0, getPaddingLeft() + WIDTH_BOX - mColumnMarginHorizontal, getHeight(), mPaintRect);
        canvas.drawRect(getWidth() - getPaddingRight() - WIDTH_BOX + mColumnMarginHorizontal, 0, getWidth() * (1 + mScaleFactor * 2), getHeight(), mPaintRect);
    }

    /**
     * Draw Text
     *
     * @param canvas canvas
     */
    private void drawText(Canvas canvas) {
        //Draw Text Max Values
        canvas.drawText(String.valueOf(mMax).concat(getResources().getString(R.string.tv_canvas_km)), 0, 50, mPaintText);
        //Draw Text Between Values
        canvas.drawText(String.valueOf((float) mMax / 2).concat(getResources().getString(R.string.tv_canvas_km)), 0, getHeight() / 2 + 8, mPaintText);
    }

    /**
     * @param value value
     * @return top
     */
    private float getTop(float value) {
        float realHeight = (getHeight() - getPaddingBottom() - getPaddingTop()) * (value * 1F / mMax * 1F);
        return getHeight() - getPaddingBottom() - realHeight;
    }

    /**
     * @param leftRect leftRect
     * @param i        i
     * @param x        x
     * @return top
     */
    private float getLeft(float leftRect, int i, int x) {
        return leftRect - 80 * i - x;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mScaleDetector.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //Reset mDataRed
            mPaths.clear();
            mTimes.clear();
            mPrevXMove = event.getX();
            mXDown = mPrevXMove;
            mTimeDown = System.currentTimeMillis();

            //Save the mDataRed of the first touch
            mPaths.add(mPrevXMove);
            mTimes.add(mTimeDown);
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float currentXMove = event.getX();
            mPaths.add(currentXMove);
            mTimes.add(System.currentTimeMillis());

            //When long touch, reset mDataRed
            if (mPrevXMove == currentXMove) {
                mXDown = currentXMove;
                mTimeDown = System.currentTimeMillis();
                mPaths.clear();
                mTimes.clear();
            }
            mIsMoveToRight = mPrevXMove <= currentXMove;
            if (event.getPointerCount() == 1) {
                //Check if the last column at right move to limit
                if (!mIsMoveToRight && mOffsetX == 0) {
                    return true;
                }
                //Check if the last column at left move to limit
                if (mIsMoveToRight && mOffsetX <= mLefts[mSizeData - 1] - getPaddingLeft() - WIDTH_BOX) {
                    return true;
                }
                //Update mOffsetX
                mOffsetX += mPrevXMove - currentXMove;

                //If move to left and and the last column exceed the limit, must block it
                if (!mIsMoveToRight && mOffsetX > 0) {
                    mOffsetX = 0;
                }
                //If move to right and and the first column exceed the limit, must block it
                if (mIsMoveToRight && mOffsetX <= mLefts[mSizeData - 1] - getPaddingLeft() - WIDTH_BOX) {
                    mOffsetX = mLefts[mSizeData - 1] - getPaddingLeft() - WIDTH_BOX;
                }
            }
            //Update latest x coordinate
            mPrevXMove = currentXMove;
            //Re-draw
            invalidate();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
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
            if (mIsMoveToRight && mOffsetX <= mLefts[mSizeData - 1] - getPaddingLeft() - WIDTH_BOX) {
                return true;
            }
            //Check if the last column at right move to limit
            if (!mIsMoveToRight && mOffsetX == 0) {
                return true;
            }

            //Calculate the move speed to handle scroll after fling
            long time = System.currentTimeMillis() - mTimeDown;
            final float s = event.getX() - mXDown;
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
                                if (mOffsetX < mLefts[mSizeData - 1] - getPaddingLeft() - WIDTH_BOX) {
                                    mOffsetX = mLefts[mSizeData - 1] - getPaddingLeft() - WIDTH_BOX;
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
        }
        return true;
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
