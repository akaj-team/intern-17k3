package vn.asiantech.internship.ui.drawer.layout;

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
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;

public class DrawerLayoutActivity extends AppCompatActivity implements InformationAdapter.OnItemClickListener {
    public static final int REQUEST_CODE = 1;
    private static final String GOOGLE_PHOTO = "com.google.android.apps.photos";
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private InformationAdapter mAdapter;
    private List<Object> mInformationList = new ArrayList<>();
    private RecyclerView mRecyclerViewLeftBar;
    private int mOldPosition = -1;
    private RelativeLayout mRlActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawerlayout);
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
                mRlActivity.setTranslationX(slideOffset * mRecyclerViewLeftBar.getWidth());
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
        mDrawerLayout = findViewById(R.id.drawerContain);
        mRlActivity = findViewById(R.id.rlActivity);
        mRecyclerViewLeftBar = findViewById(R.id.recyclerView);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }

    private void initAdapter() {
        mAdapter = new InformationAdapter(mInformationList, this);
        mRecyclerViewLeftBar.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewLeftBar.setAdapter(mAdapter);
    }

    private void initData() {
        mInformationList.add(new User(getResources().getDrawable(R.drawable.ic_ibrahimovic), getString(R.string.text_email)));
        mInformationList.add(new Option(R.drawable.ic_download, "Inbox", false));
        mInformationList.add(new Option(R.drawable.ic_outbox, "Outbox", false));
        mInformationList.add(new Option(R.drawable.ic_trash, "Trash", false));
        mInformationList.add(new Option(R.drawable.ic_spam, "Spam", false));
    }

    @Override
    public void onClickChangeAvatar() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        if (GoogleFunction.isPackageInstalled(this)) {
            intent.setPackage(GOOGLE_PHOTO);
            startActivityForResult(intent, REQUEST_CODE);
        } else {
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    public void onClickOption(int position) {
        if (position != mOldPosition) {
            if (mInformationList.get(position) instanceof Option) {
                ((Option) mInformationList.get(position)).setClicked();
                mAdapter.notifyItemChanged(position);
            }
            if (mOldPosition != -1) {
                if (mInformationList.get(mOldPosition) instanceof Option) {
                    ((Option) mInformationList.get(mOldPosition)).setClicked();
                    mAdapter.notifyItemChanged(mOldPosition);
                }
            }
            mOldPosition = position;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (data != null) {
                try {
                    Uri chosenImageUri = data.getData();
                    Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), chosenImageUri);
                    Drawable d = new BitmapDrawable(getResources(), bm);
                    ((User) mInformationList.get(0)).setAvatar(d);
                    mAdapter.notifyItemChanged(0);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mActionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
