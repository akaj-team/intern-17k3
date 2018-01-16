package vn.asiantech.internship.viewpagerandtablelayout.ui;

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

public class BatteryActivity extends AppCompatActivity {

    private TextView mTvBatteryLevel;
    private TextView mTvBatteryStatus;
    private TextView mTvBatteryHeath;
    private TextView mTvBatteryTechnology;
    private TextView mTvBatteryPlugType;
    private ImageView mImgBattery;
    private AnimationDrawable mFrameAnimation = null;
    private boolean mIsRunningAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        initViews();
        // register Receiver
        registerBatteryLevelReceiver();
    }

    /**
     * init Views
     */
    private void initViews() {
        mTvBatteryLevel = findViewById(R.id.tvLevel);
        mTvBatteryStatus = findViewById(R.id.tvStatus);
        mTvBatteryHeath = findViewById(R.id.tvHealth);
        mTvBatteryTechnology = findViewById(R.id.tvTechnology);
        mTvBatteryPlugType = findViewById(R.id.tvPlugged);
        mImgBattery = findViewById(R.id.imgBaterry);
    }

    /**
     * set default Image
     *
     * @param levelBattery levelBattery
     */
    private void setDefaultImage(int levelBattery) {
        if (!mIsRunningAnimation) {
            if (levelBattery < 25) {
                mImgBattery.setBackgroundResource(R.drawable.ic_battery_1);
            } else if (levelBattery > 25 && levelBattery < 50) {
                mImgBattery.setBackgroundResource(R.drawable.ic_battery_2);
            } else if (levelBattery > 50 && levelBattery < 75) {
                mImgBattery.setBackgroundResource(R.drawable.ic_battery_3);
            } else if (levelBattery > 75 && levelBattery < 100) {
                mImgBattery.setBackgroundResource(R.drawable.ic_battery_4);
            }
        }
    }

    /**
     * set Animation for Image
     */
    private void setAnimationImg(int levelBattery) {
        if (levelBattery < 25) {
            mImgBattery.setBackgroundResource(R.drawable.bg_list_battery_100);
        } else if (levelBattery > 25 && levelBattery < 50) {
            mImgBattery.setBackgroundResource(R.drawable.bg_list_battery_75);
        } else if (levelBattery > 50 && levelBattery < 100) {
            mImgBattery.setBackgroundResource(R.drawable.bg_list_battery_50);
        }
        // Get the background, which has been compiled to an AnimationDrawable object.
        mFrameAnimation = (AnimationDrawable) mImgBattery.getBackground();
        // Start the animation (looped playback by default).
        mFrameAnimation.start();
        mIsRunningAnimation = true;
        // stop animation when level battery = 100
        if (levelBattery == 100) {
            mFrameAnimation.stop();
            mIsRunningAnimation = false;
        }
    }

    /**
     * register Battery Level Receiver
     */
    private void registerBatteryLevelReceiver() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mBroadcastReceiver, filter);
    }

    /**
     * register BroadcastReceiver
     */
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isPresent = intent.getBooleanExtra("present", false);
            String technology = intent.getStringExtra("technology");
            int plugged = intent.getIntExtra("plugged", -1);
            int scale = intent.getIntExtra("scale", -1);
            int health = intent.getIntExtra("health", 0);
            int status = intent.getIntExtra("status", 0);
            int rawLevel = intent.getIntExtra("level", -1);
            int level = 0;
            if (isPresent) {
                if (rawLevel >= 0 && scale > 0) {
                    level = (rawLevel * 100) / scale;
                }
            }
            //set Technology
            mTvBatteryTechnology.setText(technology);
            // set plug type
            getPlugTypeString(plugged, mTvBatteryPlugType);
            //set Heath
            getHealthString(health, mTvBatteryHeath);
            // Set status
            getStatusString(status, mTvBatteryStatus, level);
            // set level
            mTvBatteryLevel.setText(String.valueOf(level));
        }
    };

    /**
     * get Plug Type
     *
     * @param plugged plugged
     */
    private void getPlugTypeString(int plugged, TextView tvPlugType) {
        switch (plugged) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                tvPlugType.setText(R.string.battery_status_ac);
                break;
            case BatteryManager.BATTERY_PLUGGED_USB:
                tvPlugType.setText(R.string.battery_status_usb);
                break;
            default:
                tvPlugType.setText(R.string.battery_status_unknown);
        }
    }

    /**
     * get health of battery
     *
     * @param health health
     */
    private void getHealthString(int health, TextView tvHeath) {

        switch (health) {
            case BatteryManager.BATTERY_HEALTH_GOOD:
                // good
                tvHeath.setText(R.string.battery_heath_good);
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                // heat
                tvHeath.setText(R.string.battery_heath_heat);
                break;
            case BatteryManager.BATTERY_HEALTH_DEAD:
                // dead
                tvHeath.setText(R.string.battery_heath_dead);
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                // over
                tvHeath.setText(R.string.battery_heath_over);
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                //failure
                tvHeath.setText(R.string.battery_heath_failure);
                break;
            default:
                // unknown
                tvHeath.setText(R.string.battery_status_unknown);
        }
    }

    /**
     * get Status Charging or not
     *
     * @param status status
     */
    private void getStatusString(int status, TextView tvStatus, int level) {

        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                tvStatus.setText(R.string.battery_status_charging);
                //set Animation
                setAnimationImg(level);
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                tvStatus.setText(R.string.battery_status_dis_charging);
                //stop animation
                if (mIsRunningAnimation) {
                    mImgBattery.setBackgroundResource(0);
                    mFrameAnimation.setVisible(false, true);
                    mFrameAnimation.stop();
                    mIsRunningAnimation = false;
                }
                setDefaultImage(level);
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                tvStatus.setText(R.string.battery_status_full);
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                tvStatus.setText(R.string.battery_status_not_charging);
                break;
            default:
                tvStatus.setText(R.string.battery_status_unknown);
        }
    }
}
