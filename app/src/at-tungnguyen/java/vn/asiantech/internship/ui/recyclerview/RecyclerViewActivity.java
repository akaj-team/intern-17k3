package vn.asiantech.internship.ui.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;

public class RecyclerViewActivity extends Activity implements StatusAdapter.OnItemClickListener {
    private List<Status> mStatusList = new ArrayList<>();
    private StatusAdapter mAdapter;
    private RecyclerView mRecyclerViewStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        initViews();
        initAdapter();
        initData();
    }

    private void initViews() {
        mRecyclerViewStatus = findViewById(R.id.recyclerView);
    }

    private void initAdapter() {
        mAdapter = new StatusAdapter(mStatusList, this);
        mRecyclerViewStatus.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewStatus.setAdapter(mAdapter);
    }

    private void initData() {
        mStatusList.add(new Status("Tung1", "Messi messi", 0));
        mStatusList.add(new Status("Tung2", "messi messi messi", 0));
        mStatusList.add(new Status("Tung3", "messi messi messi messi messi messi", 0));
        mStatusList.add(new Status("Tung4", "Messi messi Messi messi", 0));
        mStatusList.add(new Status("Tung5", "messi messi messi Messi messiMessi messi", 0));
        mStatusList.add(new Status("Tung6", "Messi messi messi messi messi messi Messi messi Messi messi Messi messi  messi messi messi messi messi messi Messi messi Messi messi Messi messi Messi messi", 0));
    }

    @Override
    public void onLikeClick(int position) {
        mStatusList.get(position).setCount(mStatusList.get(position).getCount() + 1);
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void onDisLikeClick(int position) {
        mStatusList.get(position).setCount(mStatusList.get(position).getCount() - 1);
        mAdapter.notifyItemChanged(position);
    }
}
