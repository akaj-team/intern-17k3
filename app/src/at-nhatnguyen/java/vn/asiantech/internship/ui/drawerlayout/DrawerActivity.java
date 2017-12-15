package vn.asiantech.internship.ui.drawerlayout;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Option;
import vn.asiantech.internship.models.User;
import vn.asiantech.internship.ui.calculator.CalculatorActivity;
import vn.asiantech.internship.ui.login.LoginActivity;
import vn.asiantech.internship.ui.recyclerview.CommentActivity;
import vn.asiantech.internship.util.GoogleUtil;

/**
 * Activity of screen with drawerLayout
 */
public class DrawerActivity extends AppCompatActivity implements MenuAdapter.OnItemClickListener {
    private static final int REQUEST_CODE_PICK_IMAGE = 2;
    private static final String CALCULATOR = "Calculator";
    private static final String DRAWER_LAYOUT = "DrawerLayout";
    private static final String LOGIN = "Login";
    private static final String RECYCLER_VIEW = "RecyclerView";
    private static final String OUTBOX = "Outbox";
    private DrawerLayout mDrawerLayout;
    private RecyclerView mRecyclerViewOption;
    private MenuAdapter mAdapter;
    private List<Object> mObjects;
    private LinearLayout mLlMainContent;
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        initViews();
        initData();
        initAdapter();
        initDrawerLayout();
    }

    private void initViews() {
        mToolBar = findViewById(R.id.toolBar);
        if (getSupportActionBar() != null) {
            setSupportActionBar(mToolBar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        }
        mRecyclerViewOption = findViewById(R.id.recyclerViewOption);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mLlMainContent = findViewById(R.id.llMainContent);
    }

    private void initData() {
        mObjects = new ArrayList<>();
        mObjects.add(new User(R.drawable.ic_avatar, "nhatnguyen@asiantech.vn", null));
        mObjects.add(new Option(CALCULATOR, R.drawable.selector_click_inbox, new Intent(this, CalculatorActivity.class)));
        mObjects.add(new Option(DRAWER_LAYOUT, R.drawable.selector_click_delete, new Intent(this, DrawerActivity.class)));
        mObjects.add(new Option(LOGIN, R.drawable.selector_click_spam, new Intent(this, LoginActivity.class)));
        mObjects.add(new Option(RECYCLER_VIEW, R.drawable.selector_click_inbox, new Intent(this, CommentActivity.class)));
        mObjects.add(new Option(OUTBOX, R.drawable.selector_click_outbox, new Intent()));
    }

    private void initAdapter() {
        mAdapter = new MenuAdapter(mObjects, this);
        mRecyclerViewOption.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewOption.setAdapter(mAdapter);
    }

    private void initDrawerLayout() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.numberone, R.string.numbertwo) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
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
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onClickItem(int position) {
        if (mObjects.get(position) instanceof Option) {
            if (TextUtils.equals(((Option) mObjects.get(position)).getName(), OUTBOX)) {
                String shareBody = String.valueOf(R.string.message);
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("image/*");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                sharingIntent.putExtra(Intent.EXTRA_STREAM, shareBody);
                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.app_name)));

            } else {
                Intent intent = ((Option) mObjects.get(position)).getIntent();
                startActivity(intent);
            }
        }
    }

    @Override
    public void onClickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        if (GoogleUtil.isGooglePhotosInstalled(this)) {
            intent.setPackage(GoogleUtil.GOOGLE_PHOTO_PACKAGE);
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
                if (mObjects.get(i) instanceof User) {
                    ((User) mObjects.get(i)).setUri(String.valueOf(data.getData()));
                    mAdapter.notifyItemChanged(i);
                    return;
                }
            }
        } else {
            Toast.makeText(this, R.string.notify_uri, Toast.LENGTH_SHORT).show();
        }
    }
}
