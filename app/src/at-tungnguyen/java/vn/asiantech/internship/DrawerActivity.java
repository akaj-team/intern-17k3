package vn.asiantech.internship;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DrawerActivity extends AppCompatActivity implements DrawerAdapter.OnItemClickListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private static final int REQUEST_PHOTO_FROM_GOOGLE_PHOTOS = 1;
    private List<DrawerItem> mData;
    private DrawerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        getData();
        mToolbar = findViewById(R.id.appBar);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        setSupportActionBar(mToolbar);
        initDrawer();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter = new DrawerAdapter(this, mData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        RecyclerView mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onclickHeaderitem(View view, int position) {
        Intent intentToResolve = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intentToResolve.setType("image/*");
        if (GoogleUtil.isGooglePhotosInstalled(this)) {
            intentToResolve.setPackage(GoogleUtil.GOOGLE_PHOTOS_PACKAGE_NAME);
            startActivityForResult(intentToResolve, REQUEST_PHOTO_FROM_GOOGLE_PHOTOS);
        } else {
            startActivityForResult(intentToResolve, REQUEST_PHOTO_FROM_GOOGLE_PHOTOS);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PHOTO_FROM_GOOGLE_PHOTOS && resultCode == RESULT_OK && data != null) {
            mData.get(0).setImageuri(data.getData() + "");
            mAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, R.string.have_not_picked_img, Toast.LENGTH_LONG).show();
        }
    }


    private void getData() {
        mData = new ArrayList<>();
        mData.add(new DrawerItem("Header", 1, R.drawable.profile, ""));
        mData.add(new DrawerItem("Item", 2, R.drawable.ic_compare_arrows_black_24dp, ""));
        mData.add(new DrawerItem("Item", 2, R.drawable.ic_compare_arrows_black_24dp, ""));
    }

    private void initDrawer() {
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, mToolbar, R.string.open, R.string.close) {
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
}
