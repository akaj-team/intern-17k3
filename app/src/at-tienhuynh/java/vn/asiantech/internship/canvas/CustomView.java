package vn.asiantech.internship.canvas;

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

/**
 * CustomView to draw custom my chart
 */
public class CustomView extends View {

    private static final int DEFAULT_ATTRS = 0;
    private Paint mPaintText;
    private Paint mPaintRect;
    private Paint mPaintLine;
    private Paint mPaintColumnPink;
    private Paint mPaintColumnCyan;
    private Paint mPaintColumnBlue;
    private int mColumnWidth;
    private int mColumnCornerRadius;
    private int mColumnMarginHorizontal;
    private List<Integer> mDataListPeoplePink;
    private List<Integer> mDataListPeopleCyan;
    private List<Integer> mDataListPeopleBlue;
    private float mMax;
    private int mSizeData;
    private float mLefts[];
    private float mPrevXMove;
    private float mXDown;
    private long mTimeDown;
    private float mSpeed;
    private float mOffsetX;
    private boolean mIsMoveToRight;
    private List<Float> mPaths;
    private List<Long> mTimes;
    // scale
    private boolean mIsDragged = true;
    private int mMode;
    private float mStartX = 0f;
    private float mStartY = 0f;
    private float mTranslateX = 0f;
    private float mTranslateY = 0f;
    private float mPreviousTranslateX = 0f;
    private float mPreviousTranslateY = 0f;
    private float mScaleFactor = 1.f;
    private ScaleGestureDetector mScaleDetector;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // Get attrs from XML file
        attributeSet(context, attrs);
        // init Paint
        init();
        // init Data List
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
                R.styleable.CustomView, DEFAULT_ATTRS, DEFAULT_ATTRS);
        try {
            mColumnWidth = typedArray.getDimensionPixelSize(R.styleable.CustomView_column_width, getResources()
                    .getDimensionPixelSize(R.dimen.custom_view_default_column_width));
        } finally {
            typedArray.recycle();
        }
    }

    /**
     * Init Paint
     */
    private void init() {

        // scale
        mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());

        mPaintText = new Paint();
        mPaintText.setColor(Color.GRAY);
        mPaintText.setTextSize(getResources().getDimension(R.dimen.tv_value_chart));

        mPaintLine = new Paint();
        mPaintLine.setColor(Color.BLACK);
        mPaintLine.setAntiAlias(true);

        mPaintColumnPink = new Paint();
        mPaintColumnPink.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        mPaintColumnPink.setAntiAlias(true);

        mPaintColumnCyan = new Paint();
        mPaintColumnCyan.setColor(ContextCompat.getColor(getContext(), R.color.colorCyanA700));
        mPaintColumnCyan.setAntiAlias(true);

        mPaintColumnBlue = new Paint();
        mPaintColumnBlue.setColor(ContextCompat.getColor(getContext(), R.color.colorHeader));
        mPaintColumnBlue.setAntiAlias(true);

        mPaintRect = new Paint();
        mPaintRect.setColor(Color.WHITE);

        mColumnCornerRadius = getResources().getDimensionPixelSize(R.dimen.column_corner_radius);
        mColumnMarginHorizontal = getResources().getDimensionPixelSize(R.dimen.columns_margin_horizontal);

        mPaths = new ArrayList<>();
        mTimes = new ArrayList<>();
    }

    /**
     * init data to list
     */
    private void initData() {

        //set data to list people one
        mDataListPeoplePink = new ArrayList<>(Arrays.asList(2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4,
                5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5,
                6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6,
                7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6,
                7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7,
                8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8,
                9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9,
                2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2,
                4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4,
                5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4,
                5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5,
                6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9));

        //set data to list people two
        mDataListPeopleCyan = new ArrayList<>(Arrays.asList(2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4,
                5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5,
                6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6,
                7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6,
                7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7,
                8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8,
                9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9,
                2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2,
                4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4,
                5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4,
                5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5,
                6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9));

        //set data to list people three
        mDataListPeopleBlue = new ArrayList<>(Arrays.asList(2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4,
                5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5,
                6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6,
                7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6,
                7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7,
                8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8,
                9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9,
                2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2,
                4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4,
                5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4,
                5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5,
                6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9));

        //get mMax of 3 list
        // first get max every list
        // after get max value of three max pick above
        float maxListPeoplePink = Collections.max(mDataListPeoplePink);
        float maxListPeopleCyan = Collections.max(mDataListPeopleCyan);
        float maxListPeopleBlue = Collections.max(mDataListPeopleBlue);
        mMax = Math.max(Math.max(maxListPeoplePink, maxListPeopleCyan), maxListPeopleBlue);
        // get size of list
        mSizeData = mDataListPeoplePink.size();
        // get lefts size
        mLefts = new float[mSizeData];
    }

    /**
     * Draw canvas
     *
     * @param canvas canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //scale
        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor);
        translate(canvas);
        //Draw Line
        drawLine(canvas);
        // drawChart
        drawChart(canvas);
        // drawRect
        drawRect(canvas);
        //drawText
        drawText(canvas);
        //restore
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
                getTop(mMax),
                getWidth(),
                getTop(mMax),
                mPaintLine);
        //Draw End Line
        canvas.drawLine(0,
                getHeight() - getPaddingBottom(),
                getWidth(),
                getHeight() - getPaddingBottom(),
                mPaintLine);
        //Draw Middle Line
        canvas.drawLine(0,
                getTop(mMax / 2),
                getWidth(),
                getTop(mMax / 2),
                mPaintLine);
    }

    /**
     * Draw Chart
     *
     * @param canvas canvas
     */
    private void drawChart(Canvas canvas) {
        float leftRect = getWidth() - getPaddingRight() - mColumnMarginHorizontal - mColumnWidth - 100 - mOffsetX;
        for (int index = 0; index < mSizeData; index++) {
            canvas.drawRoundRect(new RectF(leftRect - 100 * index, getTop(mDataListPeoplePink.get(index)),
                            leftRect - 100 * index + mColumnWidth,
                            getHeight() - getPaddingBottom()),
                    mColumnCornerRadius, mColumnCornerRadius,
                    mPaintColumnPink);
            leftRect -= mColumnMarginHorizontal + mColumnWidth;
            canvas.drawRoundRect(new RectF(leftRect - 100 * index - mColumnWidth - 5, getTop(mDataListPeopleCyan.get(index)),
                            leftRect - 100 * index - 5,
                            getHeight() - getPaddingBottom()),
                    mColumnCornerRadius, mColumnCornerRadius,
                    mPaintColumnCyan);
            leftRect -= mColumnMarginHorizontal + mColumnWidth;
            canvas.drawRoundRect(new RectF(leftRect - 100 * index - mColumnWidth * 2 - 10, getTop(mDataListPeopleBlue.get(index)),
                            leftRect - 100 * index - mColumnWidth - 10,
                            getHeight() - getPaddingBottom()),
                    mColumnCornerRadius, mColumnCornerRadius,
                    mPaintColumnBlue);
            //Save the first left of all columns
            if (mOffsetX == 0) {
                mLefts[index] = leftRect;
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
        canvas.drawRect(0,
                0, getPaddingLeft() + 100 - mColumnMarginHorizontal,
                getHeight(),
                mPaintRect);
        canvas.drawRect(getWidth() - getPaddingRight() - 100 + mColumnMarginHorizontal,
                0, getWidth(),
                getHeight(),
                mPaintRect);
    }

    /**
     * Draw Text
     *
     * @param canvas canvas
     */
    private void drawText(Canvas canvas) {
        //Draw Text Max Values
        canvas.drawText(String.valueOf(mMax).concat(getResources().getString(R.string.tv_canvas_km)), 0, getTop(mMax), mPaintText);
        //Draw Text Max Values
        canvas.drawText(String.valueOf(mMax / 2).concat(getResources().getString(R.string.tv_canvas_km)), 0, getTop(mMax / 2), mPaintText);
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
        int DRAG = 1;
        int ZOOM = 2;
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            //Reset mDataListPeoplePink
            mPaths.clear();
            mTimes.clear();
            mPrevXMove = ev.getX();
            mXDown = mPrevXMove;
            mTimeDown = System.currentTimeMillis();

            //Save the mDataListPeoplePink of the first touch
            mPaths.add(mPrevXMove);
            mTimes.add(mTimeDown);

            //scale
            mMode = DRAG;

            mStartX = ev.getX();
            mStartY = ev.getY();
            mStartX = ev.getX() - mPreviousTranslateX;
            mStartY = ev.getY() - mPreviousTranslateY;
        } else if (ev.getAction() == MotionEvent.ACTION_POINTER_DOWN) {
            mMode = ZOOM;
        } else if (ev.getAction() == MotionEvent.ACTION_POINTER_UP) {
            mMode = DRAG;
            mPreviousTranslateX = mTranslateX;
            mPreviousTranslateY = mTranslateY;
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            float currentXMove = ev.getX();
            mPaths.add(currentXMove);
            mTimes.add(System.currentTimeMillis());

            //When long touch, reset mDataListPeoplePink
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
                //Update mOffsetX
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

            //scale
            mTranslateX = ev.getX() - mStartX;
            mTranslateY = ev.getY() - mStartY;
            //Re-draw
            invalidate();
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {

            //scale
            mMode = 0;
            mIsDragged = false;
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
        mScaleDetector.onTouchEvent(ev);
        if ((mMode == DRAG && mScaleFactor != 1f && mIsDragged) || mMode == ZOOM) {
            invalidate();
        }
        return true;
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
}
