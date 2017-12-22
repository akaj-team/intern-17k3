package vn.asiantech.internship.viewpager.tutorial;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import me.relex.circleindicator.CircleIndicator;
import vn.asiantech.internship.R;
import vn.asiantech.internship.viewpager.ViewPagerMainActivity;

/**
 * Created by phongle on 14/12/2560.
 * Activity How to Used
 */
public class GuideActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private GuideAdapter mHowToUsedAdapter;
    private int mListColor[] = {R.drawable.bg_step1, R.drawable.bg_step2, R.drawable.bg_step3};
    private String mListStep[] = {"Step 1", "Step 2", "Step 3"};
    private boolean mIsLastView;
    private int mLastView;
    private TextView mTvSkip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initViews();
        initAdapter();
        initCircleIndicator();
        openDialog();
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewPagerHowToUsed);
        mTvSkip = findViewById(R.id.tvSkip);
    }

    private void initAdapter() {
        mHowToUsedAdapter = new GuideAdapter(this, mListStep, mListColor);
        mViewPager.setAdapter(mHowToUsedAdapter);
    }

    private void initCircleIndicator() {
        CircleIndicator circleIndicator = findViewById(R.id.circleIndicator);
        circleIndicator.setViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mIsLastView = (mLastView == (mHowToUsedAdapter.getCount() - 1));
                mLastView = position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mIsLastView) {
                    Intent intent = new Intent(GuideActivity.this, ViewPagerMainActivity.class);
                    startActivity(intent);
                    mIsLastView = false;
                }
            }
        });
    }

    private void openDialog() {
        mTvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GuideActivity.this);
                alertDialogBuilder.setTitle(getResources().getString(R.string.alert_skip_title));
                alertDialogBuilder
                        .setMessage(getResources().getString(R.string.alert_skip_message))
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(GuideActivity.this, ViewPagerMainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }
}
