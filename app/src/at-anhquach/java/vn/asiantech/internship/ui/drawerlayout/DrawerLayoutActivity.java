package vn.asiantech.internship.ui.drawerlayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.Issue;
import vn.asiantech.internship.savedata.ExternalStorageActivity;
import vn.asiantech.internship.savedata.SharePreferenceActivity;
import vn.asiantech.internship.savedata.UserSQLiteActivity;
import vn.asiantech.internship.ui.calculator.CalculatorViewActivity;
import vn.asiantech.internship.ui.login.LoginActivity;
import vn.asiantech.internship.ui.recyclerview.RecyclerViewActivity;

public class DrawerLayoutActivity extends AppCompatActivity implements View.OnClickListener, DrawerLayoutAdapter.OnItemClickListener {
    private static final String GOOGLE_PHOTOS_PACKAGE_NAME = "com.google.android.apps.photos";
    private static final int REQUEST_CODE_PICK_IMAGE = 77;
    private DrawerLayout mDrawerLayout;
    private RecyclerView mRecyclerViewLeftMenu;
    private LinearLayout mLlContent;
    private List<Issue> mIssueList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        initViews();
        initDrawer();
        initData();
        initAdapter();
    }

    private void initViews() {
        mDrawerLayout = findViewById(R.id.drawerLayout);
        Button btnLeftMenu = findViewById(R.id.btnLeftMenu);
        mRecyclerViewLeftMenu = findViewById(R.id.recyclerViewLeftMenu);
        mLlContent = findViewById(R.id.llContent);
        btnLeftMenu.setOnClickListener(this);
    }

    private void initData() {
        mIssueList.add(new Issue(R.drawable.ic_move_to_inbox_black_24dp, "Create Login Screen", new Intent(this, LoginActivity.class)));
        mIssueList.add(new Issue(R.drawable.ic_send_black_24dp, "Create Calculator View", new Intent(this, CalculatorViewActivity.class)));
        mIssueList.add(new Issue(R.drawable.ic_sms_failed_black_24dp, "Create Recycler View", new Intent(this, RecyclerViewActivity.class)));
        mIssueList.add(new Issue(R.drawable.ic_send_black_24dp, "Share Preference", new Intent(this, SharePreferenceActivity.class)));
        mIssueList.add(new Issue(R.drawable.ic_move_to_inbox_black_24dp, "External Storage", new Intent(this, ExternalStorageActivity.class)));
        mIssueList.add(new Issue(R.drawable.ic_move_to_inbox_black_24dp, "User SQLiteActivity", new Intent(this, UserSQLiteActivity.class)));
    }

    private void initDrawer() {
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, R.string.open, R.string.close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                mLlContent.setTranslationX(slideOffset * drawerView.getWidth());
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
        DrawerLayoutAdapter issueAdapter = new DrawerLayoutAdapter(mIssueList, this);
        mRecyclerViewLeftMenu.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewLeftMenu.setAdapter(issueAdapter);
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
        Intent intent = mIssueList.get(position - 1).getIntent();
        startActivity(intent);
    }
}
