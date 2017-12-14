package vn.asiantech.internship.viewpager.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import vn.asiantech.internship.R;
import vn.asiantech.internship.viewpager.adapter.ViewPagerAdapter;

public class ViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;
    private List<String> mStringList;
    private TextView mTvSkipSlide;
    private ViewPagerAdapter mViewPagerAdapter;
    private CircleIndicator mCircleIndicator;
    private int mLast;
    private boolean mIsFinishSlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        initData();
        initView();
        initListener();
    }


    private void initData() {
        mStringList = new ArrayList<>();
        mStringList.add(getResources().getString(R.string.tv_view1));
        mStringList.add(getResources().getString(R.string.tv_view2));
        mStringList.add(getResources().getString(R.string.tv_view3));
    }

    private void initView() {
        mTvSkipSlide = findViewById(R.id.tvSkip);
        mViewPager = findViewById(R.id.viewPager);
        mCircleIndicator = findViewById(R.id.indicator);
        mViewPagerAdapter = new ViewPagerAdapter(mStringList, this);
        mViewPager.setAdapter(mViewPagerAdapter);
        mCircleIndicator.setViewPager(mViewPager);
    }

    private void initListener() {
        mViewPager.addOnPageChangeListener(this);
        mTvSkipSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    /**
     * Override OnPageChangeListener
     *
     * @param position
     */

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mIsFinishSlide = mLast == mViewPagerAdapter.getCount() - 1;
        mLast = position;
    }

    @Override
    public void onPageSelected(int position) {
        //No-opp
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mIsFinishSlide) {
            Intent intent = new Intent(this, TabLayoutActivity.class);
            startActivity(intent);
            mIsFinishSlide = false;
        }
    }

    private void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(getResources().getString(R.string.dialog_title_skip));
        dialogBuilder.setMessage(getResources().getString(R.string.dialog_messanger_skip));
        dialogBuilder.setCancelable(false);

        dialogBuilder.setPositiveButton(
                getResources().getString(R.string.dialog_yes_skip),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(ViewPagerActivity.this, TabLayoutActivity.class));
                    }
                });

        dialogBuilder.setNegativeButton(
                getResources().getString(R.string.dialog_skip_no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertShowDialog = dialogBuilder.create();
        alertShowDialog.show();
    }
}
