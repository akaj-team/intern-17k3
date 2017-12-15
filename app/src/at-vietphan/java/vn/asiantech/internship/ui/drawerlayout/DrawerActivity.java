package vn.asiantech.internship.ui.drawerlayout;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Issue;
import vn.asiantech.internship.models.Person;
import vn.asiantech.internship.ui.calculator.CalculatorActivity;
import vn.asiantech.internship.ui.login.LoginActivity;
import vn.asiantech.internship.ui.recyclerview.RecyclerViewActivity;
import vn.asiantech.internship.utils.GoogleUtil;
import vn.asiantech.internship.utils.ScreenUtil;

/**
 * Class DrawerActivity
 */
public class DrawerActivity extends AppCompatActivity implements IssueAdapter.OnItemClickListener {
    private static final int REQUEST_CODE_PICK_IMAGE = 77;
    private Toolbar mToolBar;
    private DrawerLayout mDrawerLayout;
    private RecyclerView mRecyclerViewIssue;
    private List<Object> mObjects;
    private LinearLayout mLlMain;
    private IssueAdapter mIssueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        setSpActionBar();
        initViews();
        initData();
        initAdapter();
        initDrawer();
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
        mRecyclerViewIssue = findViewById(R.id.recyclerViewMenu);
        mLlMain = findViewById(R.id.llMain);
        ViewGroup.LayoutParams params = mRecyclerViewIssue.getLayoutParams();
        params.width = ScreenUtil.getWidthScreen(this) * 2 / 3;
        mRecyclerViewIssue.setLayoutParams(params);
    }

    private void initData() {
        mObjects = new ArrayList<>();
        mObjects.add(new Person(R.drawable.ic_account, "viet.phan@asiantech.vn", null));
        mObjects.add(new Issue(R.drawable.ic_login, "Login", new Intent(this, LoginActivity.class)));
        mObjects.add(new Issue(R.drawable.ic_caculator, "Calculator", new Intent(this, CalculatorActivity.class)));
        mObjects.add(new Issue(R.drawable.ic_list_grey_900_24dp, "Recycler View", new Intent(this, RecyclerViewActivity.class)));
        mObjects.add(new Issue(R.drawable.ic_share_grey_900_24dp, "Share", new Intent()));
    }

    private void initAdapter() {
        mIssueAdapter = new IssueAdapter(mObjects, this);
        mRecyclerViewIssue.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewIssue.setAdapter(mIssueAdapter);
    }

    private void initDrawer() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.open, R.string.close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                mLlMain.setTranslationX(slideOffset * drawerView.getWidth());
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
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onClickItemIssue(int position) {
        Issue issue = (Issue) mObjects.get(position);
        Intent intent = issue.getIntent();
        if (position == mObjects.size() - 1) {
            Uri uri = Uri.fromFile(getScreenShot());
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            try {
                startActivity(Intent.createChooser(intent, getString(R.string.share)));
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, R.string.no_apps, Toast.LENGTH_SHORT).show();
            }
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void onClickImgAvatar() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        if (GoogleUtil.isGooglePhotosInstalled(this)) {
            intent.setPackage(GoogleUtil.GOOGLE_PHOTOS_PACKAGE_NAME);
            startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
        } else {
            startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            for (int i = 0; i < mObjects.size(); i++) {
                if (mObjects.get(i) instanceof Person) {
                    ((Person) mObjects.get(i)).setUri(String.valueOf(data.getData()));
                    mIssueAdapter.notifyItemChanged(i);
                    return;
                }
            }
        } else {
            Toast.makeText(this, R.string.have_not_picked_img, Toast.LENGTH_LONG).show();
        }
    }

    private File getScreenShot() {
        Date now = new Date();
        DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
        File imageFile = null;
        try {
            String path = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";
            View view = getWindow().getDecorView().getRootView();
            view.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);
            imageFile = new File(path);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            Log.d(getString(R.string.error), e.getMessage());
        }
        return imageFile;
    }
}
