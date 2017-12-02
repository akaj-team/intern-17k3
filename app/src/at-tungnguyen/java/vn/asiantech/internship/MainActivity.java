
package vn.asiantech.internship;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.models.Status;
import vn.asiantech.internship.models.StatusAdapter;

public class MainActivity extends Activity implements StatusAdapter.OnItemClickListener {
    private List<Status> mStatusList = new ArrayList<>();
    private StatusAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAdapter();
        initData();
        initViews();
    }

    private void initAdapter() {
        mAdapter = new StatusAdapter(mStatusList, this) {};
    }

    private void initData() {
        mStatusList.add(new Status("Tung", "Messi messi", 0));
        mStatusList.add(new Status("Tung", "messi messi messi", 0));
        mStatusList.add(new Status("Tung", "messi messi messi messi messi messi", 0));
        mStatusList.add(new Status("Tung", "Messi messi Messi messi", 0));
        mStatusList.add(new Status("Tung", "messi messi messi Messi messiMessi messi", 0));
        mStatusList.add(new Status("Tung", "Messi messi messi messi messi messi Messi messi Messi messi Messi messi  messi messi messi messi messi messi Messi messi Messi messi Messi messi Messi messi", 0));
    }

    private void initViews() {
        RecyclerView mRecyclerViewStatus = findViewById(R.id.recyclerViewPerson);
        mRecyclerViewStatus.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewStatus.setAdapter(mAdapter);
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
