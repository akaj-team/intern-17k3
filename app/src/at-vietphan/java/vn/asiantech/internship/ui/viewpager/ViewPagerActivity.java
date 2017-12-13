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

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.R;
import vn.asiantech.internship.models.Step;

/**
 * Created by vietphan on 13/12/2017.
 * Class ViewPagerActivity
 */
public class ViewPagerActivity extends AppCompatActivity implements PageAdapter.OnItemClickListener {
    private PagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private List<Step> mSteps;
    private int selectedPageIndex = 0;
    private boolean pageEnd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        initViews();
        initData();
        initAdapter();
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewPager);
        mSteps = new ArrayList<>();
    }

    private void initData() {
        mSteps.add(new Step("Step 1", Color.RED));
        mSteps.add(new Step("Step 2", Color.GREEN));
        mSteps.add(new Step("Step 3", Color.BLUE));
    }

    private void initAdapter() {
        mPagerAdapter = new PageAdapter(mSteps, this);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (pageEnd && position == selectedPageIndex) {
                    pageEnd = false;
                    startActivity(new Intent(ViewPagerActivity.this, DictionaryActivity.class));
                } else {
                    pageEnd = false;
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
        });
    }

    @Override
    public void onSkipClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Skip step");
        builder.setMessage("Do you want to skip this step?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ViewPagerActivity.this, DictionaryActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // No-op
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
