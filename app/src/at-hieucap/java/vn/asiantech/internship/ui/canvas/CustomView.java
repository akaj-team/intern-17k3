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

/**
 * Create custom view
 */
public class CustomView extends View {
    private static List<Integer> mDataPersonA = new ArrayList<>(Arrays.asList(2, 9, 4, 5, 6, 7,
            8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8));
    private static List<Integer> mDataPersonB = new ArrayList<>(Arrays.asList(3, 5, 5, 7, 2, 4,
            7, 1, 8, 5, 3, 1, 2, 9, 11, 4, 3, 10, 3, 3, 9, 4, 3, 5, 7, 2, 4, 7, 1, 8, 5, 3, 1, 2, 9, 11));
    private static List<Integer> mDataPersonC = new ArrayList<>(Arrays.asList(7, 8, 7, 3, 8, 3,
            7, 5, 8, 2, 7, 5, 2, 6, 6, 8, 9, 2, 11, 5, 7, 5, 7, 7, 3, 8, 3, 7, 5, 8, 2, 7, 5, 2, 6, 6));
    private static int mMaxDistancePersonA = Collections.max(mDataPersonA);
    private static int mMaxDistancePersonB = Collections.max(mDataPersonB);
    private static int mMaxDistancePersonC = Collections.max(mDataPersonC);
    private static int mMaxDistance = Collections.max(new ArrayList<>(Arrays.asList(mMaxDistancePersonA,
            mMaxDistancePersonB, mMaxDistancePersonC)));
    private static int mSizeData = mDataPersonA.size();
    private static float mLefts[] = new float[mSizeData];
    private static float mPrevXMove;
    private static float mXDown;
    private static long mTimeDown;
    private static float mSpeedMove;
    private static float mOffsetX;
    private static boolean isMoveToRight;
    private static List<Float> mPaths = new ArrayList<>();
    private static List<Long> mTimes = new ArrayList<>();
    private static float mScaleFactor = 1.f;
    private static ScaleGestureDetector mScaleDetector;
    private Paint mPaintText = new Paint();
    private Paint mPaintRect = new Paint();
    private Paint mPaintLine = new Paint();
    private Paint mPaintColumn = new Paint();
    private int mColumnWidth;
    private int mColumnCornerRadius = getResources().getDimensionPixelSize(R.dimen.column_corner_radius);
    private int mColumnMarginHorizontal = getResources().getDimensionPixelSize(R.dimen.columns_margin_horizontal);
    private int mDayMarginHorizontal = getResources().getDimensionPixelSize(R.dimen.day_margin_horizontal);

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);
        mColumnWidth = typedArray.getInteger(R.styleable.CustomView_stroke_with, 10);
        mScaleDetector = new ScaleGestureDetector(context, new CustomView.ScaleListener());
        initViews();
    }

    /**
     * Init views
     */
    private void initViews() {
        // Init pain text
        mPaintText.setAntiAlias(true);
        mPaintText.setColor(Color.GRAY);
        mPaintText.setTextSize(30);
        mPaintText.setStrokeWidth(3);
        mPaintText.setStyle(Paint.Style.STROKE);
        // Init pain line
        mPaintLine.setColor(Color.GRAY);
        mPaintLine.setAntiAlias(true);
        mPaintLine.setStyle(Paint.Style.FILL);
        mPaintLine.setStrokeWidth(3);
        // Init pain column distance
        mPaintColumn.setStyle(Paint.Style.FILL);
        mPaintColumn.setAntiAlias(true);
        // Init pain glades
        mPaintRect.setColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
    }

    /**
     * @param canvas : Draw view
     */
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor);
        drawLines(canvas);
        drawColumnsDistanceAndTextDay(canvas);
        drawGlades(canvas);
        drawTextDistances(canvas);
        canvas.restore();
    }

    /**
     * @param canvas : Draw Text Distances
     */
    private void drawTextDistances(Canvas canvas) {
        canvas.drawText(String.valueOf(mMaxDistance) + getResources().getString(R.string.text_kilometer), getPaddingLeft() - mPaintText.measureText(String.valueOf(mMaxDistance) + getResources().getString(R.string.text_kilometer)) - 10, getPaddingTop() + mPaintText.getTextSize() / 2, mPaintText);
        canvas.drawText(String.valueOf((float) mMaxDistance / 2) + getResources().getString(R.string.text_kilometer), getPaddingLeft() - mPaintText.measureText(String.valueOf((float) mMaxDistance / 2) + getResources().getString(R.string.text_kilometer)) - 10, getHeightColumn((float) mMaxDistance / 2) + mPaintText.getTextSize() / 2, mPaintText);
        canvas.drawText(String.valueOf(getResources().getString(R.string.value_0)) + getResources().getString(R.string.text_kilometer), getPaddingLeft() - mPaintText.measureText(String.valueOf(getResources().getString(R.string.value_0)) + getResources().getString(R.string.text_kilometer)) - 10, getHeight() - getPaddingBottom() + mPaintText.getTextSize() / 2, mPaintText);
    }

    /**
     * @param canvas : Draw Glades
     */
    private void drawGlades(Canvas canvas) {
        canvas.drawRect(0, 0, getPaddingLeft(), getHeight(), mPaintRect);
        canvas.drawRect(getWidth() - getPaddingRight(), 0, getWidth() * (1 + mScaleFactor * 2), getHeight(), mPaintRect);
    }

    /**
     * @param canvas : Draw Columns Distance And Text Day
     */
    private void drawColumnsDistanceAndTextDay(Canvas canvas) {
        float leftRect = getWidth() - getPaddingRight() - mColumnMarginHorizontal - mColumnWidth - mOffsetX;
        for (int index = 0; index < mSizeData; index++) {
            // Draw texts day
            if (index == 0) {
                leftRect += mColumnMarginHorizontal;
                canvas.drawText(getResources().getString(R.string.sunday), leftRect - mPaintText.measureText(getResources().getString(R.string.sunday)) + mColumnWidth, getHeight() - getPaddingBottom() + mPaintText.getTextSize() * 2, mPaintText);
            } else if ((index) % 7 == 0) {
                canvas.drawText(getResources().getString(R.string.sunday), leftRect - mColumnWidth * 2 - mColumnMarginHorizontal * 2, getHeight() - getPaddingBottom() + mPaintText.getTextSize() * 2, mPaintText);
            } else if ((index) % 7 == 6) {
                canvas.drawText(getResources().getString(R.string.monday), leftRect - mColumnWidth * 2 - mColumnMarginHorizontal * 2, getHeight() - getPaddingBottom() + mPaintText.getTextSize() * 2, mPaintText);
            }
            // Draw column distance person C
            mPaintColumn.setColor(this.getResources().getColor(R.color.colorColumnDistancePersonC));
            canvas.drawRoundRect(new RectF(leftRect, getHeightColumn(mDataPersonC.get(index)), leftRect + mColumnWidth, getHeight() - getPaddingBottom()), mColumnCornerRadius, mColumnCornerRadius, mPaintColumn);
            leftRect -= mColumnWidth + mColumnMarginHorizontal;
            // Draw column distance person B
            mPaintColumn.setColor(this.getResources().getColor(R.color.colorColumnDistancePersonB));
            canvas.drawRoundRect(new RectF(leftRect, getHeightColumn(mDataPersonB.get(index)), leftRect + mColumnWidth, getHeight() - getPaddingBottom()), mColumnCornerRadius, mColumnCornerRadius, mPaintColumn);
            leftRect -= mColumnWidth + mColumnMarginHorizontal;
            // Draw column distance person A
            mPaintColumn.setColor(this.getResources().getColor(R.color.colorColumnDistancePersonA));
            canvas.drawRoundRect(new RectF(leftRect, getHeightColumn(mDataPersonA.get(index)), leftRect + mColumnWidth, getHeight() - getPaddingBottom()), mColumnCornerRadius, mColumnCornerRadius, mPaintColumn);
            // Save the first left of all columns
            if (mOffsetX == 0) {
                mLefts[index] = leftRect;
            }
            leftRect -= mColumnWidth + mColumnMarginHorizontal + mDayMarginHorizontal;
        }
    }

    /**
     * @param canvas : Draw lines
     */
    private void drawLines(Canvas canvas) {
        canvas.drawLine(getWidth() - getPaddingRight(), getPaddingTop(), getPaddingLeft(), getPaddingTop(), mPaintLine);
        canvas.drawLine(getWidth() - getPaddingRight(), getHeightColumn((float) mMaxDistance / 2), getPaddingLeft(), getHeightColumn((float) mMaxDistance / 2), mPaintLine);
        canvas.drawLine(getWidth() - getPaddingRight(), getHeight() - getPaddingBottom(), getPaddingLeft(), getHeight() - getPaddingBottom(), mPaintLine);
    }

    /**
     * @param value Height columns
     * @return Height columns
     */
    private float getHeightColumn(float value) {
        float realHeight = (getHeight() - getPaddingBottom() - getPaddingTop()) * (value * 1F / mMaxDistance * 1F);
        return getHeight() - getPaddingBottom() - realHeight;
    }

    /**
     * @param event on touch event
     * @return Action event
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mScaleDetector.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // Reset mDataPersonA
            mPaths.clear();
            mTimes.clear();
            mPrevXMove = event.getX();
            mXDown = mPrevXMove;
            mTimeDown = System.currentTimeMillis();
            // Save the mDataPersonA of the first touch
            mPaths.add(mPrevXMove);
            mTimes.add(mTimeDown);
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float currentXMove = event.getX();
            mPaths.add(currentXMove);
            mTimes.add(System.currentTimeMillis());
            // When long touch, reset mDataPersonA
            if (mPrevXMove == currentXMove) {
                mXDown = currentXMove;
                mTimeDown = System.currentTimeMillis();
                mPaths.clear();
                mTimes.clear();
            }
            isMoveToRight = mPrevXMove <= currentXMove;
            if (event.getPointerCount() == 1) {
                // Check if the last column at right move to limit
                if (!isMoveToRight && mOffsetX == 0) {
                    return true;
                }
                // Check if the last column at left move to limit
                if (isMoveToRight && mOffsetX <= mLefts[mSizeData - 1] - getPaddingLeft()) {
                    return true;
                }
                // Update mOffsetX
                mOffsetX += mPrevXMove - currentXMove;

                // If move to left and and the last column exceed the limit, must block it
                if (!isMoveToRight && mOffsetX > 0) {
                    mOffsetX = 0;
                }
                // If move to right and and the first column exceed the limit, must block it
                if (isMoveToRight && mOffsetX <= mLefts[mSizeData - 1] - getPaddingLeft()) {
                    mOffsetX = mLefts[mSizeData - 1] - getPaddingLeft();
                }
            }
            // Update latest x coordinate
            mPrevXMove = currentXMove;
            // Re-draw
            invalidate();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            // Find the latest corner, 1 2 3 4 5 6 5 4 3 2 1, corner is 6
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
            // Check if the last column at left move to limit
            if (isMoveToRight && mOffsetX <= mLefts[mSizeData - 1] - getPaddingLeft()) {
                return true;
            }
            // Check if the last column at right move to limit
            if (!isMoveToRight && mOffsetX == 0) {
                return true;
            }
            // Calculate the move speed to handle scroll after fling
            long time = System.currentTimeMillis() - mTimeDown;
            final float s = event.getX() - mXDown;
            if (time > 0) {
                mSpeedMove = Math.abs(s / time);
                mSpeedMove = mSpeedMove * 50 / 3F;
                if (mSpeedMove > 0) {
                    final android.os.Handler handler = new android.os.Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Check if the first column at left move to limit
                            if (isMoveToRight) {
                                mOffsetX -= mSpeedMove;
                                if (mOffsetX < mLefts[mSizeData - 1] - getPaddingLeft()) {
                                    mOffsetX = mLefts[mSizeData - 1] - getPaddingLeft();
                                    mSpeedMove = 0;
                                }
                            } else {
                                // Check if the last column at right move to limit
                                mOffsetX += mSpeedMove;
                                if (mOffsetX > 0) {
                                    mOffsetX = 0;
                                    mSpeedMove = 0;
                                }
                            }
                            invalidate();
                            // Scroll, scroll, scroll...
                            if (mSpeedMove-- > 0) {
                                handler.postDelayed(this, 1);
                            }
                        }
                    }, 1);
                }
            }
        }
        return true;
    }

    /**
     * Event Scroll
     */
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            mScaleFactor = Math.max(0.5f, Math.min(mScaleFactor, 5.0f));
            invalidate();
            return true;
        }
    }
}
