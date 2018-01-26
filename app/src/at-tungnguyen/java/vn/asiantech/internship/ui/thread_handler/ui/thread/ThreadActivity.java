package vn.asiantech.internship.ui.thread_handler.ui.thread;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.thread_handler.adapter.TabThreadAdapter;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 08/12/2017.
 */
public class ThreadActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabThreadAdapter mTabThreadAdapter;
    private List<Fragment>fragmentList;
    private ViewFragment mViewFragment = new ViewFragment();
    private DownloadFragment mDownloadFragment = new DownloadFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        fragmentList = new ArrayList<>();
        initView();
        initData();
    }

    /**
     * initView
     */
    private void initView() {
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);
    }

    /**
     * initData for Tablayout
     */
    private void initData() {
        mTabThreadAdapter = new TabThreadAdapter(getSupportFragmentManager(),fragmentList);
        fragmentList.add(mDownloadFragment);
        fragmentList.add(mViewFragment);
        mViewPager.setAdapter(mTabThreadAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * Set Bitmap
     */
    public void setBitMap(Bitmap bitMap) {
        ViewFragment viewFragment = (ViewFragment) mTabThreadAdapter.getItem(1);
        if (viewFragment != null) {
            viewFragment.setImageBitmap(bitMap);
        }
    }
}
