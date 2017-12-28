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
    private static final int MILLISINFUTURE = 180000;
    private static final int COUNTDOWNINTERVAL = 1000;
    private RecyclerView mRecyclerViewCountDownTimer;
    private List<String> mStrs = new ArrayList<>();
    private RecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_count_down_timer);
        initViews();
        mStrs.add("0");
        initAdapter();
        new CountDownTimer(MILLISINFUTURE, COUNTDOWNINTERVAL) {
            int countadd = 0;
            int countdelete = 0;

            @Override
            public void onTick(long longMilisecond) {
                ++countadd;
                ++countdelete;
                if (countadd == 10) {
                    mStrs.add("1");
                    mStrs.add("2");
                    mRecyclerViewAdapter.notifyDataSetChanged();
                    countadd = 0;
                }
                if (countdelete == 15) {
                    mStrs.remove(mStrs.get(mStrs.size() / 2));
                    mRecyclerViewAdapter.notifyDataSetChanged();
                    countdelete = 0;
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
