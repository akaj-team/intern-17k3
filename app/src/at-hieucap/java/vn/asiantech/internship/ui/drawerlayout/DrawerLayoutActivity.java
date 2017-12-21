package vn.asiantech.internship.ui.drawerlayout;

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
import android.view.View;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Option;
import vn.asiantech.internship.models.User;
import vn.asiantech.internship.ui.calculator.CalculatorActivity;
import vn.asiantech.internship.ui.loadimage.LoadImageActivity;
import vn.asiantech.internship.ui.login.LoginActivity;
import vn.asiantech.internship.ui.recyclerview.PersonViewActivity;
import vn.asiantech.internship.ui.savedata.SaveDataActivity;

public class DrawerLayoutActivity extends AppCompatActivity implements InformationAdapter.OnItemClickListener {
    public static final int REQUEST_CODE = 1;
    private static final String CALCULATOR = "Calculator";
    private static final String LOGIN_SCREEN = "Login Screen";
    private static final String RECYCLER_VIEW = "Recycler View";
    private static final String SAVE_DATA = "Save Data";
    private static final String LOAD_IMAGE = "Load Image";
    private static final String GOOGLE_PHOTO = "com.google.android.apps.photos";
    private static final String MSG = "Stack trace";
    private final String TAG = DrawerLayoutActivity.this.getClass().getSimpleName();
    private DrawerLayout mDrawerLayout;
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
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this,
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
        mInformationList.add(new Option(R.drawable.ic_download, LOGIN_SCREEN, false, new Intent(this, LoginActivity.class)));
        mInformationList.add(new Option(R.drawable.ic_outbox, CALCULATOR, false, new Intent(this, CalculatorActivity.class)));
        mInformationList.add(new Option(R.drawable.ic_trash, RECYCLER_VIEW, false, new Intent(this, PersonViewActivity.class)));
        mInformationList.add(new Option(R.drawable.ic_spam, LOAD_IMAGE, false, new Intent(this, LoadImageActivity.class)));
        mInformationList.add(new Option(R.drawable.ic_spam, SAVE_DATA, false, new Intent(this, SaveDataActivity.class)));
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
        Intent intent = ((Option) mInformationList.get(position)).getIntent();
        startActivity(intent);
        if (position != mOldPosition) {
            if (mInformationList.get(position) instanceof Option) {
                ((Option) mInformationList.get(position)).setSelected();
                mAdapter.notifyItemChanged(position);
            }
            if (mOldPosition != -1) {
                if (mInformationList.get(mOldPosition) instanceof Option) {
                    ((Option) mInformationList.get(mOldPosition)).setSelected();
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
                } catch (IOException e) {
                    Log.e(TAG, MSG);
                }
            }
        }
    }
}
