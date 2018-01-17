package vn.asiantech.internship.ui.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import vn.asiantech.internship.R;

public class BroadcastActivity extends AppCompatActivity {

    private TextView mBatteryLevelText;
    private ImageView mImageView;
    private TextView mBatteryStatus;
    private TextView mBatteryHealth;
    private TextView mBatteryPlug;
    private TextView mBatteryTechnology;
    private AnimationDrawable mFrameAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        initViews();
        batteryLevelReceiver();
    }

    //Register Broadcast Receiver
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String technology = intent.getStringExtra("technology");
            int plugged = intent.getIntExtra("plugged", -1);
            int health = intent.getIntExtra("health", 0);
            int status = intent.getIntExtra("status", 0);
            int level = intent.getIntExtra("level", -1);
            getPlug(plugged);
            getHealth(health);
            getStatus(status, level);
            mBatteryLevelText.setText(String.valueOf(level).concat("%"));
            mBatteryTechnology.setText(technology);
        }
    };

    //initView
    private void initViews() {
        mImageView = findViewById(R.id.imvBattery);
        mBatteryLevelText = findViewById(R.id.tvPercentBattery);
        mBatteryStatus = findViewById(R.id.tvStatus);
        mBatteryHealth = findViewById(R.id.tvHealth);
        mBatteryPlug = findViewById(R.id.tvPlugged);
        mBatteryTechnology = findViewById(R.id.tvTechnolory);
    }

    /**
     * Get Plugged Battery
     */
    private void getPlug(int pluggedBattery) {
        switch (pluggedBattery) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                mBatteryPlug.setText(R.string.battery_plugged_ac);
                break;
            case BatteryManager.BATTERY_PLUGGED_USB:
                mBatteryPlug.setText(R.string.battery_plugged_usb);
        }
    }

    /**
     * Get Health Battery
     */
    private void getHealth(int healthBattery) {
        switch (healthBattery) {
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                mBatteryHealth.setText(R.string.battery_unknown);
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                mBatteryHealth.setText(R.string.battery_heath_good);
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                mBatteryHealth.setText(R.string.battery_heath_heat);
                break;
            case BatteryManager.BATTERY_HEALTH_DEAD:
                mBatteryHealth.setText(R.string.battery_heath_dead);
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                mBatteryHealth.setText(R.string.battery_heath_over);
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                mBatteryHealth.setText(R.string.battery_heath_failure);
                break;
        }
    }

    /**
     * Register battery
     */
    private void batteryLevelReceiver() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mBroadcastReceiver, filter);
    }

    /**
     * Get Status Battery
     */
    private void getStatus(int status, int levelBattery) {
        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                mBatteryStatus.setText(R.string.battery_charging);
                setAnimationImage(levelBattery);
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                mBatteryStatus.setText(R.string.battery_discharging);
                mImageView.setBackgroundResource(0);
                mFrameAnimation.setVisible(false, true);
                mFrameAnimation.stop();
                setDefaultImage(levelBattery);
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                mBatteryStatus.setText(R.string.battery_not_charging);

            case BatteryManager.BATTERY_STATUS_FULL:
                mBatteryStatus.setText(R.string.battery_full);
                break;
            default:
                mBatteryStatus.setText(R.string.battery_unknown);
        }
    }

    /**
     * set Default Image
     */
    private void setDefaultImage(int levelBattery) {
        if (!mFrameAnimation.isRunning()) {
            if (levelBattery < 20) {
                mImageView.setBackgroundResource(R.drawable.ic_battery_charing_1);
            } else if (levelBattery > 20 && levelBattery < 50) {
                mImageView.setBackgroundResource(R.drawable.ic_battery_charing_2);
            } else if (levelBattery < 80) {
                mImageView.setBackgroundResource(R.drawable.ic_battery_charing_3);
            } else if (levelBattery == 100) {
                mImageView.setBackgroundResource(R.drawable.ic_battery_full);
            }
        }
    }

    /**
     * set Animation Image
     */
    private void setAnimationImage(int levelBattery) {
        if (levelBattery < 20) {
            mImageView.setBackgroundResource(R.drawable.bg_battery_20);
        } else if (levelBattery > 20 && levelBattery < 50) {
            mImageView.setBackgroundResource(R.drawable.bg_battery_50);
        } else if (levelBattery > 50 && levelBattery < 100) {
            mImageView.setBackgroundResource(R.drawable.bg_battery_80);
        }
        mFrameAnimation = (AnimationDrawable) mImageView.getBackground();
        mFrameAnimation.start();
    }
}
