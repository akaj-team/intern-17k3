package vn.asiantech.internship.drawerlayout;

import android.content.Intent;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.drawerlayout.adapters.DrawerMenuAdapter;
import vn.asiantech.internship.drawerlayout.models.DrawerMenu;
import vn.asiantech.internship.login.LoginActivity;

/**
 * Created at 2017
 * Created by jackty on 30/11/2017.
 */

public class DrawerLayoutActivity extends AppCompatActivity implements DrawerMenuAdapter.onItemClickListener {

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
        addMenuData();
    }

    private void addMenuData() {
        DrawerMenuAdapter adapter = new DrawerMenuAdapter(getData(), this);
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
        listDrawerMenu.add(new DrawerMenu(R.drawable.ic_user_header, "tien.huynh3@asiantech.vn"));
        listDrawerMenu.add(new DrawerMenu(R.drawable.ic_inbox_black, "Issues 1"));
        listDrawerMenu.add(new DrawerMenu(R.drawable.ic_send_black, "Issues 2"));
        listDrawerMenu.add(new DrawerMenu(R.drawable.ic_delete_black, "Issues 3"));
        listDrawerMenu.add(new DrawerMenu(R.drawable.ic_assignment_late_black, "Issues 4"));
        return listDrawerMenu;
    }

    @Override
    public void onItemClick(View view, int position) {
        // Onclick Item Menu
        switch (position) {
            case 1:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case 2:
                Toast.makeText(this, "shit3", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "shit3", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "shit4", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
