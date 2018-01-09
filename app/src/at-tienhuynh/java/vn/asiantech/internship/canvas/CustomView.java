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
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import vn.asiantech.internship.R;

public class CustomView extends View {

    private Paint mPaintText = new Paint();
    private Paint mPaintRect = new Paint();
    private Paint mPaintLine = new Paint();
    private Paint mPaintColumn = new Paint();
    private Paint mPaintColumn1 = new Paint();
    private Paint mPaintColumn2 = new Paint();
    private int mColumnWidth = getResources().getDimensionPixelSize(R.dimen.column_width);
    private int mColumnCornerRadius = getResources().getDimensionPixelSize(R.dimen.column_corner_radius);
    private int mColumnMarginHorizontal = getResources().getDimensionPixelSize(R.dimen.columns_margin_horizontal);
    private List<Integer> mDataList = new ArrayList<>(Arrays.asList(2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9));
    private int max = Collections.max(mDataList);
    private int mSizeData = mDataList.size();
    private float mLefts[] = new float[mSizeData];
    private float mPrevXMove;
    private float mXDown;
    private long mTimeDown;
    private float mSpeed;
    private float mOffsetX;
    private boolean isMoveToRight;
    private List<Float> mPaths = new ArrayList<>();
    private List<Long> mTimes = new ArrayList<>();

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
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
            mColumnWidth = typedArray.getInteger(R.styleable.CustomView_column_width, 40);
        } finally {
            typedArray.recycle();
        }
    }

    /**
     * Init Paint
     */
    private void init() {
        mPaintText.setColor(Color.GRAY);
        mPaintText.setTextSize(getResources().getDimension(R.dimen.tv_value_chart));

        mPaintLine.setColor(ContextCompat.getColor(getContext(), R.color.colorBlack));
        mPaintLine.setStrokeWidth(5);
        mPaintLine.setAntiAlias(true);

        mPaintColumn.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        mPaintColumn.setAntiAlias(true);

        mPaintColumn1.setColor(ContextCompat.getColor(getContext(), R.color.colorCyanA700));
        mPaintColumn1.setAntiAlias(true);

        mPaintColumn2.setColor(ContextCompat.getColor(getContext(), R.color.colorHeader));
        mPaintColumn2.setAntiAlias(true);

        mPaintRect.setColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
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
        canvas.drawLine(mPaintText.measureText(String.valueOf(max).concat(getResources().getString(R.string.tv_canvas_km))),
                getTop(max), getWidth(),
                getTop(max), mPaintLine);
        //Draw End Line
        canvas.drawLine(0, getHeight() - getPaddingBottom(),
                getWidth(), getHeight() - getPaddingBottom(), mPaintLine);
        //Draw Middle Line
        canvas.drawLine(0,
                getTop(max / 2), getWidth(),
                getTop(max / 2), mPaintLine);
    }

    /**
     * Draw Chart
     *
     * @param canvas canvas
     */
    private void drawChart(Canvas canvas) {
        float leftRect = getWidth() - getPaddingRight() - mColumnMarginHorizontal - mColumnWidth - 100 - mOffsetX;
        for (int index = 0; index < mSizeData; index++) {
            canvas.drawRoundRect(new RectF(leftRect - 100 * index, getTop(mDataList.get(index)), leftRect - 100 * index + mColumnWidth, getHeight() - getPaddingBottom()), mColumnCornerRadius, mColumnCornerRadius, mPaintColumn);
            canvas.drawRoundRect(new RectF(leftRect - 100 * index - mColumnWidth - 5, getTop(mDataList.get(index)), leftRect - 100 * index - 5, getHeight() - getPaddingBottom()), mColumnCornerRadius, mColumnCornerRadius, mPaintColumn1);
            canvas.drawRoundRect(new RectF(leftRect - 100 * index - mColumnWidth * 2 - 10, getTop(mDataList.get(index)), leftRect - 100 * index - mColumnWidth - 10, getHeight() - getPaddingBottom()), mColumnCornerRadius, mColumnCornerRadius, mPaintColumn2);
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
        canvas.drawRect(0, 0, getPaddingLeft() + 100 - mColumnMarginHorizontal, getHeight(), mPaintRect);
        canvas.drawRect(getWidth() - getPaddingRight() - 100 + mColumnMarginHorizontal, 0, getWidth(), getHeight(), mPaintRect);
    }

    /**
     * Draw Text
     *
     * @param canvas canvas
     */
    private void drawText(Canvas canvas) {
        //Draw Text Max Values
        canvas.drawText(String.valueOf(max).concat(getResources().getString(R.string.tv_canvas_km)), 0, 50, mPaintText);
        //Draw Text Max Values
        canvas.drawText(String.valueOf(max / 2).concat(getResources().getString(R.string.tv_canvas_km)), 0, getHeight() / 2 - max, mPaintText);
    }

    /**
     * @param value value
     * @return top
     */
    private float getTop(int value) {
        float realHeight = (getHeight() - getPaddingBottom() - getPaddingTop()) * (value * 1F / max * 1F);
        return getHeight() - getPaddingBottom() - realHeight;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
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
            isMoveToRight = mPrevXMove <= currentXMove;
            if (ev.getPointerCount() == 1) {
                //Check if the last column at right move to limit
                if (!isMoveToRight && mOffsetX == 0) {
                    return true;
                }
                //Check if the last column at left move to limit
                if (isMoveToRight && mOffsetX <= mLefts[mSizeData - 1] - getPaddingLeft() - 100) {
                    return true;
                }
                //Update mOffsetX
                mOffsetX += mPrevXMove - currentXMove;

                //If move to left and and the last column exceed the limit, must block it
                if (!isMoveToRight && mOffsetX > 0) {
                    mOffsetX = 0;
                }
                //If move to right and and the first column exceed the limit, must block it
                if (isMoveToRight && mOffsetX <= mLefts[mSizeData - 1] - getPaddingLeft() - 100) {
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
                if ((isMoveToRight && mPaths.get(i - 1) > mPaths.get(i)) || (!isMoveToRight && mPaths.get(i - 1) < mPaths.get(i))) {
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
            if (isMoveToRight && mOffsetX <= mLefts[mSizeData - 1] - getPaddingLeft() - 100) {
                return true;
            }
            //Check if the last column at right move to limit
            if (!isMoveToRight && mOffsetX == 0) {
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
                            if (isMoveToRight) {
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
}
