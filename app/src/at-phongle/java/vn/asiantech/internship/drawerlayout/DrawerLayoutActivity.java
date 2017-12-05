package vn.asiantech.internship.drawerlayout;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Option;
import vn.asiantech.internship.models.User;

public class DrawerLayoutActivity extends AppCompatActivity implements ObjectLeftbarAdapter.OnItemClickListener{
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private ObjectLeftbarAdapter mAdapter;
    private List<Object> mObjectList = new ArrayList<>();
    private RecyclerView mRecyclerViewLeftbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        mDrawerLayout = findViewById(R.id.drawerLayout);
        mRecyclerViewLeftbar = findViewById(R.id.recyclerViewMenu);
        initDrawer();
        initData();
        initAdapter();

    }
    private void initDrawer() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, R.string.open, R.string.close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
    }
    private void initAdapter(){
        mAdapter = new ObjectLeftbarAdapter(mObjectList,this);
        mRecyclerViewLeftbar.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewLeftbar.setAdapter(mAdapter);
    }
    private void initData(){
        mObjectList.add(new User(R.drawable.img_avatar, "CristianoRonaldo@gmail.com"));
        mObjectList.add(new Option(R.drawable.ic_inbox, "Inbox"));
        mObjectList.add(new Option(R.drawable.ic_outbox, "Outbox"));
        mObjectList.add(new Option(R.drawable.ic_trash, "Trash"));
        mObjectList.add(new Option(R.drawable.ic_spam, "Spam"));

    }
}
