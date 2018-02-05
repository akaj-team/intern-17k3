package vn.asiantech.internship.ui.viewpager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;

/**
 * Created by vietphan on 13/12/2017.
 * Class TabLayoutActivity
 */
public class TabLayoutActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        initViews();
        initData();
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewPagerTabLayout);
        mTabLayout = findViewById(R.id.tabLayoutDictionary);
    }

    private void initData() {
        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        TabLayoutAdapter mDictionaryAdapter = new TabLayoutAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mDictionaryAdapter);
    }
}
