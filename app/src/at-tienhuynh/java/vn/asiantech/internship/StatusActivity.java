package vn.asiantech.internship;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.adapters.StatusAdapter;
import vn.asiantech.internship.models.Status;

public class StatusActivity extends AppCompatActivity implements StatusAdapter.OnItemClickListener {
    private RecyclerView mRecyclerViewStatus;
    private List<Status> mStatusList = new ArrayList<>();
    private int mCountLike;
    private StatusAdapter mStatusAdapter;

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
        mStatusList.add(new Status("Im Tien", "Im 20 years old", 1));
        mStatusList.add(new Status("Im Tien", "Im 20 years old", 1));
        mStatusList.add(new Status("Im Tien", "Im 20 years old", 1));
        mStatusList.add(new Status("Im Tien", "Im 20 years old", 1));
    }

    private void initAdapter() {
        mStatusAdapter = new StatusAdapter(mStatusList, this);
        mRecyclerViewStatus.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewStatus.setAdapter(mStatusAdapter);
    }

    @Override
    public void onLikeClick(int position) {
        mCountLike++;
        mStatusList.get(position).setNumlike(mCountLike);
        mStatusAdapter.notifyItemChanged(position);
    }

    @Override
    public void onDisLikeClick(int position) {
        mCountLike--;
        mStatusList.get(position).setNumlike(mCountLike);
        mStatusAdapter.notifyItemChanged(position);
    }
}

