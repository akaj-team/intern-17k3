package vn.asiantech.internship.asynchronous;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import vn.asiantech.internship.R;

/**
 * Created by anh.quach on 12/20/17.
 * RecyclerView
 */

public class RecyclerViewCountDownTimerActivity extends AppCompatActivity {
    private RecyclerView mRecyclerViewCountDownTimer;
    private List<String> mStrs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_count_down_timer);
        initViews();
    }
    private void initViews() {
        mRecyclerViewCountDownTimer = findViewById(R.id.recyclerViewCountDownTimer);
    }
    private void initDatas(){

    }
}
