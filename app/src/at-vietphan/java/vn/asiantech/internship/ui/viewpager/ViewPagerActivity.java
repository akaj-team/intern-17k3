package vn.asiantech.internship.ui.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager.fragments.StepOneFragment;
import vn.asiantech.internship.ui.viewpager.fragments.StepThreeFragment;
import vn.asiantech.internship.ui.viewpager.fragments.StepTwoFragment;

/**
 * Created by vietphan on 13/12/2017.
 * Class ViewPagerActivity
 */
public class ViewPagerActivity extends AppCompatActivity {
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager mViewPager;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        initViews();
        initFragment();
        initAdapter();
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewPager);
        mFragments = new ArrayList<>();
    }

    private void initFragment() {
        mFragments.add(Fragment.instantiate(this, StepOneFragment.class.getName()));
        mFragments.add(Fragment.instantiate(this, StepTwoFragment.class.getName()));
        mFragments.add(Fragment.instantiate(this, StepThreeFragment.class.getName()));
    }

    private void initAdapter() {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mViewPagerAdapter);
    }
}
