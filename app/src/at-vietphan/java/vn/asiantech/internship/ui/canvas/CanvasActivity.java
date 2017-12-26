package vn.asiantech.internship.ui.canvas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import vn.asiantech.internship.R;

/**
 * Created by vietphan on 25/12/2017.
 * Class CanvasActivity
 */

public class CanvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        CustomView customView = new CustomView(this);
        ((LinearLayout) findViewById(R.id.llCanvas)).addView(customView);
    }
}
