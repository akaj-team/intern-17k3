package vn.asiantech.internship.ui.thread_handler.ui.thread;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.thread_handler.adapter.TabThreadAdapter;

public class ThreadActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabThreadAdapter mTabThreadAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
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
        mTabThreadAdapter = new TabThreadAdapter(getSupportFragmentManager(), getApplicationContext());
        mViewPager.setAdapter(mTabThreadAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * Set Bitmap
     * @param bitMap
     */
    public void setBitMap(Bitmap bitMap) {
        ViewFragment viewFragment = (ViewFragment) mTabThreadAdapter.getItem(1);
        if (viewFragment != null) {
            viewFragment.setImageBitmapp(bitMap);
        }
    }
}
