package vn.asiantech.internship.ui.viewpager.battery;

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
    private TextView mTvTypeBattery;
    private TextView mTvTypeJacks;
    private TextView mTvStatusBattery;
    private TextView mTvStatusChange;
    private TextView mTvPercent;
    private ImageView mImgBattery;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String technology = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
            int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
            int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            mTvPercent.setText(String.valueOf(level).concat(getString(R.string.percent)));
            mTvTypeBattery.setText(technology);
            setPlugged(plugged);
            setStatus(status);
            setHealth(health);
            setBattery(level, status);
        }
    };

    private void setBattery(int level, int status) {
        if (level == 100) {
            mImgBattery.setBackgroundResource(R.drawable.ic_battery_charging_full_red_500_48dp);
            mTvStatusChange.setText(R.string.status_full);
        } else if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
            mImgBattery.setBackgroundResource(R.drawable.animation_battery);
            AnimationDrawable animationDrawable = (AnimationDrawable) mImgBattery.getBackground();
            animationDrawable.start();
        } else {
            if (level <= 20) {
                mImgBattery.setBackgroundResource(R.drawable.ic_battery_charging_20_red_500_48dp);
            } else if (level <= 30) {
                mImgBattery.setBackgroundResource(R.drawable.ic_battery_charging_30_red_500_48dp);
            } else if (level <= 50) {
                mImgBattery.setBackgroundResource(R.drawable.ic_battery_charging_50_red_500_48dp);
            } else if (level <= 60) {
                mImgBattery.setBackgroundResource(R.drawable.ic_battery_charging_60_red_500_48dp);
            } else if (level <= 80) {
                mImgBattery.setBackgroundResource(R.drawable.ic_battery_charging_80_red_500_48dp);
            } else {
                mImgBattery.setBackgroundResource(R.drawable.ic_battery_charging_90_red_500_48dp);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        initViews();
        initBroadcastReceiver();
    }

    private void initViews() {
        mTvTypeBattery = findViewById(R.id.tvTypeBattery);
        mTvTypeJacks = findViewById(R.id.tvTypeJacks);
        mTvStatusBattery = findViewById(R.id.tvStatusBattery);
        mTvStatusChange = findViewById(R.id.tvStatusChange);
        mTvPercent = findViewById(R.id.tvPercent);
        mImgBattery = findViewById(R.id.imgBattery);
    }

    private void initBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    private void setPlugged(int plugged) {
        String result;
        switch (plugged) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                result = getResources().getString(R.string.plugged_ac);
                break;
            case BatteryManager.BATTERY_PLUGGED_USB:
                result = getResources().getString(R.string.plugged_usb);
                break;
            default:
                result = getResources().getString(R.string.unknow);
        }
        mTvTypeJacks.setText(result);
    }

    private void setStatus(int status) {
        String result = "";
        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                result = getResources().getString(R.string.status_changing);
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                result = getResources().getString(R.string.status_discharging);
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                result = getResources().getString(R.string.status_full);
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                result = getResources().getString(R.string.status_not_charging);
                break;
            case BatteryManager.BATTERY_STATUS_UNKNOWN:
                result = getResources().getString(R.string.unknow);
        }
        mTvStatusChange.setText(result);
    }

    private void setHealth(int health) {
        String result = "";
        switch (health) {
            case BatteryManager.BATTERY_HEALTH_GOOD:
                result = getResources().getString(R.string.health_good);
                break;
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                result = getResources().getString(R.string.unknow);
                break;
            case BatteryManager.BATTERY_HEALTH_DEAD:
                result = getResources().getString(R.string.health_dead);
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                result = getResources().getString(R.string.health_over_voltage);
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                result = getResources().getString(R.string.health_overheat);
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                result = getResources().getString(R.string.health_unspecified);
                mTvStatusBattery.setText(R.string.health_unspecified);
        }
        mTvStatusBattery.setText(result);
    }
}
