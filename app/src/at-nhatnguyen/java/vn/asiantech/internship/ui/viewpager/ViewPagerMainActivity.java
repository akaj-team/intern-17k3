package vn.asiantech.internship.ui.viewpager;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager.fragment.FragmentMainAdapter;

public class ViewPagerMainActivity extends AppCompatActivity {
    private ViewPager mViewPagerMain;
    private TabLayout mTabLayoutMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initViews();
        initAdapter();
    }

    private void initViews() {
        mViewPagerMain = findViewById(R.id.viewPagerMain);
        mTabLayoutMain = findViewById(R.id.tabLayoutMain);
    }

    private void initAdapter() {
        FragmentMainAdapter fragmentMainAdapter = new FragmentMainAdapter(getSupportFragmentManager());
        mViewPagerMain.setAdapter(fragmentMainAdapter);
        mTabLayoutMain.setupWithViewPager(mViewPagerMain);
    }
}
