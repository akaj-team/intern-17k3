package vn.asiantech.internship.ui.thread_handler.ui.thread;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.thread_handler.adapter.TabThreadAdapter;

public class ThreadActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        initView();
        initData();
    }

    private void initData() {
        mViewPager.setAdapter(new TabThreadAdapter(getSupportFragmentManager(),getApplicationContext()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initView() {
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);
    }
}
