package vn.asiantech.internship.ui.drawer;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.image.ImageExerciseActivity;
import vn.asiantech.internship.model.DrawerItem;
import vn.asiantech.internship.ui.caculatorview.CalculatorActivity;
import vn.asiantech.internship.ui.login.MainActivity;
import vn.asiantech.internship.ui.recyclerview.RecyclerViewActivity;

public class DrawerActivity extends AppCompatActivity implements DrawerAdapter.OnItemClickListener {
    private static final int HEADER = 1;
    private static final int ITEM = 2;
    private static final int REQUEST_PHOTO_FROM_GOOGLE_PHOTOS = 1;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private List<DrawerItem> mData;
    private DrawerAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        initHeader();
        initView();
        initDrawer();
        initData();
        initAdapter();
    }

    private void initHeader() {
        if (getSupportActionBar() != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mRecyclerView = findViewById(R.id.recyclerview);
    }

    @Override
    public void onClickHeaderitem(View view, int position) {
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
    public void onClickContentitem(View view, int position) {
        Intent intent = mData.get(position).getIntent();
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PHOTO_FROM_GOOGLE_PHOTOS && resultCode == RESULT_OK && data != null) {
            mData.get(0).setImageUri(data.getData().toString());
        } else {
            Toast.makeText(this, R.string.have_not_picked_img, Toast.LENGTH_LONG).show();
        }
    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add(new DrawerItem("Tung.nguyen2@asiastech.vn", HEADER, R.drawable.profile, "", null));
        mData.add(new DrawerItem("Login", ITEM, R.drawable.ic_inbox_content, "", new Intent(this, MainActivity.class)));
        mData.add(new DrawerItem("Calculator", ITEM, R.drawable.ic_outbox_content, "", new Intent(this, CalculatorActivity.class)));
        mData.add(new DrawerItem("RecyclerView", ITEM, R.drawable.ic_trash_content, "", new Intent(this, RecyclerViewActivity.class)));
        mData.add(new DrawerItem("LoadImage", ITEM, R.drawable.ic_outbox_content, "", new Intent(this, ImageExerciseActivity.class)));
    }

    private void initDrawer() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
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
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void initAdapter() {
        mAdapter = new DrawerAdapter(this, mData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.getLayoutParams().width = ScreenUtil.getScreenWidth(getApplicationContext()) * 2 / 3;
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
