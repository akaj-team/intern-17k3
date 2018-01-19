package vn.asiantech.internship.ui.viewpager_tablayout.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import vn.asiantech.internship.R;

/**
 * Created by anh.quach on 1/9/18.
 * information battery activity
 */
public class InfoBatteryActivity extends AppCompatActivity {
    private TextView mTvPerBattery;
    private TextView mTvBatteryTechnology;
    private TextView mTvBatteryStatus;
    private TextView mTvBatteryHealth;
    private TextView mTvBatteryPlugged;
    private BroadcastReceiver mBroadcastReceiver;
    private ImageView mImgBatteryLevel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_battery);
        initViews();
        mBroadcastReceiver = new BatteryBroadcastReceiver();
    }

    private void initViews() {
        mTvPerBattery = findViewById(R.id.tvPerBattery);
        mTvBatteryTechnology = findViewById(R.id.tvBatteryTechnology);
        mTvBatteryStatus = findViewById(R.id.tvBatteryStatus);
        mTvBatteryHealth = findViewById(R.id.tvBatteryHealth);
        mTvBatteryPlugged = findViewById(R.id.tvBatteryPlugged);
        mImgBatteryLevel = findViewById(R.id.imgBatteryLevel);
    }

    @Override
    protected void onStart() {
        registerReceiver(mBroadcastReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(mBroadcastReceiver);
        super.onStop();
    }

    public class BatteryBroadcastReceiver extends BroadcastReceiver {
        private int mLevel;
        private String mTechnology;
        private int mPlugged;
        private int mHealth;
        private int mStatus;

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onReceive(Context context, Intent intent) {
            intentBroadcastReceiver(intent);
            setPlugged();
            setHealth();
            setStatus();
            mTvPerBattery.setText(getString(R.string.tv_level_battery, mLevel));
            mTvBatteryTechnology.setText(mTechnology);
        }

        private void intentBroadcastReceiver(Intent intent) {
            mLevel = intent.getIntExtra("level", 0);
            mTechnology = intent.getStringExtra("technology");
            mPlugged = intent.getIntExtra("plugged", -1);
            mHealth = intent.getIntExtra("health", 0);
            mStatus = intent.getIntExtra("status", 0);
        }

        private void setPlugged() {
            switch (mPlugged) {
                case 1:
                    mTvBatteryPlugged.setText(R.string.tv_ac);
                    break;
                case 2:
                    mTvBatteryPlugged.setText(R.string.tv_usb);
                    break;
                default:
                    mTvBatteryPlugged.setText("");
            }
        }

        private void setHealth() {
            switch (mHealth) {
                case 1:
                    mTvBatteryHealth.setText(R.string.tv_unknown);
                    break;
                case 2:
                    mTvBatteryHealth.setText(R.string.tv_good);
                    break;
                case 3:
                    mTvBatteryHealth.setText(R.string.tv_overheated);
                    break;
                case 4:
                    mTvBatteryHealth.setText(R.string.tv_dead);
                    break;
                case 5:
                    mTvBatteryHealth.setText(R.string.tv_overvoltage);
                    break;
                case 6:
                    mTvBatteryHealth.setText(R.string.tv_failed);
            }
        }

        private void setStatus() {
            switch (mStatus) {
                case 1:
                    mTvBatteryStatus.setText(R.string.tv_unknown);
                    break;
                case 2:
                    mTvBatteryStatus.setText(R.string.tv_charging);
                    checkLevel(R.drawable.animation_list_charing_level1, R.drawable.animation_list_charing_level1,
                            R.drawable.animation_list_charing_level2, R.drawable.animation_list_charing_level3);
                    AnimationDrawable frameAnimation = (AnimationDrawable) mImgBatteryLevel.getBackground();
                    frameAnimation.start();
                    break;
                case 3:
                    mTvBatteryStatus.setText(R.string.tv_discharging);
                    checkLevel(R.drawable.ic_battery_level1, R.drawable.ic_battery_level2, R.drawable.ic_battery_level3, R.drawable.ic_battery_level4);
                    break;
                case 4:
                    mTvBatteryStatus.setText(R.string.tv_notcharging);
                    checkLevel(R.drawable.ic_battery_level1, R.drawable.ic_battery_level2, R.drawable.ic_battery_level3, R.drawable.ic_battery_level4);
                    break;
                case 5:
                    mTvBatteryStatus.setText(R.string.tv_full);
                    mImgBatteryLevel.setBackgroundResource(R.drawable.ic_battery_level4);
            }
        }

        private void checkLevel(int Imgresource1, int Imgresource2, int Imgresource3, int Imgresource4) {
            if (mLevel < 25) {
                mImgBatteryLevel.setBackgroundResource(Imgresource1);
            }
            if (mLevel >= 25 && mLevel < 50) {
                mImgBatteryLevel.setBackgroundResource(Imgresource2);
            }
            if (mLevel >= 50 && mLevel < 75) {
                mImgBatteryLevel.setBackgroundResource(Imgresource3);
            }
            if (mLevel >= 75) {
                mImgBatteryLevel.setBackgroundResource(Imgresource4);
            }
        }
    }
}
