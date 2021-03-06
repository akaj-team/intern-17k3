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
 * Create custom view
 */
public class CustomView extends View {
    private List<Integer> mDataPersonA;
    private List<Integer> mDataPersonB;
    private List<Integer> mDataPersonC;
    private int mMaxDistance;
    private int mSizeData;
    private float mLefts[];
    private float mPrevXMove;
    private float mXDown;
    private long mTimeDown;
    private float mSpeedMove;
    private float mOffsetX;
    private boolean mIsMoveToRight;
    private List<Float> mPaths;
    private List<Long> mTimes;
    private float mScaleFactor = 1.f;
    private ScaleGestureDetector mScaleDetector;
    private Paint mPaintText;
    private Paint mPaintRect;
    private Paint mPaintLine;
    private Paint mPaintColumn;
    private int mColumnWidth;
    private int mColumnCornerRadius;
    private int mColumnMarginHorizontal;
    private int mDayMarginHorizontal;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomView, 0, 0);
        try {
            mColumnWidth = typedArray.getDimensionPixelSize(R.styleable.CustomView_stroke_with, getResources().getDimensionPixelSize(R.dimen.custom_view_default_column_width));
        } finally {
            typedArray.recycle();
        }
        mScaleDetector = new ScaleGestureDetector(context, new CustomView.ScaleListener());
        init();
    }

    /**
     * Init views
     */
    private void init() {
        // Init pain text
        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setColor(Color.GRAY);
        mPaintText.setTextSize(getResources().getDimensionPixelSize(R.dimen.text_size));
        mPaintText.setStrokeWidth(getResources().getDimensionPixelOffset(R.dimen.stroke_width_text));
        mPaintText.setStyle(Paint.Style.STROKE);
        // Init pain line
        mPaintLine = new Paint();
        mPaintLine.setColor(Color.GRAY);
        mPaintLine.setStrokeWidth(getResources().getDimensionPixelOffset(R.dimen.stroke_width_line));
        // Init pain column distance
        mPaintColumn = new Paint();
        mPaintColumn.setAntiAlias(true);
        // Init pain glades
        mPaintRect = new Paint();
        mPaintRect.setColor(Color.WHITE);
        // Init data
        mDataPersonA = new ArrayList<>(Arrays.asList(2, 9, 4, 5, 6, 7,
                8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8));
        mDataPersonB = new ArrayList<>(Arrays.asList(3, 5, 5, 7, 2, 4,
                7, 1, 8, 5, 3, 1, 2, 9, 11, 4, 3, 10, 3, 3, 9, 4, 3, 5, 7, 2, 4, 7, 1, 8, 5, 3, 1, 2, 9, 11));
        mDataPersonC = new ArrayList<>(Arrays.asList(7, 8, 7, 3, 8, 3,
                7, 5, 8, 2, 7, 5, 2, 6, 6, 8, 9, 2, 11, 5, 7, 5, 7, 7, 3, 8, 3, 7, 5, 8, 2, 7, 5, 2, 6, 6));
        mMaxDistance = Math.max(Collections.max(mDataPersonA), Math.max(Collections.max(mDataPersonB), Collections.max(mDataPersonC)));
        mSizeData = mDataPersonA.size();
        mLefts = new float[mSizeData];
        mPaths = new ArrayList<>();
        mTimes = new ArrayList<>();
        mColumnCornerRadius = getResources().getDimensionPixelSize(R.dimen.column_corner_radius);
        mColumnMarginHorizontal = getResources().getDimensionPixelSize(R.dimen.columns_margin_horizontal);
        mDayMarginHorizontal = getResources().getDimensionPixelSize(R.dimen.day_margin_horizontal);
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
     * @param canvas : Draw lines
     */
    private void drawLines(Canvas canvas) {
        canvas.drawLine(getWidth() - getPaddingRight(), getPaddingTop(), getPaddingLeft(), getPaddingTop(), mPaintLine);
        canvas.drawLine(getWidth() - getPaddingRight(), getHeightColumn((float) mMaxDistance / 2), getPaddingLeft(), getHeightColumn((float) mMaxDistance / 2), mPaintLine);
        canvas.drawLine(getWidth() - getPaddingRight(), getHeight() - getPaddingBottom(), getPaddingLeft(), getHeight() - getPaddingBottom(), mPaintLine);
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
     * @param canvas : Draw Glades
     */
    private void drawGlades(Canvas canvas) {
        canvas.drawRect(0, 0, getPaddingLeft(), getHeight(), mPaintRect);
        canvas.drawRect(getWidth() - getPaddingRight(), 0, getWidth() * (1 + mScaleFactor * 2), getHeight(), mPaintRect);
    }

    /**
     * @param canvas : Draw Text Distances
     */
    private void drawTextDistances(Canvas canvas) {
        canvas.drawText(mMaxDistance + getResources().getString(R.string.text_kilometer), getPaddingLeft() - mPaintText.measureText(mMaxDistance + getResources().getString(R.string.text_kilometer)) - getResources().getDimensionPixelOffset(R.dimen.column_margin_text), getPaddingTop() + mPaintText.getTextSize() / 2, mPaintText);
        canvas.drawText((float) mMaxDistance / 2 + getResources().getString(R.string.text_kilometer), getPaddingLeft() - mPaintText.measureText((float) mMaxDistance / 2 + getResources().getString(R.string.text_kilometer)) - getResources().getDimensionPixelOffset(R.dimen.column_margin_text), getHeightColumn((float) mMaxDistance / 2) + mPaintText.getTextSize() / 2, mPaintText);
        canvas.drawText(getResources().getString(R.string.value_0) + getResources().getString(R.string.text_kilometer), getPaddingLeft() - mPaintText.measureText(getResources().getString(R.string.value_0) + getResources().getString(R.string.text_kilometer)) - getResources().getDimensionPixelOffset(R.dimen.column_margin_text), getHeight() - getPaddingBottom() + mPaintText.getTextSize() / 2, mPaintText);
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
            mIsMoveToRight = mPrevXMove <= currentXMove;
            if (event.getPointerCount() == 1) {
                // Check if the last column at right move to limit
                if (!mIsMoveToRight && mOffsetX == 0) {
                    return true;
                }
                // Check if the last column at left move to limit
                if (mIsMoveToRight && mOffsetX <= mLefts[mSizeData - 1] - getPaddingLeft()) {
                    return true;
                }
                // Update mOffsetX
                mOffsetX += mPrevXMove - currentXMove;

                // If move to left and and the last column exceed the limit, must block it
                if (!mIsMoveToRight && mOffsetX > 0) {
                    mOffsetX = 0;
                }
                // If move to right and and the first column exceed the limit, must block it
                if (mIsMoveToRight && mOffsetX <= mLefts[mSizeData - 1] - getPaddingLeft()) {
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
            // Check if the last column at left move to limit
            if (mIsMoveToRight && mOffsetX <= mLefts[mSizeData - 1] - getPaddingLeft()) {
                return true;
            }
            // Check if the last column at right move to limit
            if (!mIsMoveToRight && mOffsetX == 0) {
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
                            if (mIsMoveToRight) {
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
