package vn.asiantech.internship.canvas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import vn.asiantech.internship.R;

/**
 * Created by anh.quach on 12/25/17.
 * Activity for Chart
 */
public class ChartActivity extends AppCompatActivity {
    ChartView chartView;
    public ChartActivity() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        chartView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }
}
