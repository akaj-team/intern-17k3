package vn.asiantech.internship.ui.canvas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vn.asiantech.internship.R;

/**
 * Created by vietphan on 25/12/2017.
 * Class CustomView
 */
public class CustomView extends View {
    private static final int WIDTH_RECT_PERSON = 18;
    private static final int STROKE_WIDTH_LINE_DISTANCE = 2;
    private static final int WIDTH_BOX = 100;
    private static final int START_DISTANCE_LINE = 100;
    private static final int START_DISTANCE_TEXT = 10;
    private static final int TEXT_BETWEEN_LINE = 10;
    private static final int START_X_PERSON_A = 100;
    private static final int START_X_PERSON_B = 120;
    private static final int START_X_PERSON_C = 140;
    private Paint mPaintA;
    private Paint mPaintB;
    private Paint mPaintC;
    private Paint mPaintMonth;
    private Paint mPaintDistance;
    private Paint mPaintLineDistance;
    private Paint mPaintBox;
    private int[] mPersonA;
    private int[] mPersonB;
    private int[] mPersonC;
    private int mStartXPersonA;
    private int mStartXPersonB;
    private int mStartXPersonC;
    private float mMoveX;
    private float mTouchX;
    private long mTimeStart;
    private float mSpeed;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;
    private Handler mHandler = new Handler();
    private Runnable mRunnable;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        initViews();
        initData();
        mHandler = new Handler();
    }

    private void initViews() {
        //  mPaintA
        mPaintA = new Paint();
        mPaintA.setColor(Color.RED);
        mPaintA.setAntiAlias(true);
        //  mPaintB
        mPaintB = new Paint();
        mPaintB.setColor(Color.GREEN);
        //  mPaintC
        mPaintC = new Paint();
        mPaintC.setColor(Color.BLUE);
        //  mPaintMonth
        mPaintMonth = new Paint();
        mPaintMonth.setColor(Color.DKGRAY);
        mPaintMonth.setTextSize(getResources().getDimensionPixelSize(R.dimen.text_size_chart));
        //  mPaintDistance
        mPaintDistance = new Paint();
        mPaintDistance.setColor(Color.DKGRAY);
        mPaintDistance.setTextSize(getResources().getDimensionPixelSize(R.dimen.text_size_chart));
        //  mPaintLineDistance
        mPaintLineDistance = new Paint();
        mPaintLineDistance.setStrokeWidth(STROKE_WIDTH_LINE_DISTANCE);
        mPaintLineDistance.setColor(Color.LTGRAY);
        //  mPaintBox
        mPaintBox = new Paint();
        mPaintBox.setColor(Color.WHITE);
    }

    private void initData() {
        mStartXPersonA = START_X_PERSON_A;
        mStartXPersonB = START_X_PERSON_B;
        mStartXPersonC = START_X_PERSON_C;
        mPersonA = getResources().getIntArray(R.array.person_one);
        mPersonB = getResources().getIntArray(R.array.person_two);
        mPersonC = getResources().getIntArray(R.array.person_three);
    }

    private float setStartX(float distancePerson, float mStartXPerson) {
        return mStartXPerson + distancePerson * 100;
    }

    private float setStartY() {
        return this.getHeight() * 3 / 4;
    }

    private float setStopY(float distancePerson) {
        return setStartY() - distancePerson * 30;
    }

    private float setStartYMonth() {
        return setStartY() + 60;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor);
        drawLineDistance(canvas);
        drawColumnPerson(canvas);
        drawBox(canvas);
        drawText(canvas);
        canvas.restore();
    }

    /**
     * Draw box for hint chart in left, right
     */
    private void drawBox(Canvas canvas) {
        canvas.drawRect(0, 0, WIDTH_BOX, getHeight(), mPaintBox);
        canvas.drawRect(getWidth() - WIDTH_BOX, 0, getWidth(), getHeight(), mPaintBox);
    }

    /**
     * Draw column person A, B, C
     */
    private void drawColumnPerson(Canvas canvas) {
        for (int i = 0; i < mPersonA.length; i++) {
            float stopXA = setStartX(i, mStartXPersonA) + WIDTH_RECT_PERSON;
            float stopXB = setStartX(i, mStartXPersonB) + WIDTH_RECT_PERSON;
            float stopXC = setStartX(i, mStartXPersonC) + WIDTH_RECT_PERSON;
            RectF rectA = new RectF(setStartX(i, mStartXPersonA), setStopY(mPersonA[i]), stopXA, setStartY());
            RectF rectB = new RectF(setStartX(i, mStartXPersonB), setStopY(mPersonB[i]), stopXB, setStartY());
            RectF rectC = new RectF(setStartX(i, mStartXPersonC), setStopY(mPersonC[i]), stopXC, setStartY());
            canvas.drawRoundRect(rectA, 5, 5, mPaintA);
            canvas.drawRoundRect(rectB, 5, 5, mPaintB);
            canvas.drawRoundRect(rectC, 5, 5, mPaintC);
        }
    }

    /**
     * Draw lineDistance max, min, between
     */
    private void drawLineDistance(Canvas canvas) {
        float maxDistance = getMaxValueDistance();
        float stopYBetween = (float) (setStopY(maxDistance / 2) + 0.5);
        canvas.drawLine(START_DISTANCE_LINE, setStopY(maxDistance), getWidth(), setStopY(maxDistance), mPaintLineDistance);
        canvas.drawLine(START_DISTANCE_LINE, stopYBetween, getWidth(), stopYBetween, mPaintLineDistance);
        canvas.drawLine(START_DISTANCE_LINE, setStartY(), getWidth(), setStartY(), mPaintLineDistance);
    }

    /**
     * Draw text month; distance min, between, max
     */
    private void drawText(Canvas canvas) {
        String jul_24 = getContext().getString(R.string.jul_24);
        String oct_9 = getContext().getString(R.string.oct_9);
        String km = getContext().getString(R.string.km);
        float maxDistance = getMaxValueDistance();
        float stopYMax = setStopY(maxDistance) + TEXT_BETWEEN_LINE;
        float stopYBetween = setStopY(maxDistance / 2) + TEXT_BETWEEN_LINE;
        float stopYMin = setStartY() + TEXT_BETWEEN_LINE;
        //  Draw
        canvas.drawText(jul_24, START_DISTANCE_LINE, setStartYMonth(), mPaintMonth);
        canvas.drawText(oct_9, getWidth() - WIDTH_BOX - 60, setStartYMonth(), mPaintMonth);
        canvas.drawText(maxDistance + km, START_DISTANCE_TEXT, stopYMax, mPaintDistance);
        canvas.drawText(maxDistance / 2 + km, START_DISTANCE_TEXT, stopYBetween, mPaintDistance);
        canvas.drawText(0 + km, START_DISTANCE_TEXT, stopYMin, mPaintDistance);
    }

    /**
     * Add int[] in List<Integer> and find max in List
     *
     * @return max
     */
    private int getMaxValueDistance() {
        List<Integer> listABC = new ArrayList<>();
        for (int i = 0; i < mPersonA.length; i++) {
            listABC.add(mPersonA[i]);
            listABC.add(mPersonB[i]);
            listABC.add(mPersonC[i]);
        }
        return Collections.max(listABC);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        mScaleDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchX = event.getX();
                mTimeStart = System.currentTimeMillis();
                break;
            case MotionEvent.ACTION_MOVE:
                boolean isStop = mTouchX >= event.getX();
                if (event.getPointerCount() == 1) {
                    mMoveX += event.getX() - mTouchX;
                    if (!isStop && mMoveX > 0.0f) {
                        mMoveX = 0.0f;
                    }
                    if (isStop && mMoveX < 0.0f && (getWidth() - 100) >= (setStartX(mPersonC.length - 1, mStartXPersonC) + WIDTH_RECT_PERSON)) {
                        mMoveX = (getWidth() - 160) - 100 * mPersonA.length;
                    }
                    updateStartXPersonLine(mMoveX);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                float mMoveDistance = event.getX() - mTouchX;
                long mTimeMove = System.currentTimeMillis() - mTimeStart;
                if (mTimeMove > 0) {
                    mSpeed = mMoveDistance / mTimeMove;
                    mSpeed = mSpeed * 10.0f;
                    mHandler.postDelayed(mRunnable = new Runnable() {
                        @Override
                        public void run() {
                            if (mSpeed > 0) {
                                mSpeed -= 0.5f;
                                mMoveX += mSpeed;
                                updateStartXPersonLine(mMoveX);
                                if (mMoveX > 0) {
                                    mSpeed = 0;
                                }
                                mHandler.postDelayed(this, 1);
                            } else if (mSpeed < 0) {
                                mSpeed += 0.5f;
                                mMoveX += mSpeed;
                                updateStartXPersonLine(mMoveX);
                                if (mSpeed > 0) {
                                    mSpeed = 0;
                                }
                                if (mMoveX < 0.0f && (getWidth() - 100) >= (setStartX(mPersonC.length - 1, mStartXPersonC) + WIDTH_RECT_PERSON)) {
                                    mMoveX = (getWidth() - 160) - 100 * mPersonA.length;
                                }
                                mHandler.postDelayed(this, 1);
                            }
                            invalidate();
                        }
                    }, 1);
                }
        }
        return true;
    }

    private void updateStartXPersonLine(float moveX) {
        mStartXPersonA = (int) (START_X_PERSON_A + moveX);
        mStartXPersonB = (int) (START_X_PERSON_B + moveX);
        mStartXPersonC = (int) (START_X_PERSON_C + moveX);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeCallbacks(mRunnable);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));
            invalidate();
            return true;
        }
    }
}
