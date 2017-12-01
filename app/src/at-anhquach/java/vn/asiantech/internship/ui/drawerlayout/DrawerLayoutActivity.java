package vn.asiantech.internship.ui.drawerlayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.Issue;
import vn.asiantech.internship.ui.calculator.CalculatorViewActivity;
import vn.asiantech.internship.ui.login.LoginActivity;
import vn.asiantech.internship.ui.recyclerview.RecyclerViewActivity;

public class DrawerLayoutActivity extends AppCompatActivity implements View.OnClickListener, DrawerLayoutAdapter.OnItemClickListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private RecyclerView mRecyclerViewLeftMenu;
    private LinearLayout mLlContent;
    private List<Issue> mIssueList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        initView();
        initDrawer();
        initData();
        initAdapter();
    }

    private void initView() {
        mDrawerLayout = findViewById(R.id.drawerLayout);
        Button mBtnMenuLeft = findViewById(R.id.btnLeftMenu);
        mRecyclerViewLeftMenu = findViewById(R.id.recyclerViewLeftMenu);
        mLlContent = findViewById(R.id.llContent);
        mBtnMenuLeft.setOnClickListener(this);
    }

    private void initData() {
        mIssueList.add(new Issue(R.drawable.ic_move_to_inbox_black_24dp, "Create Login Screen"));
        mIssueList.add(new Issue(R.drawable.ic_send_black_24dp, "Create Calculator View"));
        mIssueList.add(new Issue(R.drawable.ic_sms_failed_black_24dp, "Create Recycler View"));
    }

    private void initDrawer() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, R.string.open, R.string.close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                mLlContent.setTranslationX(slideOffset * drawerView.getWidth());
                mDrawerLayout.bringChildToFront(drawerView);
                mDrawerLayout.requestLayout();
                mDrawerLayout.setScrimColor(Color.TRANSPARENT);
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
    }

    private void initAdapter() {
        DrawerLayoutAdapter mIssueAdapter = new DrawerLayoutAdapter(mIssueList, this);
        mRecyclerViewLeftMenu.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewLeftMenu.setAdapter(mIssueAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mActionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLeftMenu:
                mDrawerLayout.openDrawer(Gravity.LEFT);
                break;

        }
    }

    @Override
    public void onNameIssueClick(int position) {
        switch (position) {
            case 1:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, CalculatorViewActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
        }
    }

}
