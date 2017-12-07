package vn.asiantech.internship.drawerlayout;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.calculation.CalculatorActivity;
import vn.asiantech.internship.drawerlayout.adapters.DrawerMenuAdapter;
import vn.asiantech.internship.drawerlayout.models.DrawerMenu;
import vn.asiantech.internship.login.LoginActivity;
import vn.asiantech.internship.recyclerview.StatusActivity;

/**
 * Created at 2017
 * Created by jackty on 30/11/2017.
 */
public class DrawerLayoutActivity extends AppCompatActivity implements DrawerMenuAdapter.onItemClickListener {

    private static final int REQUEST_PHOTO_FROM_GOOGLE_PHOTOS = 1;
    public static final String GOOGLE_PHOTOS_PACKAGE_NAME = "com.google.android.apps.photos";
    private static final float SET_WIDTH = 2 / 3;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private View mMainContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawerlayout);
        initViews();
        if (getSupportActionBar() != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
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
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                ViewGroup.LayoutParams params = mMainContent.getLayoutParams();
                params.width = (int) (size.x * SET_WIDTH);
                mMainContent.setLayoutParams(params);
                mMainContent.setTranslationX(mRecyclerView.getWidth() * slideOffset);
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
        mToolbar = findViewById(R.id.toolBar);
        mRecyclerView = findViewById(R.id.recyclerViewDrawer);
        mMainContent = findViewById(R.id.rlContent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mActionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    public List<DrawerMenu> getData() {
        List<DrawerMenu> listDrawerMenu = new ArrayList<>();
        listDrawerMenu.add(new DrawerMenu(R.drawable.ic_user_header, "tien.huynh3@asiantech.vn"));
        listDrawerMenu.add(new DrawerMenu(R.drawable.ic_inbox_black, "Issues 1"));
        listDrawerMenu.add(new DrawerMenu(R.drawable.ic_send_black, "Issues 2"));
        listDrawerMenu.add(new DrawerMenu(R.drawable.ic_delete_black, "Issues 3"));
        listDrawerMenu.add(new DrawerMenu(R.drawable.ic_assignment_late_black, "Issues 4"));
        return listDrawerMenu;
    }

    /**
     * OnClick Item Hoder
     **/
    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 1:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, CalculatorActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, StatusActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, DrawerLayoutActivity.class));
                break;
        }
    }

    /**
     * Onclick Header Hoder
     **/
    @Override
    public void onImgHeaderClick(View view) {
        Intent intentToResolve = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (GoogleUtil.isGooglePhotosInstalled(this)) {
            intentToResolve.setPackage(GOOGLE_PHOTOS_PACKAGE_NAME);
            ResolveInfo resolveInfo = getPackageManager().resolveActivity(intentToResolve, 0);
            if (resolveInfo != null) {
                Intent intent = new Intent(intentToResolve);
                intent.setType("image/*");
                this.startActivityForResult(intent, REQUEST_PHOTO_FROM_GOOGLE_PHOTOS);
            }
        } else {
            intentToResolve.setType("video/*, images/*");
            this.startActivityForResult(intentToResolve, REQUEST_PHOTO_FROM_GOOGLE_PHOTOS);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == REQUEST_PHOTO_FROM_GOOGLE_PHOTOS && resultCode == RESULT_OK) {
            ((ImageView) findViewById(R.id.imgHeader)).setImageURI(data.getData());
        } else {
            Toast.makeText(this, getString(R.string.not_img_picked),
                    Toast.LENGTH_LONG).show();
        }
    }
}
