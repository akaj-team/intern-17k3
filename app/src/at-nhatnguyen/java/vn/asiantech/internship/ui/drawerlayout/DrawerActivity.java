package vn.asiantech.internship.ui.drawerlayout;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Option;
import vn.asiantech.internship.models.User;
import vn.asiantech.internship.ui.MainActivity;

public class DrawerActivity extends AppCompatActivity implements MenuAdapter.OnItemClickListener {
    private static final float NUMBER_MOVE = 2 / 3;
    private static final int REQUEST_CODE = 2;
    private static final String GOOGLE_PHOTO = "com.google.android.apps.photos";
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private RecyclerView mRecyclerViewOption;
    private MenuAdapter mAdapter;
    private List<Object> mObjects;
    private LinearLayout mLlMainContent;
    private Toolbar mToolbar;

    public static boolean isGooglePhotosInstalled(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(GOOGLE_PHOTO, PackageManager.GET_ACTIVITIES) != null;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        initView();
        initData();
        initAdapter();
        initDrawerLayout();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerViewOption = findViewById(R.id.recycleViewOption);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mLlMainContent = findViewById(R.id.llMainContent);
    }

    private void initData() {
        mObjects = new ArrayList<>();
        mObjects.add(new User(R.drawable.ic_avatar, "nhatnguyen@asiantech.vn"));
        mObjects.add(new Option("Inbox", R.drawable.selector_click_inbox));
        mObjects.add(new Option("Outbox", R.drawable.selector_click_outbox));
        mObjects.add(new Option("Trash", R.drawable.selector_click_delete));
        mObjects.add(new Option("Spam", R.drawable.selector_click_spam));
    }

    private void initAdapter() {
        mAdapter = new MenuAdapter(mObjects, this);
        mRecyclerViewOption.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewOption.setAdapter(mAdapter);
    }

    private void initDrawerLayout() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.numberone, R.string.numbertwo) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                mRecyclerViewOption.setTranslationX(mRecyclerViewOption.getWidth() * (1 - slideOffset) * NUMBER_MOVE);
                mLlMainContent.setTranslationX(mRecyclerViewOption.getWidth() * slideOffset);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mActionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickItem(int position) {
        if (mObjects.get(position) instanceof Option) {
            mAdapter.notifyItemChanged(position);
            Toast.makeText(this, ((Option) mObjects.get(position)).getOptionName(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Override
    public void onClickImage(View view) {
        if (isGooglePhotosInstalled(this)) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setPackage(GOOGLE_PHOTO);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_CODE);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            ((ImageView) findViewById(R.id.imgAvatar)).setImageURI(Uri.parse(data.getData().toString()));
        }
    }
}
