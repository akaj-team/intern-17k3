package vn.asiantech.internship;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.adapters.StatusAdapter;
import vn.asiantech.internship.models.Status;

public class StatusActivity extends AppCompatActivity implements StatusAdapter.OnItemClickListener {
    private RecyclerView mRecyclerViewStatus;
    private List<Status> mStatusList = new ArrayList<>();
    private static final int NUM_LIKE = 1;
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
        mStatusList.add(new Status("Im Tien", "Im 20 years old,Im 20 years old,Im 20 years old,Im 20 years old", 0));
        mStatusList.add(new Status("Im Tung", "Im very handsome, Im the best, Im the best, Im the best, Im the best", 1));
        mStatusList.add(new Status("Im Anh", "Da Nang is very beautiful, i love Da Nang,i love Da Nang,i love Da Nang,i love Da Nang", 5));
        mStatusList.add(new Status("Im Cuong", "What the fu** man???,What the fu** man???,What the fu** man???,What the fu** man???", 10));
    }

    private void initAdapter() {
        mStatusAdapter = new StatusAdapter(mStatusList, this);
        mRecyclerViewStatus.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewStatus.setAdapter(mStatusAdapter);
    }

    @Override
    public void onLikeClick(int position) {
        mStatusList.get(position).setNumlike(mStatusList.get(position).getNumlike() + NUM_LIKE);
        mStatusAdapter.notifyItemChanged(position);
    }

    @Override
    public void onDisLikeClick(int position) {
        mStatusList.get(position).setNumlike(mStatusList.get(position).getNumlike() - NUM_LIKE);
        mStatusAdapter.notifyItemChanged(position);
    }
}

