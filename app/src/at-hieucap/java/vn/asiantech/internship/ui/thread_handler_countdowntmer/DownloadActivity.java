package vn.asiantech.internship.ui.thread_handler_countdowntmer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;

/**
 * Created by tiboo on 20/12/2017.
 */

public class DownloadActivity extends AppCompatActivity {
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ViewPager viewPager = findViewById(R.id.viewPagerContain);
        mTabLayout = findViewById(R.id.tabLayout);
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(new FirstFragment(), "Send");
        fragmentAdapter.addFragment(new SecondFragment(), "Result");
        viewPager.setAdapter(fragmentAdapter);
        mTabLayout.setupWithViewPager(viewPager);
    }
}
