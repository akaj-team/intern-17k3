package vn.asiantech.internship.ui.drawerlayout;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Issue;
import vn.asiantech.internship.ui.calculator.CalculatorActivity;
import vn.asiantech.internship.ui.login.LoginActivity;
import vn.asiantech.internship.ui.recyclerview.RecyclerViewActivity;
import vn.asiantech.internship.utils.GoogleUtil;

public class DrawerActivity extends AppCompatActivity implements IssueAdapter.OnItemClickListener {
    private static final String GOOGLE_PHOTOS_PACKAGE_NAME = "com.google.android.apps.photos";
    private static final int REQUEST_LOAD_IMG = 11;
    private Toolbar mToolBar;
    private DrawerLayout mDrawerLayout;
    private RecyclerView mRecyclerViewIssue;
    private List<Issue> mIssueList;
    private IssueAdapter mAdapter;
    private LinearLayout mLnMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        setSpActionBar();
        initViews();
        initData();
        initAdapter();
        initDrawer();
        setWidthDrawerLayout();
    }

    private void setSpActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        }
    }

    private void initViews() {
        mToolBar = findViewById(R.id.toolBar);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mRecyclerViewIssue = findViewById(R.id.recyclerViewMulti);
        mIssueList = new ArrayList<>();
        mLnMain = findViewById(R.id.llMain);

    }

    private void initData() {
        mIssueList.add(new Issue(R.drawable.ic_login, "Login"));
        mIssueList.add(new Issue(R.drawable.ic_caculator, "Calculator"));
        mIssueList.add(new Issue(R.drawable.ic_list_grey_900_24dp, "Recycler View"));
        mIssueList.add(new Issue(R.drawable.ic_share_grey_900_24dp, "Share"));
        mIssueList.add(new Issue(R.drawable.ic_exit_to_app_grey_900_24dp, "Exit"));
    }

    private void initAdapter() {
        mAdapter = new IssueAdapter(mIssueList, this);
        mRecyclerViewIssue.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewIssue.setAdapter(mAdapter);
    }

    private void initDrawer() {
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.open, R.string.close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                mLnMain.setTranslationX(slideOffset * drawerView.getWidth());
                mRecyclerViewIssue.bringChildToFront(drawerView);
                mRecyclerViewIssue.requestLayout();
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

    private void setWidthDrawerLayout() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int displayViewWidth = size.x;
        ViewGroup.LayoutParams params = mRecyclerViewIssue.getLayoutParams();
        params.width = displayViewWidth * 2 / 3;
        mRecyclerViewIssue.setLayoutParams(params);
    }

    @Override
    public void onClickItemIssue(int position) {
        switch (position) {
            case 1:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, CalculatorActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case 4:
                takeScreenshot();
                break;
            case 5:
                ActivityCompat.finishAffinity(this);
                break;
            default:
                break;
        }
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void onClickImgAvatar() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        if (GoogleUtil.isGooglePhotosInstalled(this)) {
            intent.setPackage(GOOGLE_PHOTOS_PACKAGE_NAME);
            startActivityForResult(intent, REQUEST_LOAD_IMG);
        } else {
            startActivityForResult(intent, REQUEST_LOAD_IMG);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOAD_IMG && resultCode == RESULT_OK && data != null) {
            ((CircleImageView) findViewById(R.id.circleImgAvater)).setImageURI(data.getData());
        } else {
            Toast.makeText(this, R.string.have_not_picked_img, Toast.LENGTH_LONG).show();
        }
    }

    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
        try {
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);
            File imageFile = new File(mPath);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();
            shareImage(imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void shareImage(File file) {
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        try {
            startActivity(Intent.createChooser(intent, "Share"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No apps", Toast.LENGTH_SHORT).show();
        }
    }
}
