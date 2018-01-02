package vn.asiantech.internship.ui.canvas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import vn.asiantech.internship.R;

/**
 * Created by tiboo on 25/12/2017.
 * Create custom view
 */

public class CustomView extends View {
    private static final int MARGIN_TOP = 100;
    private static final int MARGIN_DAY = 50;
    private static final int CORNER_RADIUS = 3;
    private static final int WIDTH_DAY = 86;
    private static int mStartHeight;
    private static int mStartWidth;
    private static int mMaxHeight;
    private static int mWidthScreen;
    private static float mUnit;
    private static int mMaxDistance = 0;
    private static String mTextDistanceMax;
    private static String mTextDistanceCenter;
    private static String mTextDistanceStart;
    private static String mTextMonday;
    private static String mTextSunday;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;
    private int mListPersonA[] = {1, 7, 5, 2, 9, 6, 5};
    private int mListPersonB[] = {1, 3, 5, 2, 2, 6, 5};
    private int mListPersonC[] = {1, 8, 5, 2, 11, 6, 5};
    private Paint mPaintLine;
    private Paint mPaintColumn;
    private Paint mPaintText;
    private float mBeforeX;
    private float mAfterX;

    private float mMove;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        findMaxDistance();
        transmissionString();
        initPaintText();
        initPaintColumn();
        initPaintLine();
    }

    /**
     * @param event Event touch
     * @return event
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mScaleDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mBeforeX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() == 1) {
                    mAfterX = event.getX();
                    mMove = (mAfterX - mBeforeX);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                mBeforeX = mAfterX;
        }
        return true;
    }

    /**
     * @param canvas Draw
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor);
        calculation();
        mStartWidth *= mScaleFactor;
        mStartHeight *= mScaleFactor;
        mMaxHeight *= mScaleFactor;
        mStartWidth += mMove;
        drawTexts(canvas);
        drawLines(canvas);
        drawColumns(canvas);
        canvas.restore();
    }

    /**
     * Set value start of columns
     */
    private void calculation() {
        mWidthScreen = getWidth();
        int heightScreen = getHeight();
        mStartWidth = mWidthScreen / 6;
        mStartHeight = 2 * heightScreen / 3;
        mMaxHeight = mStartHeight - MARGIN_TOP;
        mUnit = (float) mMaxHeight / mMaxDistance;
    }

    /**mHeightScreen
     * @param canvas Draw columns distance
     */
    private void drawColumns(Canvas canvas) {
        for (int i = 0; i < mListPersonA.length; i++) {
            int marginColumn = 18;
            // Draw column distance person A
            mPaintColumn.setColor(this.getResources().getColor(R.color.colorColumnDistancePersonA));
            int widthColumn = 15;
            canvas.drawRoundRect((new RectF(mStartWidth, mStartHeight - mListPersonA[i] * mUnit,
                            mStartWidth + widthColumn, mStartHeight)), CORNER_RADIUS, CORNER_RADIUS,
                    mPaintColumn);
            // Update location start
            mStartWidth += marginColumn;
            // Draw column distance person B
            mPaintColumn.setColor(this.getResources().getColor(R.color.colorColumnDistancePersonB));
            canvas.drawRoundRect((new RectF(mStartWidth, mStartHeight - mListPersonB[i] * mUnit,
                            mStartWidth + widthColumn, mStartHeight)), CORNER_RADIUS, CORNER_RADIUS,
                    mPaintColumn);
            // Update location start
            mStartWidth += marginColumn;
            // Draw column distance person C
            mPaintColumn.setColor(this.getResources().getColor(R.color.colorColumnDistancePersonC));
            canvas.drawRoundRect((new RectF(mStartWidth, mStartHeight - mListPersonC[i] * mUnit,
                            mStartWidth + widthColumn, mStartHeight)), CORNER_RADIUS, CORNER_RADIUS,
                    mPaintColumn);
            // Update location start
            mStartWidth += MARGIN_DAY;
        }
    }

    /**
     * @param canvas Draw lines
     */
    private void drawLines(Canvas canvas) {
        // Draw line min distance
        canvas.drawLine(mStartWidth, mStartHeight, mWidthScreen, mStartHeight, mPaintLine);
        // Draw line center distance
        canvas.drawLine(mStartWidth, (mMaxHeight / 2) + MARGIN_TOP, mWidthScreen,
                (mMaxHeight / 2) + MARGIN_TOP, mPaintLine);
        // Draw line max distance
        canvas.drawLine(mStartWidth, MARGIN_TOP, mWidthScreen, MARGIN_TOP, mPaintLine);
    }

    /**
     * @param canvas Draw texts
     */
    private void drawTexts(Canvas canvas) {
        // Draw text max distance
        canvas.drawText(mTextDistanceMax, mStartWidth - mPaintText.measureText(mTextDistanceMax) - 10,
                MARGIN_TOP + (mPaintText.getTextSize() / 2), mPaintText);
        // Draw text center distance
        canvas.drawText(mTextDistanceCenter, mStartWidth - mPaintText.measureText(mTextDistanceCenter) - 10,
                mMaxHeight / 2 + MARGIN_TOP, mPaintText);
        // Draw text min distance
        canvas.drawText(mTextDistanceStart, mStartWidth - mPaintText.measureText(mTextDistanceStart) - 10,
                mStartHeight, mPaintText);
        // Draw text monday
        canvas.drawText(mTextMonday, mStartWidth, mStartHeight + mPaintText.getTextSize(), mPaintText);
        // Draw text sunday
        canvas.drawText(mTextSunday, mStartWidth + WIDTH_DAY * 6,
                mStartHeight + mPaintText.getTextSize(), mPaintText);
    }

    /**
     * Init line
     */
    private void initPaintLine() {
        mPaintLine = new Paint();
        mPaintLine.setColor(Color.GRAY);
        mPaintLine.setAntiAlias(true);
        mPaintLine.setStyle(Paint.Style.FILL);
        mPaintLine.setStrokeWidth(3);
    }

    /**
     * Init column
     */
    private void initPaintColumn() {
        mPaintColumn = new Paint();
        mPaintColumn.setAntiAlias(true);
        mPaintColumn.setStyle(Paint.Style.FILL);
    }

    /**
     * Init text
     */
    private void initPaintText() {
        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setColor(Color.GRAY);
        mPaintText.setTextSize(30);
        mPaintText.setStrokeWidth(3);
        mPaintText.setStyle(Paint.Style.STROKE);
    }

    /**
     * Text
     */
    private void transmissionString() {
        int startDistance = 0;
        mTextDistanceMax = String.valueOf(mMaxDistance) + " Km";
        mTextDistanceCenter = String.valueOf(((float) mMaxDistance / 2)) + " Km";
        mTextDistanceStart = String.valueOf(startDistance) + " Km";
        mTextMonday = getResources().getString(R.string.monday);
        mTextSunday = getResources().getString(R.string.sunday);
    }

    /**
     * Max distance
     */
    private void findMaxDistance() {
        for (int aListPersonA : mListPersonA) {
            if (aListPersonA >= mMaxDistance) {
                mMaxDistance = aListPersonA;
            }
            for (int aListPersonB : mListPersonB) {
                if (aListPersonB >= mMaxDistance) {
                    mMaxDistance = aListPersonB;
                }
                for (int aListPersonC : mListPersonC) {
                    if (aListPersonC >= mMaxDistance) {
                        mMaxDistance = aListPersonC;
                    }
                }
            }
        }
    }

    /**
     * Scale view
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
