package vn.asiantech.internship.ui.thread_handler_countdowntmer.thread_handler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;

/**
 * Created by tiboo on 20/12/2017.
 * Create activity download.
 */

public class DownloadActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    FragmentAdapter mFragmentAdapter;
    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mFragmentTittles = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ViewPager viewPager = findViewById(R.id.viewPagerContain);
        mTabLayout = findViewById(R.id.tabLayout);
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        mFragmentAdapter = new FragmentAdapter(fragmentManager, mFragments, mFragmentTittles);
        mFragmentAdapter.addFragment(new FirstFragment(), "Send");
        mFragmentAdapter.addFragment(new SecondFragment(), "Result");
        viewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(viewPager);
    }
}
