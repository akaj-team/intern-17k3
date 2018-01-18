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
    private Paint mPaintText;
    private Paint mPaintRect;
    private Paint mPaintLine;
    private Paint mPaintColumnPink;
    private Paint mPaintColumnBlue;
    private Paint mPaintColumnOrange;
    private int mColumnWidth;
    private int mColumnCornerRadiusHorizontal;
    private List<Integer> mDataList;
    private List<Integer> mDataList2;
    private List<Integer> mDataList3;
    private float mMax;
    private int mSizeData;
    private float mLefts[];
    private float mPrevXMove;
    private float mXDown;
    private long mTimeDown;
    private float mSpeed;
    private float mOffsetX;
    int mMarginLeft = 100;
    private boolean mIsMoveToRight;
    private List<Float> mPaths;
    private List<Long> mTimes;
    // scale
    private boolean mDragged = true;
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
        // scale
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        initValues();
        initPaint();
    }

    /**
     * init Value
     */
    private void initValues() {
        mPaintText = new Paint();
        mPaintRect = new Paint();
        mPaintLine = new Paint();
        mPaintColumnPink = new Paint();
        mPaintColumnBlue = new Paint();
        mPaintColumnOrange = new Paint();
        mDataList2 = new ArrayList<>(Arrays.asList(3, 2, 6, 3, 7, 8, 10, 2, 4, 5, 6, 7, 6, 9, 3, 9, 5, 3, 7, 8, 9, 6, 4, 5, 7, 9, 6, 8, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 10, 8, 9, 2, 4, 11, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9));
        mDataList3 = new ArrayList<>(Arrays.asList(2, 4, 3, 5, 8, 3, 11, 5, 6, 2, 6, 7, 8, 11, 2, 4, 5, 6, 7, 8, 11, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 11, 9, 2, 4, 5, 6, 7, 11, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 10, 9, 4, 5, 10, 10, 8, 9, 2, 11, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 10, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9));
        mPaths = new ArrayList<>();
        mTimes = new ArrayList<>();
        mDataList = new ArrayList<>(Arrays.asList(2, 4, 5, 6, 7, 8, 11, 2, 4, 5, 6, 7, 8, 11, 2, 4, 5, 6, 7, 8, 11, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 11, 2, 4, 5, 6, 7, 11, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 11, 2, 6, 7, 10, 9, 2, 4, 5, 6, 10, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9));
        float maxListPeoplePink = Collections.max(mDataList);
        float maxListPeopleCyan = Collections.max(mDataList2);
        float maxListPeopleBlue = Collections.max(mDataList3);
        mMax = Math.max(Math.max(maxListPeoplePink, maxListPeopleCyan), maxListPeopleBlue);
        // get size of list
        mSizeData = mDataList.size();
        // get lefts size
        mLefts = new float[mSizeData];
    }

    /**
     * Get attrs from XML file
     *
     * @param attrs attrs
     */
    private void attributeSet(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomView, 0, 0);
        try {
            mColumnWidth = typedArray.getDimensionPixelOffset(R.styleable.CustomView_column_width, getResources()
                    .getDimensionPixelSize(R.dimen.custom_column_width));
        } finally {
            typedArray.recycle();
        }
    }

    /**
     * initPaint Paint
     */
    private void initPaint() {
        mPaintText.setColor(Color.GRAY);
        mPaintText.setTextSize(getResources().getDimension(R.dimen.tv_chart));

        mPaintLine.setColor(ContextCompat.getColor(getContext(), R.color.black));
        mPaintLine.setStrokeWidth(getResources().getDimension(R.dimen.tv_paint_line));
        mPaintLine.setAntiAlias(true);

        mPaintColumnPink.setColor(ContextCompat.getColor(getContext(), R.color.colorPurple800));
        mPaintColumnPink.setAntiAlias(true);

        mPaintColumnBlue.setColor(ContextCompat.getColor(getContext(), R.color.colorCyanA700));
        mPaintColumnBlue.setAntiAlias(true);

        mPaintColumnOrange.setColor(ContextCompat.getColor(getContext(), R.color.colorOrange500));
        mPaintColumnOrange.setAntiAlias(true);

        mPaintRect.setColor(ContextCompat.getColor(getContext(), R.color.white));
        mColumnWidth = getResources().getDimensionPixelSize(R.dimen.column_width);
        mColumnCornerRadiusHorizontal = getResources().getDimensionPixelSize(R.dimen.column_corner_radius);
    }

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
        canvas.restore();
    }

    /**
     * Draw Line
     *
     * @param canvas canvas
     */
    private void drawLine(Canvas canvas) {
        //Draw Max Line
        canvas.drawLine(0,
                getTop(mMax),
                getWidth(),
                getTop(mMax),
                mPaintLine);
        //Draw End Line
        canvas.drawLine(0, getHeight() - getPaddingBottom(),
                getWidth(), getHeight() - getPaddingBottom(), mPaintLine);
        //Draw Middle Line
        canvas.drawLine(0,
                getTop((mMax / 2)),
                getWidth(),
                getTop((mMax / 2)),
                mPaintLine);
    }

    /**
     * Draw Chart
     *
     * @param canvas canvas
     */
    private void drawChart(Canvas canvas) {
        float leftRect = getWidth() - getPaddingRight() - mColumnCornerRadiusHorizontal - mColumnWidth - 100 - mOffsetX;
        for (int index = 0; index < mSizeData; index++) {
            canvas.drawRoundRect(new RectF(leftRect - mMarginLeft * index, getTop(mDataList.get(index)), leftRect - mMarginLeft * index + mColumnWidth, getHeight() - getPaddingBottom()), mColumnCornerRadiusHorizontal, mColumnCornerRadiusHorizontal, mPaintColumnPink);
            canvas.drawRoundRect(new RectF(leftRect - mMarginLeft * index - mColumnWidth - 5, getTop(mDataList2.get(index)), leftRect - mMarginLeft * index - 5, getHeight() - getPaddingBottom()), mColumnCornerRadiusHorizontal, mColumnCornerRadiusHorizontal, mPaintColumnBlue);
            canvas.drawRoundRect(new RectF(leftRect - mMarginLeft * index - mColumnWidth * 2 - 10, getTop(mDataList3.get(index)), leftRect - mMarginLeft * index - mColumnWidth - 10, getHeight() - getPaddingBottom()), mColumnCornerRadiusHorizontal, mColumnCornerRadiusHorizontal, mPaintColumnOrange);
            //Save the first left of all columns
            if (mOffsetX == 0) {
                mLefts[index] = leftRect;
            }
            leftRect -= mColumnWidth + mColumnCornerRadiusHorizontal;
        }
    }

    /**
     * Draw Rect
     *
     * @param canvas canvas
     */
    private void drawRect(Canvas canvas) {
        canvas.drawRect(0, 0, getPaddingLeft() + 100 - mColumnCornerRadiusHorizontal, getHeight(), mPaintRect);
        canvas.drawRect(getWidth() - getPaddingRight() - 100 + mColumnCornerRadiusHorizontal, 0, getWidth(), getHeight(), mPaintRect);
    }

    /**
     * Draw Text
     *
     * @param canvas canvas
     */
    private void drawText(Canvas canvas) {
        //Draw Text Max Values
        canvas.drawText(mMax + (getResources().getString(R.string.tv_canvas_km)), 0, getTop(mMax) + mColumnWidth, mPaintText);
        //Draw Text Center Values
        canvas.drawText(mMax / 2 + (getResources().getString(R.string.tv_canvas_km)), 0, getTop(mMax / 2), mPaintText);
        //Draw Text Below
        canvas.drawText(getResources().getString(R.string.tv_canvas_km), 0, getHeight(), mPaintText);
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
            //Reset mDataList
            mPaths.clear();
            mTimes.clear();
            mPrevXMove = ev.getX();
            mXDown = mPrevXMove;
            mTimeDown = System.currentTimeMillis();

            //Save the mDataList of the first touch
            mPaths.add(mPrevXMove);
            mTimes.add(mTimeDown);

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

            //When long touch, reset mDataList
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
        if ((mMode == DRAG && mScaleFactor != 1f && mDragged) || mMode == ZOOM) {
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
