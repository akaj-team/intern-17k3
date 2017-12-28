package vn.asiantech.internship.ui.thread;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.thread.fragment.TwoFragment;

/**
 * This class is activity of download image
 */
public class LoadImageActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private LoadImageAdapter mLoadImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image_thread);
        initViews();
        initAdapter();
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewPagerThread);
        mTabLayout = findViewById(R.id.tabLayoutThread);
    }

    private void initAdapter() {
        mLoadImageAdapter = new LoadImageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mLoadImageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public void getBitmap(Bitmap bitmap) {
        // get TwoFragment form LoadImageAdapter
        TwoFragment twoFragment = (TwoFragment) mLoadImageAdapter.getItem(1);
        if (twoFragment != null) {
            twoFragment.showBitmap(bitmap);
        }
    }
}
