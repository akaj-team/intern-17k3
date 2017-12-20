package vn.asiantech.internship.ui.thread_handler.ui.countdowntimer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.CountDownItem;
import vn.asiantech.internship.ui.thread_handler.adapter.CountDownAdapter;

public class CountDownActivity extends AppCompatActivity {
    private List<CountDownItem> mCountDownTimers = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private CountDownAdapter mCountDownAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        initView();
        initAdapter();
        initData();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recyclerViewCountDown);
    }

    private void initAdapter() {
        mCountDownAdapter = new CountDownAdapter(mCountDownTimers);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mCountDownAdapter);
    }

    private void initData() {
        mCountDownTimers.add(new CountDownItem("TungKute"));
        mCountDownTimers.add(new CountDownItem("TungKute2"));
        mCountDownTimers.add(new CountDownItem("TungKute3"));
        mCountDownTimers.add(new CountDownItem("TungKute4"));
        mCountDownTimers.add(new CountDownItem("TungKute"));
    }
}
