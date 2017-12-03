package vn.asiantech.internship.drawerlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.drawerlayout.adapters.DrawerMenuAdapter;
import vn.asiantech.internship.drawerlayout.models.DrawerMenu;

/**
 * Created at 2017
 * Created by jackty on 30/11/2017.
 */

public class DrawerLayoutActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawerlayout);
        initViews();
        if (getSupportActionBar() != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        callDrawerMenu();
        addDataMenu();
    }

    private void addDataMenu() {
        DrawerMenuAdapter adapter = new DrawerMenuAdapter(getData());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
    }

    private void callDrawerMenu() {
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

    private void initViews() {
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mToolbar = findViewById(R.id.action_bar);
        mRecyclerView = findViewById(R.id.recyclerViewDrawer);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mActionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    public static List<DrawerMenu> getData() {
        List<DrawerMenu> listDrawerMenu = new ArrayList<>();
        listDrawerMenu.add(new DrawerMenu(R.drawable.ic_user_header, "Mail"));
        listDrawerMenu.add(new DrawerMenu(R.drawable.ic_assignment_late_black, "fuck"));
        listDrawerMenu.add(new DrawerMenu(R.drawable.ic_assignment_late_black, "fuck"));
        listDrawerMenu.add(new DrawerMenu(R.drawable.ic_assignment_late_black, "fuck"));
        listDrawerMenu.add(new DrawerMenu(R.drawable.ic_assignment_late_black, "fuck"));
        return listDrawerMenu;
    }

}