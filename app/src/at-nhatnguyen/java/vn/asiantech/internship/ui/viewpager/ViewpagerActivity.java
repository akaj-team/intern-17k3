package vn.asiantech.internship.ui.viewpager;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Screen;

/**
 * Activity use for intro
 */
public class ViewpagerActivity extends AppCompatActivity {
    private List<Screen> mScreens;
    private ScreenPagerAdapter mScreenPagerAdapter;
    private ViewPager mViewPager;
    private int mPositionPager;
    private boolean mIsLastPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        initViews();
        initData();
        initAdapter();
        listenViewPager();
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewPager);
        TextView mTvSkip = findViewById(R.id.tvSkip);
        mTvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewpagerActivity.this);
                builder.setMessage(R.string.message_dialog)
                        .setPositiveButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //No-op
                            }
                        })
                        .setNegativeButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                startActivity(new Intent(ViewpagerActivity.this, ViewPagerMainActivity.class));
                            }
                        });
                builder.create();
                builder.show();
            }
        });
    }

    private void initData() {
        mScreens = new ArrayList<>();
        mScreens.add(new Screen("Screen 1", Color.GREEN));
        mScreens.add(new Screen("Screen 2", Color.RED));
        mScreens.add(new Screen("Screen 3", Color.YELLOW));
    }

    private void initAdapter() {
        mScreenPagerAdapter = new ScreenPagerAdapter(mScreens, this);
        mViewPager.setAdapter(mScreenPagerAdapter);
        PageIndicatorView pageIndicatorView = findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setAnimationType(AnimationType.DROP);
        pageIndicatorView.setViewPager(mViewPager);
    }

    private void listenViewPager() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int mLastPos;
            boolean isFinish = false;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                isFinish = mLastPos == mScreenPagerAdapter.getCount() - 1;
                mLastPos = position;
            }

            @Override
            public void onPageSelected(int position) {
                //No-op
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (isFinish) {
                    Intent intent = new Intent(ViewpagerActivity.this, ViewPagerMainActivity.class);
                    startActivity(intent);
                    isFinish = false;
                }
            }
        });
    }
}
