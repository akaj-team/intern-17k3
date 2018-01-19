package vn.asiantech.internship.ui.viewpager.broadcast;

import android.annotation.SuppressLint;
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

public class BroadCastReceiverActivity extends AppCompatActivity {
    private ImageView mImgBattery;
    private TextView mTvPercent;
    private TextView mTvPlugin;
    private TextView mTvHealth;
    private TextView mTvState;
    private TextView mTvTechnology;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast_receiver);
        initViews();
        batteryStatus();
    }

    private void initViews() {
        mImgBattery = findViewById(R.id.imgBattery);
        mTvPercent = findViewById(R.id.tvPercent);
        mTvPlugin = findViewById(R.id.tvPlugin);
        mTvHealth = findViewById(R.id.tvHealth);
        mTvState = findViewById(R.id.tvState);
        mTvTechnology = findViewById(R.id.tvTechnology);
    }

    private void batteryStatus() {
        BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                batteryLevel(intent);
                batteryPlugin();
                batteryHealth(intent);
                deviceStatus(intent);
                batteryTechnology(intent);
                batteryAnimationCharging(intent);
            }
        };
        registerReceiver(batteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    private void batteryAnimationCharging(Intent intent) {
        int deviceStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        if (deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING) {
            if (level < 100) {
                if (level <= 25) {
                    mImgBattery.setBackgroundResource(R.drawable.img_spin_animation_25);
                } else if (level <= 50) {
                    mImgBattery.setBackgroundResource(R.drawable.img_spin_animation_50);
                } else if (level <= 75) {
                    mImgBattery.setBackgroundResource(R.drawable.img_spin_animation_75);
                } else {
                    mImgBattery.setBackgroundResource(R.drawable.img_spin_animation_100);
                }
                AnimationDrawable animationDrawable = (AnimationDrawable) mImgBattery.getBackground();
                animationDrawable.start();
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void batteryLevel(Intent intent) {
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        if (level >= 0) {
            mTvPercent.setText(level + getString(R.string.percent));
        }
        if (level <= 25) {
            mImgBattery.setBackgroundResource(R.drawable.ic_battery_25);
        } else if (level <= 50) {
            mImgBattery.setBackgroundResource(R.drawable.ic_battery_50);
        } else if (level <= 75) {
            mImgBattery.setBackgroundResource(R.drawable.ic_battery_75);
        } else {
            mImgBattery.setBackgroundResource(R.drawable.ic_battery_100);
        }
    }

    private void batteryPlugin() {
        Intent batteryStatus = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        if (chargePlug >= 0) {
            if (chargePlug == BatteryManager.BATTERY_PLUGGED_USB) {
                mTvPlugin.setText(R.string.usb);
            } else if (chargePlug == BatteryManager.BATTERY_PLUGGED_AC) {
                mTvPlugin.setText(R.string.ac);
            } else if (chargePlug == BatteryManager.BATTERY_PLUGGED_WIRELESS) {
                mTvPlugin.setText(R.string.wireless);
            } else {
                mTvPlugin.setText(R.string.not_plugin);
            }
        }
    }

    private void batteryHealth(Intent intent) {
        int status = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
        if (status >= 0) {
            if (status == BatteryManager.BATTERY_HEALTH_COLD) {
                mTvHealth.setText(R.string.cold);
            } else if (status == BatteryManager.BATTERY_HEALTH_DEAD) {
                mTvHealth.setText(R.string.dead);
            } else if (status == BatteryManager.BATTERY_HEALTH_GOOD) {
                mTvHealth.setText(R.string.good);
            } else if (status == BatteryManager.BATTERY_HEALTH_OVERHEAT) {
                mTvHealth.setText(R.string.over_heat);
            } else if (status == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE) {
                mTvHealth.setText(R.string.over_voltage);
            } else if (status == BatteryManager.BATTERY_HEALTH_UNKNOWN) {
                mTvHealth.setText(R.string.uknown);
            } else if (status == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE) {
                mTvHealth.setText(R.string.failure);
            }
        }
    }

    private void deviceStatus(Intent intent) {
        int deviceStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        if (deviceStatus >= 0) {
            if (deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING) {
                mTvState.setText(R.string.charging);
            } else if (deviceStatus == BatteryManager.BATTERY_STATUS_DISCHARGING) {
                mTvState.setText(R.string.discharging);
            } else if (deviceStatus == BatteryManager.BATTERY_STATUS_FULL) {
                mTvState.setText(R.string.battery_full);
            } else if (deviceStatus == BatteryManager.BATTERY_STATUS_UNKNOWN) {
                mTvState.setText(R.string.uknown);
            } else if (deviceStatus == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
                mTvState.setText(R.string.not_charging);
            }
        }
    }

    private void batteryTechnology(Intent intent) {
        if (intent.getExtras() != null) {
            String technology = intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
            if (!"".equals(technology)) {
                mTvTechnology.setText(technology);
            }
        }
    }
}
