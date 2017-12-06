package vn.asiantech.internship.ui.drawer.layout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;

public class DrawerLayoutActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,EventAdapter.OnItemClickListener {
    private RecyclerView mRecyclerViewDrawerLayout;
    private EventAdapter mAdapter;
    private List<Event> mEventList=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawerlayout);
        initView();
        initData();
        initAdapter();

    }

    private void initAdapter() {
    }

    private void initData() {
//        mEventList.add(new Event(R.drawable.ic_ibrahimovic),(getString(R.string.text_inbox)));
    }

    private void initView() {
        mRecyclerViewDrawerLayout = findViewById(R.id.navigationRecyclerView);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    public void onOfflineClick(int position) {
        mEventList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
}
