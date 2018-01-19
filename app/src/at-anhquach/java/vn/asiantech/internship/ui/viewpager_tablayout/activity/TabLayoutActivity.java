package vn.asiantech.internship.ui.viewpager_tablayout.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager_tablayout.adapter.TabLayoutAdapter;
import vn.asiantech.internship.ui.viewpager_tablayout.fragment.HomeFragment;
import vn.asiantech.internship.ui.viewpager_tablayout.fragment.BroadcastReceiverAndServiceFragment;
import vn.asiantech.internship.ui.viewpager_tablayout.fragment.InfoFragment;

public class TabLayoutActivity extends AppCompatActivity {
    private ViewPager mViewPagerMain;
    private List<Fragment> mFragments = new ArrayList<>();
    private TabLayout mTabLayoutMain;
    private List<String> mFragmentTittles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        initViews();
        initAdapterViewPager();
        setUpTabLayout();
    }

    private void initViews() {
        mViewPagerMain = findViewById(R.id.viewPagerMain);
        mTabLayoutMain = findViewById(R.id.tabLayoutMain);
    }

    private void initAdapterViewPager() {
        FragmentManager manager = getSupportFragmentManager();
        TabLayoutAdapter mTabLayoutAdapter = new TabLayoutAdapter(manager, mFragments, mFragmentTittles);
        mTabLayoutAdapter.addFrag(new HomeFragment(), getString(R.string.title_tablayout_home));
        mTabLayoutAdapter.addFrag(new InfoFragment(), getString(R.string.title_tablayout_info));
        mTabLayoutAdapter.addFrag(new BroadcastReceiverAndServiceFragment(), getString(R.string.title_tablayout_info_battery));
        mViewPagerMain.setAdapter(mTabLayoutAdapter);
    }

    private void setUpTabLayout() {
        mTabLayoutMain.setupWithViewPager(mViewPagerMain);
    }
}
