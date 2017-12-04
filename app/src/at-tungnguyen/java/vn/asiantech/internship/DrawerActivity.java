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
import android.widget.AdapterView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class DrawerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private ImageView mImageView;
    private RecyclerView mRecyclerView;
    private View mMaincontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        mToolbar = findViewById(R.id.appBar);
        mRecyclerView = findViewById(R.id.recyclerview);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mImageView = findViewById(R.id.imgItemMenu);
        mMaincontent = findViewById(R.id.llContent);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    private static final class DummyData {
        private static List<DrawerEvent> getData() {
            List<DrawerEvent> list = new ArrayList<>();
            list.add(new DrawerEvent("London", null, DrawerEvent.EVENT_HEADER, R.drawable.ic_compare_arrows_black_24dp));
            list.add(new DrawerEvent("Droidcon", "Droidcon in London", DrawerEvent.EVENT_CONTENT, R.drawable.ic_compare_arrows_black_24dp));
            list.add(new DrawerEvent("Droidcon", "Droidcon in London", DrawerEvent.EVENT_CONTENT, R.drawable.ic_compare_arrows_black_24dp));
            return list;
        }
    }

    private void initDrawer() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, mToolbar, R.string.open, R.string.close) {
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
