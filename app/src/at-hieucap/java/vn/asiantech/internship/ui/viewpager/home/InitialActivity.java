package vn.asiantech.internship.ui.viewpager.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager.information.HomeActivity;

/**
 * Create Initial Activity
 */
public class InitialActivity extends AppCompatActivity {
    private static final int CURRENT_PAGE = 0;
    private List<String> mListFragment = new ArrayList<>();
    private ViewPager mViewPager;
    private TextView mTvSkip;
    private InitialAdapter mAdapter;
    private PageIndicatorView mPageIndicatorView;
    private int mPositionPager;
    private boolean lastPager = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        initData();
        initAdapter();
        initViews();
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(CURRENT_PAGE);
        mPageIndicatorView.setViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (lastPager && position == mAdapter.getCount() - 1) {
                    startActivity();
                }
                mPositionPager = position;
                lastPager = false;
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    mTvSkip.setVisibility(View.INVISIBLE);
                } else {
                    mTvSkip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    if (mPositionPager == mAdapter.getCount() - 1) {
                        lastPager = true;
                    }
                }
            }
        });
        initListeners();
    }

    private void startActivity() {
        startActivity(new Intent(this, HomeActivity.class));
    }

    private void initData() {
        mListFragment.add(getResources().getString(R.string.tv_1));
        mListFragment.add(getResources().getString(R.string.tv_2));
        mListFragment.add(getResources().getString(R.string.tv_3));
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewPagerContain);
        mTvSkip = findViewById(R.id.tvSkip);
        mPageIndicatorView = findViewById(R.id.pageIndicatorView);
    }

    private void initAdapter() {
        mAdapter = new InitialAdapter(InitialActivity.this, mListFragment);
    }

    private void initListeners() {
        mTvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InitialActivity.this);
                builder.setMessage(R.string.message)
                        .setPositiveButton(R.string.event_cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        })
                        .setNegativeButton(R.string.event_submit, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                startActivity();
                            }
                        });
                builder.create();
                builder.show();
            }
        });
    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}
