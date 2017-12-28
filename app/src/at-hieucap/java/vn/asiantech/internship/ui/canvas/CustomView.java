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
    private static int startHeight;
    private static int startWidth;
    private static int widthColumn = 15;
    private static int maxHeight;
    private static int widthScreen;
    private static int heightScreen;
    private static float unit;
    private static int MAX_DISTANCE = 0;
    private static String textDistanceMax;
    private static String textDistanceCenter;
    private static String textDistanceStart;
    private static String textMonday;
    private static String textSunday;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;
    private int listPersonA[] = {1, 7, 5, 2, 9, 6, 5};
    private int listPersonB[] = {1, 3, 5, 2, 2, 6, 5};
    private int listPersonC[] = {1, 8, 5, 2, 11, 6, 5};
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor);
        calculator();
        startWidth *= mScaleFactor;
        startHeight *= mScaleFactor;
        maxHeight *= mScaleFactor;
        startWidth += mMove;
        drawTexts(canvas);
        drawLines(canvas);
        drawColumns(canvas);
        canvas.restore();
    }

    private void calculator() {
        widthScreen = getWidth();
        heightScreen = getHeight();
        startWidth = widthScreen / 6;
        startHeight = 2 * heightScreen / 3;
        maxHeight = startHeight - MARGIN_TOP;
        unit = (float) maxHeight / MAX_DISTANCE;
    }

    private void drawColumns(Canvas canvas) {
        for (int i = 0; i < listPersonA.length; i++) {
            int marginColumn = 18;
            // Draw column distance person A
            mPaintColumn.setColor(this.getResources().getColor(R.color.colorColumnDistancePersonA));
            canvas.drawRoundRect((new RectF(startWidth, startHeight - listPersonA[i] * unit,
                            startWidth + widthColumn, startHeight)), CORNER_RADIUS, CORNER_RADIUS,
                    mPaintColumn);
            // Update location start
            startWidth += marginColumn;
            // Draw column distance person B
            mPaintColumn.setColor(this.getResources().getColor(R.color.colorColumnDistancePersonB));
            canvas.drawRoundRect((new RectF(startWidth, startHeight - listPersonB[i] * unit,
                            startWidth + widthColumn, startHeight)), CORNER_RADIUS, CORNER_RADIUS,
                    mPaintColumn);
            // Update location start
            startWidth += marginColumn;
            // Draw column distance person C
            mPaintColumn.setColor(this.getResources().getColor(R.color.colorColumnDistancePersonC));
            canvas.drawRoundRect((new RectF(startWidth, startHeight - listPersonC[i] * unit,
                    startWidth + widthColumn, startHeight)), CORNER_RADIUS, CORNER_RADIUS,
                    mPaintColumn);
            // Update location start
            startWidth += MARGIN_DAY;
        }
    }

    private void drawLines(Canvas canvas) {
        // Draw line min distance
        canvas.drawLine(startWidth, startHeight, widthScreen, startHeight, mPaintLine);
        // Draw line center distance
        canvas.drawLine(startWidth, (maxHeight / 2) + MARGIN_TOP, widthScreen,
                (maxHeight / 2) + MARGIN_TOP, mPaintLine);
        // Draw line max distance
        canvas.drawLine(startWidth, MARGIN_TOP, widthScreen, MARGIN_TOP, mPaintLine);
    }

    private void drawTexts(Canvas canvas) {
        // Draw text max distance
        canvas.drawText(textDistanceMax, startWidth - mPaintText.measureText(textDistanceMax) - 10,
                MARGIN_TOP + (mPaintText.getTextSize() / 2), mPaintText);
        // Draw text center distance
        canvas.drawText(textDistanceCenter, startWidth - mPaintText.measureText(textDistanceCenter) - 10,
                maxHeight / 2 + MARGIN_TOP, mPaintText);
        // Draw text min distance
        canvas.drawText(textDistanceStart, startWidth - mPaintText.measureText(textDistanceStart) - 10,
                startHeight, mPaintText);
        // Draw text monday
        canvas.drawText(textMonday, startWidth, startHeight + mPaintText.getTextSize(), mPaintText);
        // Draw text sunday
        canvas.drawText(textSunday, startWidth + WIDTH_DAY * 6,
                startHeight + mPaintText.getTextSize(), mPaintText);
    }

    private void initPaintLine() {
        mPaintLine = new Paint();
        mPaintLine.setColor(Color.GRAY);
        mPaintLine.setAntiAlias(true);
        mPaintLine.setStyle(Paint.Style.FILL);
        mPaintLine.setStrokeWidth(3);
    }

    private void initPaintColumn() {
        mPaintColumn = new Paint();
        mPaintColumn.setAntiAlias(true);
        mPaintColumn.setStyle(Paint.Style.FILL);
    }

    private void initPaintText() {
        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setColor(Color.GRAY);
        mPaintText.setTextSize(30);
        mPaintText.setStrokeWidth(3);
        mPaintText.setStyle(Paint.Style.STROKE);
    }

    private void transmissionString() {
        int startDistance = 0;
        textDistanceMax = String.valueOf(MAX_DISTANCE) + " Km";
        textDistanceCenter = String.valueOf(((float) MAX_DISTANCE / 2)) + " Km";
        textDistanceStart = String.valueOf(startDistance) + " Km";
        textMonday = getResources().getString(R.string.monday);
        textSunday = getResources().getString(R.string.sunday);
    }

    private void findMaxDistance() {
        for (int aListPersonA : listPersonA) {
            if (aListPersonA >= MAX_DISTANCE) {
                MAX_DISTANCE = aListPersonA;
            }
            for (int aListPersonB : listPersonB) {
                if (aListPersonB >= MAX_DISTANCE) {
                    MAX_DISTANCE = aListPersonB;
                }
                for (int aListPersonC : listPersonC) {
                    if (aListPersonC >= MAX_DISTANCE) {
                        MAX_DISTANCE = aListPersonC;
                    }
                }
            }
        }
    }

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
