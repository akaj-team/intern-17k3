package vn.asiantech.internship;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
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
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class DrawerActivity extends AppCompatActivity implements DrawerAdapter.OnItemClickListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private ImageView mImageView;
    private RecyclerView mRecyclerView;
    private View mMaincontent;
    private static final int REQUEST_PHOTO_FROM_GOOGLE_PHOTOS = 1;
    private static final String GOOGLE_PHOTOS_PACKAGE_NAME = "com.google.android.apps.photos";
    public static boolean isGooglePhotosInstalled(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(GOOGLE_PHOTOS_PACKAGE_NAME, PackageManager.GET_ACTIVITIES) != null;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        mToolbar = findViewById(R.id.appBar);
        mRecyclerView = findViewById(R.id.recyclerview);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mImageView = findViewById(R.id.imgCirle);
        mMaincontent = findViewById(R.id.llContent);
        setSupportActionBar(mToolbar);
        initDrawer();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mImageView.setImageURI(data.getData());
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        DrawerAdapter adapter = new DrawerAdapter(DummyData.getData(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        RecyclerView mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onclickHeaderitem(View view, int position) {
        if (isGooglePhotosInstalled(getApplicationContext())) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_PICK);
            intent.setType("image/*");
            List<ResolveInfo> resolveInfoList = this.getPackageManager().queryIntentActivities(intent, 0);
            for (int i = 0; i < resolveInfoList.size(); i++) {
                if (resolveInfoList.get(i) != null) {
                    String packageName = resolveInfoList.get(i).activityInfo.packageName;
                    if (GOOGLE_PHOTOS_PACKAGE_NAME.equals(packageName)) {
                        intent.setComponent(new ComponentName(packageName, resolveInfoList.get(i).activityInfo.name));
                        this.startActivityForResult(intent, REQUEST_PHOTO_FROM_GOOGLE_PHOTOS);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void onclickMenuitem(View view, int position) {

    }

    private static final class DummyData {
        private static List<DrawerEvent> getData() {
            List<DrawerEvent> list = new ArrayList<>();
            list.add(new DrawerEvent("Droidcon", "Issue1", DrawerEvent.EVENT_CONTENT, R.drawable.ic_compare_arrows_black_24dp));
            list.add(new DrawerEvent("Droidcon", "Issue2", DrawerEvent.EVENT_CONTENT, R.drawable.ic_compare_arrows_black_24dp));
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
