package vn.asiantech.internship.ui.drawerlayout;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Issue;

public class DrawerActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerViewIssue;
    private List<Issue> mIssueList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        }
        mToolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        initViews();
        initData();
        initAdapter();
        initDrawer();
        setWidthDrawerLayout();
    }


    private void initViews() {
        mRecyclerViewIssue = findViewById(R.id.recyclerViewMultipe);
        mIssueList = new ArrayList<>();
    }

    private void initData() {
        mIssueList.add(new Issue(R.drawable.ic_login, "Login"));
        mIssueList.add(new Issue(R.drawable.ic_caculator, "Calculator"));
        mIssueList.add(new Issue(R.drawable.ic_list_24dp, "Recycler View"));
    }

    private void initAdapter() {
        IssueAdapter mAdapter = new IssueAdapter(mIssueList, this);
        mRecyclerViewIssue.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewIssue.setAdapter(mAdapter);
    }

    private void initDrawer() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close) {
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

    private void setWidthDrawerLayout() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int displayViewWidth_ = size.x;
        ViewGroup.LayoutParams params = mRecyclerViewIssue.getLayoutParams();
        params.width = displayViewWidth_ * 2 / 3;
        mRecyclerViewIssue.setLayoutParams(params);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle != null) {
            return mActionBarDrawerToggle.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
