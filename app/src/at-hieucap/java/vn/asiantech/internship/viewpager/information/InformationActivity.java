package vn.asiantech.internship.viewpager.information;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;

public class InformationActivity extends AppCompatActivity {
private ViewPager mViewPager;
private TabLayout mTabLayout;
private InformationAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mViewPager = findViewById(R.id.viewPagerActivityHome);
        mTabLayout = findViewById(R.id.tlContain);

        setupViewPager(mViewPager);
    }

    public void setupViewPager(ViewPager mViewPager) {
        mAdapter = new InformationAdapter(getSupportFragmentManager());
        mAdapter.addFragment(new HomeFragment(), "Menu");
        mAdapter.addFragment(new InformationFragment(), "Information");
        mViewPager.setAdapter(mAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }

}
