package vn.asiantech.internship.ui.viewpager_tablayout.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import me.relex.circleindicator.CircleIndicator;
import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager_tablayout.adapter.StepAdapter;

public class StepActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private String[] mStrs = {"Step 1", "Step 2", "Step 3"};
    private int[] mColors = {R.color.colorPink100, R.color.colorCyan300, R.color.colorIndigo300};
    private TextView mTvSkip;
    private boolean mIsLastStep;
    private int mLaststep;
    private StepAdapter mStepAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        initViews();
        mTvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(StepActivity.this);
                builder.setMessage(R.string.dialog_message_skip_step).setTitle(R.string.dialog_title_skip_step);
                builder.setPositiveButton(R.string.dialog_btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(StepActivity.this, TabLayoutActivity.class));
                    }
                });
                builder.setNegativeButton(R.string.dialog_btn_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        mStepAdapter = new StepAdapter(StepActivity.this, mStrs, mColors);
        mViewPager.setAdapter(mStepAdapter);
        CircleIndicator indicator = findViewById(R.id.circleIndicator);
        indicator.setViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mIsLastStep = (mLaststep == (mStepAdapter.getCount() - 1));
                mLaststep = position;
            }

            @Override
            public void onPageSelected(int position) {
            // No-op
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mIsLastStep) {
                    startActivity(new Intent(StepActivity.this, TabLayoutActivity.class));
                    mIsLastStep = false;
                }
            }
        });
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewPagerStep);
        mTvSkip = findViewById(R.id.tvSkip);
    }
}
