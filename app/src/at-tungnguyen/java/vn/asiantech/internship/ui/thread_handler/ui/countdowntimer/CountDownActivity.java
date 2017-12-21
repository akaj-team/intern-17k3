package vn.asiantech.internship.ui.thread_handler.ui.countdowntimer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.CountDownItem;
import vn.asiantech.internship.ui.thread_handler.adapter.CountDownAdapter;

public class CountDownActivity extends AppCompatActivity {
    private List<CountDownItem> mCountDownTimers = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private CountDownAdapter mCountDownAdapter;
    private TextView mTvCountDown;
    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        initView();
        initAdapter();
        initData();
        initCountDown();
        addItem();
        deleteItem();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recyclerViewCountDown);
        mTvCountDown = findViewById(R.id.tvCountDownTimer);
    }

    private void initAdapter() {
        mCountDownAdapter = new CountDownAdapter(mCountDownTimers);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mCountDownAdapter);
    }

    private void initData() {
        mCountDownTimers.add(new CountDownItem(String.valueOf(R.string.tv_countdown_timer)));
        mCountDownTimers.add(new CountDownItem(String.valueOf(R.string.tv_countdown_timer)));
        mCountDownTimers.add(new CountDownItem(String.valueOf(R.string.tv_countdown_timer)));
    }

    private void initCountDown() {
        new CountDownTimer(180000, 1000) {
            @Override
            public void onTick(long l) {
                mCount = (int) (l / 1000);
                mTvCountDown.setText(String.valueOf(l / 1000));
            }

            @Override
            public void onFinish() {
                mCount = 0;
                mTvCountDown.setText(String.valueOf(mCount));
            }
        }.start();
    }

    private void addItem() {
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                //No-opp
            }

            @Override
            public void onFinish() {
                if (mCount > 0) {
                    mCountDownTimers.add(new CountDownItem(String.valueOf(R.string.tv_countdown_timer)));
                    mCountDownTimers.add(new CountDownItem(String.valueOf(R.string.tv_countdown_timer)));
                    mCountDownAdapter.notifyDataSetChanged();
                    addItem();
                }
            }
        }.start();
    }

    private void deleteItem() {
        new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long l) {
                //No-opp
            }

            @Override
            public void onFinish() {
                if (mCount > 0) {
                    mCountDownTimers.remove(mCountDownTimers.size()/ 2);
                    mCountDownAdapter.notifyDataSetChanged();
                    deleteItem();
                }
            }
        }.start();
    }
}
