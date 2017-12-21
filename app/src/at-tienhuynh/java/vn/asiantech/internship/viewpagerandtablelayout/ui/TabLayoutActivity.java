package vn.asiantech.internship.viewpagerandtablelayout.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;
import vn.asiantech.internship.viewpagerandtablelayout.adapters.TabLayoutAdapter;

public class TabLayoutActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPagerContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        initViews();
        initAdapters();
    }

    /**
     * Init Views
     */
    private void initViews() {
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPagerContainer = findViewById(R.id.viewPagerContainer);
    }

    /**
     * Init Adapters
     */
    private void initAdapters() {
        mViewPagerContainer.setAdapter(new TabLayoutAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPagerContainer);
    }
}
