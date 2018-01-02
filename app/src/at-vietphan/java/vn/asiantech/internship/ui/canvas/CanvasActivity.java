package vn.asiantech.internship.ui.canvas;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;

import vn.asiantech.internship.R;

/**
 * Created by vietphan on 25/12/2017.
 * Class CanvasActivity
 */
public class CanvasActivity extends AppCompatActivity {
    private CustomView customView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        customView = findViewById(R.id.customView);
        final GestureDetector myGesture = new GestureDetector(this, new MyOnGestureListener());

        customView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return myGesture.onTouchEvent(event);
            }
        });

        customView.setClickable(true);
    }

    class MyOnGestureListener implements OnGestureListener {

        int MIN_DIST = 100;

        @Override
        public boolean onDown(MotionEvent arg0) {
            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            float e1X = e1.getX();
            float e1Y = e1.getY();
            float e2X = e2.getX();
            float e2Y = e2.getY();
            float distX = e2X - e1X;
            float distY = e2Y - e1Y;

            //Get the Y OFfset
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int offsetY = displayMetrics.heightPixels - customView.getMeasuredHeight();

            int[] location = new int[2];
            customView.getLocationOnScreen(location);
            float orgX = location[0];
            float orgY = location[1] - offsetY;

            float stopX = orgX + distX;
            float stopY = orgY + distY;

            if (distX > MIN_DIST) {
                //Fling Right
                ObjectAnimator flingAnimator = ObjectAnimator.ofFloat(customView, "translationX", orgX, stopX);
                flingAnimator.setDuration(1000);
                flingAnimator.start();
            } else if (distX < -MIN_DIST) {
                //Fling Left
                ObjectAnimator flingAnimator = ObjectAnimator.ofFloat(customView, "translationX", orgX, stopX);
                flingAnimator.setDuration(1000);
                flingAnimator.start();
            } else if (distY > MIN_DIST) {
                //Fling Down
                ObjectAnimator flingAnimator = ObjectAnimator.ofFloat(customView, "translationY", orgY, stopY);
                flingAnimator.setDuration(1000);
                flingAnimator.start();
            } else if (distY < -MIN_DIST) {
                //Fling Up
                ObjectAnimator flingAnimator = ObjectAnimator.ofFloat(customView, "translationY", orgY, stopY);
                flingAnimator.setDuration(1000);
                flingAnimator.start();
            }

            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            // No-op
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }
    }
}
