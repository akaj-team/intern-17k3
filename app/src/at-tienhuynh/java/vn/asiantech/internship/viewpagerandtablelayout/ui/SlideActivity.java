package vn.asiantech.internship.viewpagerandtablelayout.ui;

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
import vn.asiantech.internship.viewpagerandtablelayout.adapters.SlideAdapter;

/**
 * Created at 2017
 * Created by jackty on 13/12/2017.
 */
public class SlideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private static final int CURRENT_PAGE = 0;
    private List<String> mListStringSlides = new ArrayList<>();
    private ViewPager mViewPager;
    private TextView mTvSkipSlide;
    private SlideAdapter mSlideAdapter;
    private boolean mIsFinishSlideStep;
    private int mLastStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        initData();
        initViews();
        initAdapter();
        initListeners();
    }

    /**
     * Init Data to List Images
     */
    private void initData() {
        mListStringSlides.add(getResources().getString(R.string.tv_step_1));
        mListStringSlides.add(getResources().getString(R.string.tv_step_2));
        mListStringSlides.add(getResources().getString(R.string.tv_step_3));
    }

    /**
     * Init Views
     */
    private void initViews() {
        mViewPager = findViewById(R.id.viewPagerSlide);
        mTvSkipSlide = findViewById(R.id.tvSkipSlide);
    }

    /**
     * Init Adapter
     */
    private void initAdapter() {
        mSlideAdapter = new SlideAdapter(SlideActivity.this, mListStringSlides);
        mViewPager.setAdapter(mSlideAdapter);
        CircleIndicator indicator = findViewById(R.id.circleIndicator);
        indicator.setViewPager(mViewPager);
        mViewPager.setCurrentItem(CURRENT_PAGE);
    }

    /**
     * Init Listeners
     */
    private void initListeners() {
        mTvSkipSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        mViewPager.addOnPageChangeListener(this);
    }

    /**
     * Show Dialog When User Click Skip
     */
    private void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(getResources().getString(R.string.dialog_skip_title));
        dialogBuilder.setMessage(getResources().getString(R.string.dialog_skip_message));
        dialogBuilder.setCancelable(false);

        dialogBuilder.setPositiveButton(
                getResources().getString(R.string.dialog_skip_yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(SlideActivity.this, TabLayoutActivity.class));
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

    /**
     * On Page Change Listener
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mIsFinishSlideStep = mLastStep == mSlideAdapter.getCount() - 1;
        mLastStep = position;
    }

    @Override
    public void onPageSelected(int position) {
        //No-op
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mIsFinishSlideStep) {
            Intent intent = new Intent(SlideActivity.this, TabLayoutActivity.class);
            startActivity(intent);
            mIsFinishSlideStep = false;
        }
    }
}
