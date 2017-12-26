package vn.asiantech.internship.canvas;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager_tablayout.ScreenUtil;

/**
 * Created by anh.quach on 12/25/17.
 * Custom  Chart
 */
public class ChartView extends View {
    private Paint mPaint;
    private int mWidth;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;
    private List<Integer> mDistanceAs = new ArrayList<>();
    private List<Integer> mDistanceBs = new ArrayList<>();
    private List<Integer> mDistanceCs = new ArrayList<>();

    public ChartView(Context context) {
        this(context, null);
    }

    public ChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.ChartView, 0, 0
        );
        try {
            mWidth = a.getInteger(R.styleable.ChartView_line_width, 100);
        } finally {
            a.recycle();
        }
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        initDistanceA();
        initDistanceB();
        initDistanceC();
        init();
    }

    private void initDistanceA() {
        mDistanceAs.add(1);
        mDistanceAs.add(7);
        mDistanceAs.add(5);
        mDistanceAs.add(2);
        mDistanceAs.add(9);
        mDistanceAs.add(6);
        mDistanceAs.add(5);
    }

    private void initDistanceB() {
        mDistanceBs.add(1);
        mDistanceBs.add(3);
        mDistanceBs.add(5);
        mDistanceBs.add(2);
        mDistanceBs.add(2);
        mDistanceBs.add(6);
        mDistanceBs.add(5);
    }

    private void initDistanceC() {
        mDistanceCs.add(1);
        mDistanceCs.add(8);
        mDistanceCs.add(5);
        mDistanceCs.add(2);
        mDistanceCs.add(11);
        mDistanceCs.add(6);
        mDistanceCs.add(5);
    }

    private float maxLists() {
        int max1 = Collections.max(mDistanceAs);
        int max2 = Collections.max(mDistanceBs);
        int max3 = Collections.max(mDistanceCs);
        float max = Math.max(Math.max(max1, max2), max3);
        return max;
    }

    private void init() {
        mPaint = new Paint();

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mScaleDetector.onTouchEvent(ev);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor);
        mPaint.setColor(getResources().getColor(R.color.colorGrayDark));
        mPaint.setTextSize(40);
        mPaint.setStrokeWidth(1f);
        canvas.drawText(maxLists() + " km", 0, ScreenUtil.getHeightScreen(getContext()) / 2 - maxLists() * 50 + 15, mPaint);
        canvas.drawLine(150, ScreenUtil.getHeightScreen(getContext()) / 2, ScreenUtil.getWidthScreen(getContext()), ScreenUtil.getHeightScreen(getContext()) / 2, mPaint);
        canvas.drawLine(150, ScreenUtil.getHeightScreen(getContext()) / 2 - maxLists() * 50, ScreenUtil.getWidthScreen(getContext()), ScreenUtil.getHeightScreen(getContext()) / 2 - maxLists() * 50, mPaint);
        canvas.drawText("0.3 km", 0, ScreenUtil.getHeightScreen(getContext()) / 2, mPaint);
        canvas.drawLine(150, ScreenUtil.getHeightScreen(getContext()) / 2 - (maxLists() / 2) * 50, ScreenUtil.getWidthScreen(getContext()), ScreenUtil.getHeightScreen(getContext()) / 2 - (maxLists() / 2) * 50, mPaint);
        for (int i = 0; i < 7; i++) {
            mPaint.setColor(getResources().getColor(R.color.colorPurple800));
            mPaint.setStrokeWidth((float) mWidth);
            canvas.drawLine(ScreenUtil.getWidthScreen(getContext()) / 2 + (i * mWidth * 7 + mWidth) - ((mWidth * 42) / 2),
                    ScreenUtil.getHeightScreen(getContext()) / 2 - mDistanceAs.get(i) * 50,
                    ScreenUtil.getWidthScreen(getContext()) / 2 + (i * mWidth * 7 + mWidth) - ((mWidth * 42) / 2),
                    ScreenUtil.getHeightScreen(getContext()) / 2, mPaint);
            mPaint.setColor(getResources().getColor(R.color.colorCyanA700));
            mPaint.setAntiAlias(true);
            canvas.drawLine(ScreenUtil.getWidthScreen(getContext()) / 2 + (i * mWidth * 7 + mWidth * 2 + 5) - ((mWidth * 42) / 2),
                    ScreenUtil.getHeightScreen(getContext()) / 2 - mDistanceBs.get(i) * 50,
                    ScreenUtil.getWidthScreen(getContext()) / 2 + (i * mWidth * 7 + mWidth * 2 + 5) - ((mWidth * 42) / 2),
                    ScreenUtil.getHeightScreen(getContext()) / 2, mPaint);
            mPaint.setColor(getResources().getColor(R.color.colorOrange500));
            mPaint.setAntiAlias(true);
            canvas.drawLine(ScreenUtil.getWidthScreen(getContext()) / 2 + (i * mWidth * 7 + mWidth * 3 + 10) - ((mWidth * 42) / 2),
                    ScreenUtil.getHeightScreen(getContext()) / 2 - mDistanceCs.get(i) * 50,
                    ScreenUtil.getWidthScreen(getContext()) / 2 + (i * mWidth * 7 + mWidth * 3 + 10) - ((mWidth * 42) / 2),
                    ScreenUtil.getHeightScreen(getContext()) / 2, mPaint);
        }
        canvas.restore();
    }
    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
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
