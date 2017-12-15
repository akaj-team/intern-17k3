package vn.asiantech.internship.viewpager.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;

public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private static final int CURRENT_PAGE = 0;
    private List<String> mListFragment = new ArrayList<>();
    private ViewPager mViewPager;
    private TextView mTvSkip;
    private ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        initData();
        initViews();
        initAdapter();
        initListeners();
    }

    private void initData() {
        mListFragment.add(getResources().getString(R.string.tv_1));
        mListFragment.add(getResources().getString(R.string.tv_2));
        mListFragment.add(getResources().getString(R.string.tv_3));
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewPagerContain);
        mViewPager.setAdapter(mAdapter);
        mTvSkip = findViewById(R.id.tvSkip);
//        PageIndicatorView pageIndicatorView = findViewById(R.id.pageIndicatorView);
//        pageIndicatorView.setViewPager(mViewPager);
    }

    private void initAdapter() {
        mViewPager.setAdapter(new ViewPagerAdapter(HomeActivity.this, mListFragment));
        mViewPager.setCurrentItem(CURRENT_PAGE);

    }

    private void initListeners() {
        mTvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showDialog();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
