package vn.asiantech.internship.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;

/**
 * Created by phongle on 14/12/2560.
 * Activity view pager main
 */

public class ViewPagerMainActivity extends AppCompatActivity {
    private ViewPager mViewPagerMain;
    private TabLayout mTabLayoutMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_main);
        initViews();
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        mViewPagerMain.setAdapter(mainAdapter);
        mTabLayoutMain.setupWithViewPager(mViewPagerMain);
    }

    private void initViews() {
        mViewPagerMain = findViewById(R.id.viewPagerMain);
        mTabLayoutMain = findViewById(R.id.tabLayoutMain);
    }
}
