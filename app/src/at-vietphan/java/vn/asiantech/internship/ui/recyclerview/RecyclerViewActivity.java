package vn.asiantech.internship.ui.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.recyclerview.models.Status;

public class RecyclerViewActivity extends AppCompatActivity implements StatusAdapter.OnItemClickListener {
    private RecyclerView mRecyclerViewStatus;
    private List<Status> mStatusList = new ArrayList<>();
    private StatusAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initViews();
        initData();
        initAdapter();
    }

    private void initViews() {
        mRecyclerViewStatus = findViewById(R.id.recyclerViewStatus);
    }

    private void initData() {
        mStatusList.add(new Status("m_d 1", "Content Content Content Content", 1));
        mStatusList.add(new Status("m_d 2", "Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content", -2));
        mStatusList.add(new Status("m_d 3", "Content Content Content Content", 3));
        mStatusList.add(new Status("m_d 4", "Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content", 4));
        mStatusList.add(new Status("m_d 5", "Content Content Content Content", -5));
        mStatusList.add(new Status("m_d 6", "Content Content Content Content", 6));
        mStatusList.add(new Status("m_d 7", "Content Content Content Content Content Content ContentContent Content Content Content Content Content Content Content Content Content", -7));
        mStatusList.add(new Status("m_d 8", "Content Content Content Content", 8));
        mStatusList.add(new Status("m_d 9", "Content Content Content Content", 9));
        mStatusList.add(new Status("m_d 10", "Content Content Content Content Content Content Content Content Content Content Content Content Content Content Content", 10));
    }

    private void initAdapter() {
        mAdapter = new StatusAdapter(mStatusList, this);
        mRecyclerViewStatus.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewStatus.setAdapter(mAdapter);
    }

    @Override
    public void onLikeClick(int position) {
        mStatusList.get(position).setCountLike(mStatusList.get(position).getCountLike() + 1);
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void onDislikeClick(int position) {
        mStatusList.get(position).setCountLike(mStatusList.get(position).getCountLike() - 1);
        mAdapter.notifyItemChanged(position);
    }
}
