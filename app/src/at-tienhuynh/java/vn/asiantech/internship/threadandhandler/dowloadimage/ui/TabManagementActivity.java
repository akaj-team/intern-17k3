package vn.asiantech.internship.threadandhandler.dowloadimage.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;
import vn.asiantech.internship.threadandhandler.dowloadimage.adapters.TabAdapter;

/**
 * Created at 2017
 * Created by jackty on 19/12/2017.
 */
public class TabManagementActivity extends AppCompatActivity {

    public TabAdapter mTabAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPagerContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_management);
        initViews();
        initAdapter();
    }

    /**
     * Init Views
     */
    private void initViews() {
        mTabLayout = findViewById(R.id.tabLayoutThread);
        mViewPagerContainer = findViewById(R.id.viewPagerThread);
    }

    /**
     * Init Adapters
     */
    private void initAdapter() {
        mTabAdapter = new TabAdapter(getSupportFragmentManager());
        mViewPagerContainer.setAdapter(mTabAdapter);
        mTabLayout.setupWithViewPager(mViewPagerContainer);
    }
}
