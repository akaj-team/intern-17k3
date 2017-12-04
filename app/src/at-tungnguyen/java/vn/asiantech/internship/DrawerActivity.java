package vn.asiantech.internship;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DrawerActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        mToolbar = findViewById(R.id.appBar);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        setSupportActionBar(mToolbar);
        initDrawer();
    }
    @Override
    protected void onStart() {
        super.onStart();
        DrawerAdapter adapter = new DrawerAdapter(DummyData.getData());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
    }
    public static final class DummyData {
        public static List<DrawerEvent> getData() {
            List<DrawerEvent> list = new ArrayList<>();
            list.add(new DrawerEvent("London", null, DrawerEvent.EVENT_HEADER));
            list.add(new DrawerEvent("Droidcon", "Droidcon in London", DrawerEvent.EVENT_CONTENT));
            return list;
        }
    }

    private void initDrawer() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,mToolbar,R.string.open,R.string.close){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                Log.i("XXX", "onDrawerSlide: ");
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Log.i("XXX", "onDrawerOpened: ");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.i("XXX", "onDrawerClosed: ");
            }
        };
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
    }

}
