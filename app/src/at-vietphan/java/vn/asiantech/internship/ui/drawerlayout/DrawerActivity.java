package vn.asiantech.internship.ui.drawerlayout;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Issue;
import vn.asiantech.internship.ui.calculator.CalculatorActivity;
import vn.asiantech.internship.ui.login.LoginActivity;
import vn.asiantech.internship.ui.recyclerview.RecyclerViewActivity;

public class DrawerActivity extends AppCompatActivity implements IssueAdapter.OnItemClickListener {
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private RecyclerView mRecyclerViewIssue;
    private List<Issue> mIssueList;
    private IssueAdapter mAdapter;
    private LinearLayout mLnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        setSpActionBar();
        initViews();
        initData();
        initAdapter();
        initDrawer();
        setWidthDrawerLayout();
    }

    private void setSpActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        }
    }

    private void initViews() {
        mToolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mRecyclerViewIssue = findViewById(R.id.recyclerViewMulti);
        mIssueList = new ArrayList<>();
        mLnMain = findViewById(R.id.lnMain);
    }

    private void initData() {
        mIssueList.add(new Issue(R.drawable.ic_login, "Login"));
        mIssueList.add(new Issue(R.drawable.ic_caculator, "Calculator"));
        mIssueList.add(new Issue(R.drawable.ic_list_grey_900_24dp, "Recycler View"));
        mIssueList.add(new Issue(R.drawable.ic_exit_to_app_grey_900_24dp, "Exit"));
    }

    private void initAdapter() {
        mAdapter = new IssueAdapter(mIssueList, this);
        mRecyclerViewIssue.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewIssue.setAdapter(mAdapter);
    }

    private void initDrawer() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                mLnMain.setTranslationX(slideOffset * drawerView.getWidth());
                mRecyclerViewIssue.bringChildToFront(drawerView);
                mRecyclerViewIssue.requestLayout();
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
    public void onClickItemIssue(int position) {
        switch (position) {
            case 1:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, CalculatorActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case 4:
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
                break;
            default:
                break;
        }
        mAdapter.notifyItemChanged(position);
    }
}
