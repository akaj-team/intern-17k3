package vn.asiantech.internship.asynchronous;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;

/**
 * Created by anh.quach on 12/19/17.
 * Created threads.
 */
public class ThreadActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mFragmentTittles = new ArrayList<>();
    public TabThreadAdapter mTabLayoutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        initViews();
        initAdapter();
        setUpTabLayout();
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewPagerThread);
        mTabLayout = findViewById(R.id.tabLayoutThread);
    }

    private void initAdapter() {
        FragmentManager manager = getSupportFragmentManager();
        mTabLayoutAdapter = new TabThreadAdapter(manager, mFragments, mFragmentTittles);
        mTabLayoutAdapter.addFrag(new DownLoadImgFragment(), getString(R.string.title_tablayout_thread1));
        mTabLayoutAdapter.addFrag(new ViewImgFragment(), getString(R.string.title_tablayout_thread2));
        mViewPager.setAdapter(mTabLayoutAdapter);
    }

    private void setUpTabLayout() {
        mTabLayout.setupWithViewPager(mViewPager);
    }
}