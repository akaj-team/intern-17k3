package vn.asiantech.internship.ui.customview;

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
 * Created by hoangnhat on 25/12/2017.
 * The CustomView for CanvasActivity
 */
public class CustomView extends View {
    private static final int MARGIN = 100;
    private static final int DISTANCE_COLUMN = 42;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;
    private Paint mPaintText;
    private Paint mPaintRect;
    private Paint mPaintLine;
    private Paint mPaintColumnRed;
    private Paint mPaintColumnYellow;
    private Paint mPaintColumnBlue;
    private int mColumnWidth;
    private int mColumnCornerRadius;
    private int mColumnMarginHorizontal;
    private List<Integer> mDataReds;
    private List<Integer> mDataYellows;
    private List<Integer> mDataBlues;
    private int mMax;
    private int mSizeData;
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
    private float mSpeed;
    //To handle move column function
    private float mOffsetX;
    //Check if swipe to left or right
    private boolean mIsMoveToRight;
    //Save x coordinate of columns
    private List<Float> mPaths;
    //Same with #path but it's mTimes
    private List<Long> mTimes;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        // Get attrs from XML file
        attributeSet(context, attrs);
        // init Paint
        init();
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
            mColumnWidth = typedArray.getDimensionPixelSize(R.styleable.CustomView_stroke_with, getResources().getDimensionPixelSize(R.dimen.custom_view_default_column_width));
        } finally {
            typedArray.recycle();
        }
    }

    /**
     * Init Paint
     */
    private void init() {
        mPaintText = new Paint();
        mPaintText.setColor(Color.GRAY);
        mPaintText.setTextSize(getResources().getDimension(R.dimen.tv_value_chart));

        mPaintLine = new Paint();
        mPaintLine.setColor(Color.GRAY);
        mPaintLine.setStrokeWidth(getResources().getDimension(R.dimen.stroke_width));
        mPaintLine.setAntiAlias(true);

        mPaintColumnRed = new Paint();
        mPaintColumnRed.setColor(Color.RED);
        mPaintColumnRed.setAntiAlias(true);

        mPaintColumnYellow = new Paint();
        mPaintColumnYellow.setColor(Color.YELLOW);
        mPaintColumnYellow.setAntiAlias(true);

        mPaintColumnBlue = new Paint();
        mPaintColumnBlue.setColor(Color.BLUE);
        mPaintColumnBlue.setAntiAlias(true);

        mPaintRect = new Paint();
        mPaintRect.setColor(Color.WHITE);

        mDataReds = new ArrayList<>(Arrays.asList(10, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9));
        mDataYellows = new ArrayList<>(Arrays.asList(11, 6, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9));
        mDataBlues = new ArrayList<>(Arrays.asList(3, 5, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9));

        mSizeData = mDataReds.size();

        mMax = Math.max(Collections.max(mDataReds), Math.max(Collections.max(mDataYellows), Collections.max(mDataBlues)));

        mLefts = new float[mSizeData];
        mPaths = new ArrayList<>();
        mTimes = new ArrayList<>();

        mColumnWidth = getResources().getDimensionPixelSize(R.dimen.column_width);
        mColumnCornerRadius = getResources().getDimensionPixelSize(R.dimen.column_corner_radius);
        mColumnMarginHorizontal = getResources().getDimensionPixelSize(R.dimen.columns_margin_horizontal);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor);
        //Draw Line
        drawLine(canvas);
        // drawChart
        drawChart(canvas);
        // drawRect
        drawRect(canvas);
        //drawText
        drawText(canvas);
    }

    /**
     * Draw Line
     *
     * @param canvas canvas
     */
    private void drawLine(Canvas canvas) {
        //Draw Max Line
        canvas.drawLine(
                mPaintText.measureText(String.valueOf(mMax).concat(getResources().getString(R.string.km))),
                getTop(mMax),
                getWidth(),
                getTop(mMax),
                mPaintLine);
        //Draw End Line
        canvas.drawLine(
                0,
                getHeight() - getPaddingBottom(),
                getWidth(),
                getHeight() - getPaddingBottom(),
                mPaintLine);
        //Draw Middle Line
        canvas.drawLine(
                0,
                getTop((float)mMax / 2),
                getWidth(),
                getTop((float)mMax / 2),
                mPaintLine);
    }

    /**
     * Draw Chart
     *
     * @param canvas canvas
     */
    private void drawChart(Canvas canvas) {
        float leftRect = getWidth() - getPaddingRight() - mColumnMarginHorizontal - mColumnWidth - MARGIN - mOffsetX;
        for (int index = 0; index < mSizeData; index++) {
            canvas.drawRoundRect(new RectF(leftRect - MARGIN * index, getTop(mDataReds.get(index)), leftRect - 100 * index + mColumnWidth, getHeight() - getPaddingBottom()), mColumnCornerRadius, mColumnCornerRadius, mPaintColumnRed);
            canvas.drawRoundRect(new RectF(leftRect - MARGIN * index - DISTANCE_COLUMN, getTop(mDataYellows.get(index)), leftRect - 100 * index - 42 + mColumnWidth, getHeight() - getPaddingBottom()), mColumnCornerRadius, mColumnCornerRadius, mPaintColumnYellow);
            canvas.drawRoundRect(new RectF(leftRect - MARGIN * index - DISTANCE_COLUMN * 2, getTop(mDataBlues.get(index)), leftRect - 100 * index - 84 + mColumnWidth, getHeight() - getPaddingBottom()), mColumnCornerRadius, mColumnCornerRadius, mPaintColumnBlue);
            canvas.drawText(
                    convertDay(mSizeData - index),
                    leftRect - MARGIN * index - DISTANCE_COLUMN * 2 + mColumnWidth,
                    getHeight() - getPaddingBottom() + 50,
                    mPaintText);
            //Save the first left of all columns
            if (mOffsetX == 0) {
                mLefts[index] = leftRect;
            }
            leftRect -= mColumnWidth + mColumnMarginHorizontal;
        }
    }

    private String convertDay(int i) {
        int result = i % 7 + 2;
        String day = "CN";
        if (result != 8) {
            day = String.valueOf(result);
        }
        return day;
    }

    /**
     * Draw Rect
     *
     * @param canvas canvas
     */
    private void drawRect(Canvas canvas) {
        canvas.drawRect(0, 0, getPaddingLeft() + MARGIN - mColumnMarginHorizontal, getHeight(), mPaintRect);
        canvas.drawRect(getWidth() - getPaddingRight() - MARGIN + mColumnMarginHorizontal, 0, getWidth(), getHeight(), mPaintRect);
    }

    /**
     * Draw Text
     *
     * @param canvas canvas
     */
    private void drawText(Canvas canvas) {
        //Draw Text Max Values
        canvas.drawText(String.valueOf(mMax).concat(getResources().getString(R.string.km)), 0, getTop((float)mMax), mPaintText);
        //Draw Text half part Values
        canvas.drawText(String.valueOf((float) mMax / 2).concat(getResources().getString(R.string.km)), 0, getTop((float)mMax / 2), mPaintText);
    }

    /**
     * @param value value
     * @return top
     */
    private float getTop(float value) {
        float realHeight = (getHeight() - getPaddingBottom() - getPaddingTop()) * (value * 1F / mMax * 1F);
        return getHeight() - getPaddingBottom() - realHeight;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mScaleDetector.onTouchEvent(ev);
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            //Reset mDatas
            mPaths.clear();
            mTimes.clear();
            mPrevXMove = ev.getX();
            mXDown = mPrevXMove;
            mTimeDown = System.currentTimeMillis();

            //Save the mDatas of the first touch
            mPaths.add(mPrevXMove);
            mTimes.add(mTimeDown);
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            float currentXMove = ev.getX();
            mPaths.add(currentXMove);
            mTimes.add(System.currentTimeMillis());

            //When long touch, reset mDatas
            if (mPrevXMove == currentXMove) {
                mXDown = currentXMove;
                mTimeDown = System.currentTimeMillis();
                mPaths.clear();
                mTimes.clear();
            }
            mIsMoveToRight = mPrevXMove <= currentXMove;
            if (ev.getPointerCount() == 1) {
                //Check if the last column at right move to limit
                if (!mIsMoveToRight && mOffsetX == 0) {
                    return true;
                }
                //Check if the last column at left move to limit
                if (mIsMoveToRight && mOffsetX <= mLefts[mSizeData - 1] - getPaddingLeft() - 100) {
                    return true;
                }
                //Update offsetX
                mOffsetX += mPrevXMove - currentXMove;

                //If move to left and and the last column exceed the limit, must block it
                if (!mIsMoveToRight && mOffsetX > 0) {
                    mOffsetX = 0;
                }
                //If move to right and and the first column exceed the limit, must block it
                if (mIsMoveToRight && mOffsetX <= mLefts[mSizeData - 1] - getPaddingLeft() - 100) {
                    mOffsetX = mLefts[mSizeData - 1] - getPaddingLeft() - 100;
                }
            }
            //Update latest x coordinate
            mPrevXMove = currentXMove;
            //Re-draw
            invalidate();
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {
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
            if (mIsMoveToRight && mOffsetX <= mLefts[mSizeData - 1] - getPaddingLeft() - 100) {
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
                mSpeed = Math.abs(s / time);
                mSpeed = mSpeed * 50 / 3F;
                if (mSpeed > 0) {
                    final android.os.Handler handler = new android.os.Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Check if the first column at left move to limit
                            if (mIsMoveToRight) {
                                mOffsetX -= mSpeed;
                                if (mOffsetX < mLefts[mSizeData - 1] - getPaddingLeft() - 100) {
                                    mOffsetX = mLefts[mSizeData - 1] - getPaddingLeft() - 100;
                                    mSpeed = 0;
                                }
                            } else {//Check if the last column at right move to limit
                                mOffsetX += mSpeed;
                                if (mOffsetX > 0) {
                                    mOffsetX = 0;
                                    mSpeed = 0;
                                }
                            }
                            invalidate();
                            //Scroll, scroll, scroll...
                            if (mSpeed-- > 0) {
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
            mScaleFactor = Math.max(1.0f, Math.min(mScaleFactor, 2.0f));
            invalidate();
            return true;
        }
    }
}
