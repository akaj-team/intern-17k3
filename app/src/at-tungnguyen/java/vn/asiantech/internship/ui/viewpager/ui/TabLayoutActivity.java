package vn.asiantech.internship.ui.viewpager.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager.adapter.TabLayoutAdapter;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 14/12/2017.
 */
public class TabLayoutActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        initData();
        initView();
    }

    private void initData() {
        mViewPager.setAdapter(new TabLayoutAdapter(getSupportFragmentManager(), getApplicationContext()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initView() {
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);
    }
}
