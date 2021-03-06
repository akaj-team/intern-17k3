package vn.asiantech.internship.drawerlayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.login.LoginActivity;
import vn.asiantech.internship.models.Option;
import vn.asiantech.internship.models.User;

/**
 * Created by phongle on 11/12/2560.
 * DrawerLayoutActivity
 */
public class DrawerLayoutActivity extends AppCompatActivity implements LeftMenuAdapter.OnItemClickListener {
    private static final int REQUEST_CODE_PICK_IMAGE = 1;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private LeftMenuAdapter mAdapter;
    private List<Object> mObjectList = new ArrayList<>();
    private RecyclerView mRecyclerViewLeftMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        initViews();
        initDrawer();
        initData();
        initAdapter();
    }

    private void initDrawer() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, R.string.open, R.string.close) {
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
        mRecyclerViewLeftMenu = findViewById(R.id.recyclerViewMenu);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }

    private void initAdapter() {
        mAdapter = new LeftMenuAdapter(mObjectList, this);
        mRecyclerViewLeftMenu.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewLeftMenu.setAdapter(mAdapter);
    }

    private void initData() {
        mObjectList.add(new User(getResources().getDrawable(R.drawable.bg_avatar), "CristianoRonaldo@gmail.com"));
        mObjectList.add(new Option(R.drawable.ic_inbox, "Inbox"));
        mObjectList.add(new Option(R.drawable.ic_outbox, "Outbox"));
        mObjectList.add(new Option(R.drawable.ic_trash, "Trash"));
        mObjectList.add(new Option(R.drawable.ic_spam, "Spam"));
    }

    @Override
    public void onClickChangeAvatar() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        if (GoogleUtil.isPackageInstalled(this)) {
            intent.setPackage(GoogleUtil.GOOGLE_PHOTO);
            startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
        } else {
            startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
        }
    }

    @Override
    public void onClickOption(int position) {
        Intent intent = new Intent(DrawerLayoutActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_IMAGE) {
            if (data != null) {
                try {
                    Uri chosenImageUri = data.getData();
                    Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), chosenImageUri);
                    Drawable d = new BitmapDrawable(getResources(), bm);
                    if (mObjectList.get(0) instanceof User) {
                        ((User) mObjectList.get(0)).setAvatar(d);
                    }
                    mAdapter.notifyItemChanged(0);
                } catch (IOException e) {
                    Log.e("v", "no image");
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mActionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
