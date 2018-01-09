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
    private Paint mPaintText;
    private Paint mPaintRect;
    private Paint mPaintLine;
    private Paint mPaintColumn1;
    private Paint mPaintColumn2;
    private Paint mPaintColumn3;
    private int mColumnWidth = getResources().getDimensionPixelSize(R.dimen.column_width);
    private int mColumnCornerRadius = getResources().getDimensionPixelSize(R.dimen.column_corner_radius);
    private int mColumnMarginHorizontal = getResources().getDimensionPixelSize(R.dimen.columns_margin_horizontal);
    private List<Integer> mDatas1;
    private List<Integer> mDatas2;
    private List<Integer> mDatas3;
    private int mMax;
    private int mSizeData;
    //To save the first left of all columns
    private float mLefts[];
    //Prev x coordinate in action move
    private float prevXMove;
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
    private boolean isMoveToRight;
    //Save x coordinate of columns
    private List<Float> mPaths;
    //Same with #path but it's mTimes
    private List<Long> mTimes;

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
            mColumnWidth = typedArray.getInteger(R.styleable.CustomView_stroke_with, 40);
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
        mPaintLine.setStrokeWidth(5);
        mPaintLine.setAntiAlias(true);
        mPaintColumn1 = new Paint();
        mPaintColumn1.setColor(Color.RED);
        mPaintColumn1.setAntiAlias(true);
        mPaintColumn2 = new Paint();
        mPaintColumn2.setColor(Color.YELLOW);
        mPaintColumn2.setAntiAlias(true);
        mPaintColumn3 = new Paint();
        mPaintColumn3.setColor(Color.BLUE);
        mPaintColumn3.setAntiAlias(true);
        mPaintRect = new Paint();
        mPaintRect.setColor(Color.WHITE);
        mDatas1 = new ArrayList<>(Arrays.asList(11, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9));
        mDatas2 = new ArrayList<>(Arrays.asList(8, 6, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9));
        mDatas3 = new ArrayList<>(Arrays.asList(3, 5, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9));
        mSizeData = mDatas1.size();
        mMax = Collections.max(mDatas1);
        mLefts = new float[mSizeData];
        mPaths = new ArrayList<>();
        mTimes = new ArrayList<>();
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
        canvas.drawLine(mPaintText.measureText(String.valueOf(mMax).concat(getResources()
                        .getString(R.string.km))),
                getTop(mMax), getWidth(),
                getTop(mMax), mPaintLine);
        //Draw End Line
        canvas.drawLine(0, getHeight() - getPaddingBottom(),
                getWidth(), getHeight() - getPaddingBottom(), mPaintLine);
        //Draw Middle Line
        canvas.drawLine(0,
                getTop(mMax / 2), getWidth(),
                getTop(mMax / 2), mPaintLine);
    }

    /**
     * Draw Chart
     *
     * @param canvas canvas
     */
    private void drawChart(Canvas canvas) {
        float leftRect = getWidth() - getPaddingRight() - mColumnMarginHorizontal - mColumnWidth - 100 - mOffsetX;
        for (int index = 0; index < mSizeData; index++) {
            canvas.drawRoundRect(new RectF(leftRect - 100 * index, getTop(mDatas1.get(index)), leftRect - 100 * index + mColumnWidth, getHeight() - getPaddingBottom()), mColumnCornerRadius, mColumnCornerRadius, mPaintColumn1);
            canvas.drawRoundRect(new RectF(leftRect - 100 * index - 42, getTop(mDatas2.get(index)), leftRect - 100 * index - 42 + mColumnWidth, getHeight() - getPaddingBottom()), mColumnCornerRadius, mColumnCornerRadius, mPaintColumn2);
            canvas.drawRoundRect(new RectF(leftRect - 100 * index - 84, getTop(mDatas3.get(index)), leftRect - 100 * index - 84 + mColumnWidth, getHeight() - getPaddingBottom()), mColumnCornerRadius, mColumnCornerRadius, mPaintColumn3);
            canvas.drawText(convertDay(mSizeData-index), leftRect - 100 * index - 84 + mColumnWidth,
                    getHeight() - getPaddingBottom() + 50, mPaintText);
            //Save the first left of all columns
            if (mOffsetX == 0) {
                mLefts[index] = leftRect;
            }
            leftRect -= mColumnWidth + mColumnMarginHorizontal;
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
        canvas.drawText(String.valueOf(mMax).concat(getResources().getString(R.string.km)), 0, getTop(mMax), mPaintText);
        //Draw Text Max Values
        canvas.drawText(String.valueOf(mMax / 2).concat(getResources().getString(R.string.km)), 0, getTop(mMax / 2), mPaintText);
    }

    /**
     * @param value value
     * @return top
     */
    private float getTop(int value) {
        float realHeight = (getHeight() - getPaddingBottom() - getPaddingTop()) * (value * 1F / mMax * 1F);
        return getHeight() - getPaddingBottom() - realHeight;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            //Reset mDatas
            mPaths.clear();
            mTimes.clear();
            prevXMove = ev.getX();
            mXDown = prevXMove;
            mTimeDown = System.currentTimeMillis();

            //Save the mDatas of the first touch
            mPaths.add(prevXMove);
            mTimes.add(mTimeDown);
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            float currentXMove = ev.getX();
            mPaths.add(currentXMove);
            mTimes.add(System.currentTimeMillis());

            //When long touch, reset mDatas
            if (prevXMove == currentXMove) {
                mXDown = currentXMove;
                mTimeDown = System.currentTimeMillis();
                mPaths.clear();
                mTimes.clear();
            }
            isMoveToRight = prevXMove <= currentXMove;
            if (ev.getPointerCount() == 1) {
                //Check if the last column at right move to limit
                if (!isMoveToRight && mOffsetX == 0) {
                    return true;
                }
                //Check if the last column at left move to limit
                if (isMoveToRight && mOffsetX <= mLefts[mSizeData - 1] - getPaddingLeft() - 100) {
                    return true;
                }
                //Update offsetX
                mOffsetX += prevXMove - currentXMove;

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
            prevXMove = currentXMove;
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
