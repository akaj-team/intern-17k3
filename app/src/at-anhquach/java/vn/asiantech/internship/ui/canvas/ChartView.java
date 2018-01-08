package vn.asiantech.internship.ui.canvas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager_tablayout.ScreenUtil;

/**
 * Created by anh.quach on 12/29/17.
 * Chart View
 */
public class ChartView extends View {
    private boolean dragged = true;
    private int mode;

    private float startX = 0f;
    private float startY = 0f;
    private float translateX = 0f;
    private float translateY = 0f;
    private float previousTranslateX = 0f;
    private float previousTranslateY = 0f;
    private float mScaleFactor = 1.f;

    private Paint mPaintColumn1 = new Paint();
    private Paint mPaintText = new Paint();
    private Paint mPaintLine = new Paint();
    private Paint mPaintColumn = new Paint();
    private Paint mPaintColumn2 = new Paint();
    private Paint mPaintRect = new Paint();

    private ScaleGestureDetector mScaleDetector;
    private int columnWidth;
    double distance;
    private int mOxChart = 130;
    private int columnCornerRadius = getResources().getDimensionPixelSize(R.dimen.column_corner_radius);
    private int columnMarginHorizontal = getResources().getDimensionPixelSize(R.dimen.columns_margin_horizontal);
    private float mWitdhScreen = ScreenUtil.getWidthScreen(getContext());
    private float mHeightScreen = ScreenUtil.getHeightScreen(getContext());

    private List<Integer> mDistanceAs = new ArrayList<>(Arrays.asList(2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9));
    private List<Integer> mDistanceBs = new ArrayList<>(Arrays.asList(1, 2, 5, 2, 7, 8, 9, 7, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9));
    private List<Integer> mDistanceCs = new ArrayList<>(Arrays.asList(3, 9, 5, 6, 10, 8, 9, 2, 4, 4, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9));

    private float lefts[] = new float[mDistanceAs.size()];
    //Prev x coordinate in action move
    private float prevXMove;
    //x coordinate
    //If move from left to right or right to left, it's the x coordinate in action down
    //If move from left to right, to left..., it's the x coordinate in the latest corner
    private float xDown;
    //Same with #xDown but it's time
    private long timeDown;
    //Move speed after move and fling
    private float v;
    //To handle move column function
    private float offsetX;
    //Check if swipe to left or right
    private boolean isMoveToRight;
    //Save x coordinate of columns
    private List<Float> paths = new ArrayList<>();
    //Same with #path but it's times
    private List<Long> times = new ArrayList<>();

    public ChartView(Context context) {
        this(context, null);
    }

