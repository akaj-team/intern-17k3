package vn.asiantech.internship.asynchronous;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;

/**
 * Created by anh.quach on 12/20/17.
 * RecyclerView
 */

public class RecyclerViewCountDownTimerActivity extends AppCompatActivity {
    private RecyclerView mRecyclerViewCountDownTimer;
    private List<String> mStrs = new ArrayList<>();
    private RecyclerViewAdapter mRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_count_down_timer);
        initViews();
        initDatas();
        initAdapter();
    }
    private void initViews() {
        mRecyclerViewCountDownTimer = findViewById(R.id.recyclerViewCountDownTimer);
    }
    private void initDatas(){
        mStrs.add("1");
        mStrs.add("2");
    }
    private void initAdapter(){
    mRecyclerViewAdapter = new RecyclerViewAdapter(mStrs);
        mRecyclerViewCountDownTimer.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewCountDownTimer.setAdapter(mRecyclerViewAdapter);
    }
}
