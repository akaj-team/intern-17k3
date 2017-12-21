package vn.asiantech.internship.asynchronous;

import android.os.Bundle;
import android.os.CountDownTimer;
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
        mStrs.add("0");
        mStrs.add("1");
        mStrs.add("2");
        initAdapter();
        new CountDownTimer(180000, 5000) {
            int count = 0;

            @Override
            public void onTick(long longMilisecond) {
                ++count;
                if (count % 2 == 0) {
                    mStrs.add("3");
                    mStrs.add("4");
                    mRecyclerViewAdapter.notifyDataSetChanged();
                }
                if (count % 3 == 0) {
                    mStrs.remove(mStrs.size() / 2);
                    mRecyclerViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void initViews() {
        mRecyclerViewCountDownTimer = findViewById(R.id.recyclerViewCountDownTimer);
    }

    private void initAdapter() {
        mRecyclerViewAdapter = new RecyclerViewAdapter(mStrs);
        mRecyclerViewCountDownTimer.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewCountDownTimer.setAdapter(mRecyclerViewAdapter);
    }
}
