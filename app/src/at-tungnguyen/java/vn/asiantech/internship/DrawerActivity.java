package vn.asiantech.internship;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DrawerActivity extends AppCompatActivity implements DrawerAdapter.OnItemClickListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private RecyclerView mRecyclerView;
    private static final int REQUEST_PHOTO_FROM_GOOGLE_PHOTOS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        mToolbar = findViewById(R.id.appBar);
        mRecyclerView = findViewById(R.id.recyclerview);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        setSupportActionBar(mToolbar);
        initDrawer();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PHOTO_FROM_GOOGLE_PHOTOS && resultCode == RESULT_OK && data != null) {
            ((CircleImageView) findViewById(R.id.imgCirle)).setImageURI(Uri.parse(data.getData().toString()));
            Toast.makeText(this, data.getData() + "", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.have_not_picked_img, Toast.LENGTH_LONG).show();
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
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this,
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