    @SuppressLint("ClickableViewAccessibility")
    public ChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.ChartView, 0, 0
        );
        try {
            columnWidth = a.getInteger(R.styleable.ChartView_line_width, 20);
        } finally {
            a.recycle();
        }
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        init();
    }

    private void init() {
        mPaintText.setColor(getResources().getColor(R.color.colorGrayDark));
        mPaintText.setTextSize(getResources().getDimension(R.dimen.textsize40));
        mPaintText.setAntiAlias(true);

        mPaintRect.setColor(getResources().getColor(R.color.colorWhite));

        mPaintLine.setColor(getResources().getColor(R.color.colorGrayDark));
        mPaintLine.setAntiAlias(true);

        mPaintColumn.setColor(getResources().getColor(R.color.colorPurple800));
        mPaintColumn.setAntiAlias(true);

        mPaintColumn1.setColor(getResources().getColor(R.color.colorCyanA700));
        mPaintColumn1.setAntiAlias(true);

        mPaintColumn2.setColor(getResources().getColor(R.color.colorOrange500));
        mPaintColumn2.setAntiAlias(true);
    }

    private float maxLists() {
        int max1 = Collections.max(mDistanceAs);
        int max2 = Collections.max(mDistanceBs);
        int max3 = Collections.max(mDistanceCs);
        return (float) Math.max(Math.max(max1, max2), max3);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int DRAG = 1;
        int ZOOM = 2;
        switch (ev.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                mode = DRAG;
                startX = ev.getX() - previousTranslateX;
                startY = ev.getY() - previousTranslateY;

                paths.clear();
                times.clear();
                prevXMove = ev.getX();
                xDown = prevXMove;
                timeDown = System.currentTimeMillis();
                //Save the data of the first touch
                paths.add(prevXMove);
                times.add(timeDown);
                break;
            case MotionEvent.ACTION_MOVE:
                translateX = ev.getX() - startX;
                translateY = ev.getY() - startY;
                float currX = ev.getX();
                float currY = ev.getY();
                distance = Math.sqrt(Math.pow(currX - (startX + previousTranslateX), 2) +
                        Math.pow(currY - (startY + previousTranslateY), 2));
                float currentXMove = ev.getX();
                paths.add(currentXMove);
                times.add(System.currentTimeMillis());

                //When long touch, reset data
                if (prevXMove == currentXMove) {
                    xDown = currentXMove;
                    timeDown = System.currentTimeMillis();
                    paths.clear();
                    times.clear();
                }
                isMoveToRight = prevXMove <= currentXMove;
                if (ev.getPointerCount() == 1) {
                    //Check if the last column at right move to limit
                    if (!isMoveToRight && offsetX == 0) {
                        return true;
                    }
                    //Check if the last column at left move to limit
                    if (isMoveToRight && offsetX <= lefts[mDistanceAs.size() - 1] - getPaddingLeft() - 100) {
                        return true;
                    }
                    //Update offsetX
                    offsetX += prevXMove - currentXMove;

                    //If move to left and and the last column exceed the limit, must block it
                    if (!isMoveToRight && offsetX > 0) {
                        offsetX = 0;
                    }
                    //If move to right and and the first column exceed the limit, must block it
                    if (isMoveToRight && offsetX <= lefts[mDistanceAs.size() - 1] - getPaddingLeft() - 100) {
                        offsetX = lefts[mDistanceAs.size() - 1] - getPaddingLeft() - 100;
                    }
                }
                //Update latest x coordinate
                prevXMove = currentXMove;
                invalidate();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                mode = ZOOM;
                break;
            case MotionEvent.ACTION_UP:
                mode = 0;
                dragged = false;
                previousTranslateX = translateX;
                previousTranslateY = translateY;
                //Find the latest corner, 1 2 3 4 5 6 5 4 3 2 1, corner is 6
                int size = paths.size();
                for (int i = size - 1; i > 0; i--) {
                    if ((isMoveToRight && paths.get(i - 1) > paths.get(i)) || (!isMoveToRight && paths.get(i - 1) < paths.get(i))) {
                        xDown = paths.get(i);
                        timeDown = times.get(i);
                        break;
                    }
                    if (i - 1 == 0) {
                        xDown = paths.get(0);
                        timeDown = times.get(0);
                    }
                }
                //Check if the last column at left move to limit
                if (isMoveToRight && offsetX <= lefts[mDistanceAs.size() - 1] - getPaddingLeft() - 100) {
                    return true;
                }
                //Check if the last column at right move to limit
                if (!isMoveToRight && offsetX == 0) {
                    return true;
                }

                //Calculate the move speed to handle scroll after fling
                long time = System.currentTimeMillis() - timeDown;
                final float s = ev.getX() - xDown;
                if (time > 0) {
                    v = Math.abs(s / time);
                    v = v * 50 / 3F;
                    if (v > 0) {
                        final android.os.Handler handler = new android.os.Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Check if the first column at left move to limit
                                if (isMoveToRight) {
                                    offsetX -= v;
                                    if (offsetX < lefts[mDistanceAs.size() - 1] - getPaddingLeft() - 100) {
                                        offsetX = lefts[mDistanceAs.size() - 1] - getPaddingLeft() - 100;
                                        v = 0;
                                    }
                                } else {//Check if the last column at right move to limit
                                    offsetX += v;
                                    if (offsetX > 0) {
                                        offsetX = 0;
                                        v = 0;
                                    }
                                }
                                invalidate();
                                //Scroll, scroll, scroll...
                                if (v-- > 0) {
                                    handler.postDelayed(this, 1);
                                }
                            }
                        }, 1);
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mode = DRAG;
                previousTranslateX = translateX;
                previousTranslateY = translateY;
                break;
        }
        mScaleDetector.onTouchEvent(ev);
        if ((mode == DRAG && mScaleFactor != 1f && dragged) || mode == ZOOM) {
            invalidate();
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor);
        translate(canvas);

        drawChart(canvas);

        drawRect(canvas, 0, mPaintRect);
        drawRect(canvas, mWitdhScreen - 50, mPaintRect);

        drawText(canvas, getContext().getString(R.string.distance_km, ((int) maxLists())), 0, maxLists(), mPaintText);
        drawText(canvas, getContext().getString(R.string.day), mOxChart, -1, mPaintText);
        drawText(canvas, getContext().getString(R.string.month), mWitdhScreen - 100, -1, mPaintText);

        drawLine(canvas, 0, mPaintLine);
        drawLine(canvas, maxLists(), mPaintLine);

        drawText(canvas, getContext().getString(R.string.distance_km, ((int) 0)), 0, 0, mPaintText);

        drawLine(canvas, maxLists() / 2, mPaintLine);

        canvas.restore();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawChart(Canvas canvas) {
        float leftRect = getWidth() - getPaddingRight() - columnMarginHorizontal - columnWidth - 100 - offsetX;
        for (int index = 0; index < mDistanceAs.size(); index++) {
            canvas.drawRoundRect(leftRect - 100 * index, getTop(mDistanceAs.get(index)), leftRect - 100 * index + columnWidth, mHeightScreen / 2 - getPaddingBottom(), columnCornerRadius, columnCornerRadius, mPaintColumn);
            canvas.drawRoundRect(leftRect - 100 * index - columnWidth - 5, getTop(mDistanceBs.get(index)), leftRect - 100 * index - 5, mHeightScreen / 2 - getPaddingBottom(), columnCornerRadius, columnCornerRadius, mPaintColumn1);
            canvas.drawRoundRect(leftRect - 100 * index - columnWidth * 2 - 10, getTop(mDistanceCs.get(index)), leftRect - 100 * index - columnWidth - 10, mHeightScreen / 2 - getPaddingBottom(), columnCornerRadius, columnCornerRadius, mPaintColumn2);
            //Save the first left of all columns
            if (offsetX == 0) {
                lefts[index] = leftRect;
            }
            leftRect -= columnWidth + columnMarginHorizontal;
        }
    }

    private float getTop(int value) {
        return mHeightScreen / 2 - value * 50;
    }

    private void translate(Canvas canvas) {
        if ((translateX * -1) < 0) {
            translateX = 0;
        } else if ((translateX * -1) > (mScaleFactor - 1) * getWidth()) {
            translateX = (1 - mScaleFactor) * getWidth();
        }
        if (translateY * -1 < 0) {
            translateY = 0;
        } else if ((translateY * -1) > (mScaleFactor - 1) * getHeight()) {
            translateY = (1 - mScaleFactor) * getHeight();
        }
        canvas.translate(translateX / mScaleFactor, translateY / mScaleFactor);
    }

    private void drawText(Canvas canvas, String str, float x, float y, Paint paint) {
        canvas.drawText(str, x, mHeightScreen / 2 - y * 50, paint);
    }

    private void drawLine(Canvas canvas, float y, Paint paint) {
        canvas.drawLine(mOxChart, mHeightScreen / 2 - y * 50, mWitdhScreen - 50, mHeightScreen / 2 - y * 50, paint);
    }

    private void drawRect(Canvas canvas, float left, Paint paint) {
        canvas.drawRect(left, mHeightScreen / 2 - maxLists() * 50, left + 130, mHeightScreen / 2, paint);
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
}
