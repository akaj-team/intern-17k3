package vn.asiantech.internship.ui.canvas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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
    }
}
