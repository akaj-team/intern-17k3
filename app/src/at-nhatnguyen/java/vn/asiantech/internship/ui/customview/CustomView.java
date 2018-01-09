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
    private Paint paintText = new Paint();
    private Paint paintRect = new Paint();
    private Paint paintLine = new Paint();
    private Paint paintColumn = new Paint();
    private Paint paintColumn1 = new Paint();
    private Paint paintColumn2 = new Paint();
    private int columnWidth = getResources().getDimensionPixelSize(R.dimen.column_width);
    private int columnCornerRadius = getResources().getDimensionPixelSize(R.dimen.column_corner_radius);
    private int columnMarginHorizontal = getResources().getDimensionPixelSize(R.dimen.columns_margin_horizontal);
    private List<Integer> data = new ArrayList<>(Arrays.asList(11, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9, 2, 4, 5, 6, 7, 8, 9));
    private int max = Collections.max(data);
    private int sizeData = data.size();
    //To save the first left of all columns
    private float lefts[] = new float[sizeData];
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
            columnWidth = typedArray.getInteger(R.styleable.CustomView_stroke_with, 40);
        } finally {
            typedArray.recycle();
        }
    }

    /**
     * Init Paint
     */
    private void init() {
        paintText.setColor(Color.GRAY);
        paintText.setTextSize(getResources().getDimension(R.dimen.tv_value_chart));
        paintLine.setColor(Color.GRAY);
        paintLine.setStrokeWidth(5);
        paintLine.setAntiAlias(true);
        paintColumn.setColor(Color.BLUE);
        paintColumn.setAntiAlias(true);
        paintColumn1.setColor(Color.RED);
        paintColumn1.setAntiAlias(true);
        paintColumn2.setColor(Color.YELLOW);
        paintColumn2.setAntiAlias(true);
        paintRect.setColor(Color.WHITE);
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
        canvas.drawLine(paintText.measureText(String.valueOf(max).concat(getResources()
                        .getString(R.string.km))),
                getTop(max), getWidth(),
                getTop(max), paintLine);
        //Draw End Line
        canvas.drawLine(0, getHeight() - getPaddingBottom(),
                getWidth(), getHeight() - getPaddingBottom(), paintLine);
        //Draw Middle Line
        canvas.drawLine(0,
                getTop(max / 2), getWidth(),
                getTop(max / 2), paintLine);
    }

    /**
     * Draw Chart
     *
     * @param canvas canvas
     */
    private void drawChart(Canvas canvas) {
        float leftRect = getWidth() - getPaddingRight() - columnMarginHorizontal - columnWidth - 100 - offsetX;
        for (int index = 0; index < sizeData; index++) {
            canvas.drawRoundRect(new RectF(leftRect - 100 * index, getTop(data.get(index)), leftRect - 100 * index + columnWidth, getHeight() - getPaddingBottom()), columnCornerRadius, columnCornerRadius, paintColumn);
            canvas.drawRoundRect(new RectF(leftRect - 100 * index - 42, getTop(data.get(index)), leftRect - 100 * index - 42 + columnWidth, getHeight() - getPaddingBottom()), columnCornerRadius, columnCornerRadius, paintColumn1);
            canvas.drawRoundRect(new RectF(leftRect - 100 * index - 84, getTop(data.get(index)), leftRect - 100 * index - 84 + columnWidth, getHeight() - getPaddingBottom()), columnCornerRadius, columnCornerRadius, paintColumn2);
//            canvas.drawText(convertDay(index), leftRect - 100 * index - 42 + columnWidth,
//                    getHeight() - getPaddingBottom() + 20, paintText);
            //Save the first left of all columns
            if (offsetX == 0) {
                lefts[index] = leftRect;
            }
            leftRect -= columnWidth + columnMarginHorizontal;
        }
    }

    /**
     * Draw Rect
     *
     * @param canvas canvas
     */
    private void drawRect(Canvas canvas) {
        canvas.drawRect(0, 0, getPaddingLeft() + 100 - columnMarginHorizontal, getHeight(), paintRect);
        canvas.drawRect(getWidth() - getPaddingRight() - 100 + columnMarginHorizontal, 0, getWidth(), getHeight(), paintRect);
    }

    /**
     * Draw Text
     *
     * @param canvas canvas
     */
    private void drawText(Canvas canvas) {
        //Draw Text Max Values
        canvas.drawText(String.valueOf(max).concat(getResources().getString(R.string.km)), 0, 50, paintText);
        //Draw Text Max Values
        canvas.drawText(String.valueOf(max / 2).concat(getResources().getString(R.string.km)), 0, getHeight() / 2 - max, paintText);
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
            //Reset data
            paths.clear();
            times.clear();
            prevXMove = ev.getX();
            xDown = prevXMove;
            timeDown = System.currentTimeMillis();

            //Save the data of the first touch
            paths.add(prevXMove);
            times.add(timeDown);
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
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
                if (isMoveToRight && offsetX <= lefts[sizeData - 1] - getPaddingLeft() - 100) {
                    return true;
                }
                //Update offsetX
                offsetX += prevXMove - currentXMove;

                //If move to left and and the last column exceed the limit, must block it
                if (!isMoveToRight && offsetX > 0) {
                    offsetX = 0;
                }
                //If move to right and and the first column exceed the limit, must block it
                if (isMoveToRight && offsetX <= lefts[sizeData - 1] - getPaddingLeft() - 100) {
                    offsetX = lefts[sizeData - 1] - getPaddingLeft() - 100;
                }
            }
            //Update latest x coordinate
            prevXMove = currentXMove;
            //Re-draw
            invalidate();
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {
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
            if (isMoveToRight && offsetX <= lefts[sizeData - 1] - getPaddingLeft() - 100) {
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
                                if (offsetX < lefts[sizeData - 1] - getPaddingLeft() - 100) {
                                    offsetX = lefts[sizeData - 1] - getPaddingLeft() - 100;
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
        }
        return true;
    }
}
