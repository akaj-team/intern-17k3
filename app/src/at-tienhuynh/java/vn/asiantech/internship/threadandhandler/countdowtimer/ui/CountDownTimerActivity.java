package vn.asiantech.internship.threadandhandler.countdowtimer.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.threadandhandler.countdowtimer.adapters.ListCountDownTimerAdapter;
import vn.asiantech.internship.threadandhandler.countdowtimer.models.Item;

public class CountDownTimerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewCountDownTimer;
    private List<Item> mItemList = new ArrayList<>();
    private TextView mTvCountDown;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_timer);
        initData();
        initViews();
        initAdapters();
    }

    /**
     * Init Data
     */
    private void initData() {
        mItemList.add(new Item("aaaaa"));
        mItemList.add(new Item("aaaaa"));
        mItemList.add(new Item("aaaaa"));
        mItemList.add(new Item("aaaaa"));
        mItemList.add(new Item("aaaaa"));
    }

    /**
     * Init Views
     */
    private void initViews() {
        mRecyclerViewCountDownTimer = findViewById(R.id.recyclerViewCountDownTimer);
        mTvCountDown = findViewById(R.id.tvCountDown);
    }

    /**
     * Init Adapter
     */
    private void initAdapters() {
        ListCountDownTimerAdapter listCountDownTimerAdapter = new ListCountDownTimerAdapter(mItemList);
        mRecyclerViewCountDownTimer.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewCountDownTimer.setAdapter(listCountDownTimerAdapter);
    }

    /**
     * This is count down timer
     */
    CountDownTimer countDownTimer = new CountDownTimer(18000, 1000) {
        @Override
        public void onTick(long l) {
        }

        @Override
        public void onFinish() {

        }
    }.start();
}
