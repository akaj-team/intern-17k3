package vn.asiantech.internship.ui.viewpager;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Step;
import vn.asiantech.internship.ui.viewpager.adapter.SliderStepAdapter;

/**
 * Created by vietphan on 13/12/2017.
 * Class SliderActivity
 */
public class SliderActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private PagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private List<Step> mSteps;
    private int selectedPageIndex = 0;
    private boolean pageEnd;
    private TextView mTvSkip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        initViews();
        initData();
        initAdapter();
        PageIndicatorView pageIndicatorView = findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setViewPager(mViewPager);
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewPager);
        mTvSkip = findViewById(R.id.btnSkip);
        mSteps = new ArrayList<>();
    }

    private void initData() {
        mSteps.add(new Step("Step 1", Color.RED));
        mSteps.add(new Step("Step 2", Color.GREEN));
        mSteps.add(new Step("Step 3", Color.BLUE));
    }

    private void initAdapter() {
        mPagerAdapter = new SliderStepAdapter(mSteps);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(this);
        mTvSkip.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.skip_all_step);
        builder.setMessage(R.string.do_you_want_to_skip);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(SliderActivity.this, TabLayoutActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // No-op
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (pageEnd && position == selectedPageIndex) {
            startActivity(new Intent(SliderActivity.this, TabLayoutActivity.class));
        }
    }

    @Override
    public void onPageSelected(int position) {
        selectedPageIndex = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_DRAGGING) {
            if (selectedPageIndex == mPagerAdapter.getCount() - 1) {
                pageEnd = true;
            }
        }
    }
}
