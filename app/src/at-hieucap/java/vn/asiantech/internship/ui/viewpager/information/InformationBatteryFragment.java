package vn.asiantech.internship.ui.viewpager.information;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import vn.asiantech.internship.R;

/**
 * Create Information Battery Fragment use broadcast receiver
 */
public class InformationBatteryFragment extends Fragment {
    private static final int CLEAN_BATTERY = 0;
    private static final int PART_FOUR_BATTERY = 25;
    private static final int HALF_BATTERY = 50;
    private static final int THREE_PART_FOUR_BATTERY = 75;
    private static final int FULL_BATTERY = 100;
    private TextView mTvBatteryLevel;
    private TextView mTvBatteryTechnology;
    private TextView mTvBatteryPlugged;
    private TextView mTvBatteryHealth;
    private TextView mTvBatteryStatus;
    private ImageView mImgBatteryLevel;
    private AnimationDrawable mFrameAnimation;
    private BroadcastReceiver battery_receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isPresent = intent.getBooleanExtra(getString(R.string.text_present), false);
            String technology = intent.getStringExtra(getString(R.string.text_technology));
            int plugged = intent.getIntExtra(getString(R.string.text_plugged), -1);
            int scale = intent.getIntExtra(getString(R.string.text_scale), -1);
            int health = intent.getIntExtra(getString(R.string.text_health), 0);
            int status = intent.getIntExtra(getString(R.string.text_status), 0);
            int rawLevel = intent.getIntExtra(getString(R.string.text_level), -1);
            int level = 0;
            if (isPresent) {
                if (rawLevel >= 0 && scale > 0) {
                    level = (rawLevel * 100) / scale;
                }
                if (status == BatteryManager.BATTERY_STATUS_DISCHARGING || status == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
                    if (level >= CLEAN_BATTERY && level <= PART_FOUR_BATTERY) {
                        setBackgroundImage(R.drawable.ic_battery_level_1);
                    } else if (level > PART_FOUR_BATTERY && level <= HALF_BATTERY) {
                        setBackgroundImage(R.drawable.ic_battery_level_2);
                    } else if (level > HALF_BATTERY && level <= THREE_PART_FOUR_BATTERY) {
                        setBackgroundImage(R.drawable.ic_battery_level_3);
                    } else {
                        setBackgroundImage(R.drawable.ic_battery_level_4);
                    }
                } else if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
                    if (level >= CLEAN_BATTERY && level <= PART_FOUR_BATTERY) {
                        setAnimationImage(R.drawable.bg_animation_level_1);
                    } else if (level > PART_FOUR_BATTERY && level <= HALF_BATTERY) {
                        setAnimationImage(R.drawable.bg_animation_level_2);
                    } else if (level > HALF_BATTERY && level <= THREE_PART_FOUR_BATTERY) {
                        setAnimationImage(R.drawable.bg_animation_level_3);
                    } else if (level > THREE_PART_FOUR_BATTERY && level < FULL_BATTERY) {
                        setAnimationImage(R.drawable.bg_animation_level_3);
                    } else {
                        setBackgroundImage(R.drawable.ic_battery_level_4);
                    }
                    mFrameAnimation.start();
                } else {
                    mFrameAnimation.stop();
                }
                setBatteryLevelText(level + "%", technology, getPlugTypeString(plugged), getHealthString(health), getStatusString(status));
            } else {
                Toast.makeText(getActivity(), getString(R.string.battery_not_present),
                        Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void setBackgroundImage(int resource) {
        mImgBatteryLevel.setBackgroundResource(resource);
    }

    private void setAnimationImage(int resource) {
        mImgBatteryLevel.setBackgroundResource(resource);
        mFrameAnimation = (AnimationDrawable) mImgBatteryLevel.getBackground();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_broadcast_receiver, container, false);
        initViews(view);
        registerBatteryLevelReceiver();
        return view;
    }

    private void initViews(View view) {
        mTvBatteryLevel = view.findViewById(R.id.tvBatteryLevel);
        mTvBatteryTechnology = view.findViewById(R.id.tvBatteryTechnology);
        mTvBatteryPlugged = view.findViewById(R.id.tvBatteryPlugged);
        mTvBatteryHealth = view.findViewById(R.id.tvBatteryHealth);
        mTvBatteryStatus = view.findViewById(R.id.tvBatteryStatus);
        mImgBatteryLevel = view.findViewById(R.id.imgBattery);
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(battery_receiver);
        super.onDestroy();
    }

    private String getPlugTypeString(int plugged) {
        String plugType = getString(R.string.text_unknown);
        switch (plugged) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                plugType = getString(R.string.text_charging_ac);
                break;
            case BatteryManager.BATTERY_PLUGGED_USB:
                plugType = getString(R.string.text_charging_usb);
                break;
        }
        return plugType;
    }

    private String getHealthString(int health) {
        String healthString = getString(R.string.text_unknown);
        switch (health) {
            case BatteryManager.BATTERY_HEALTH_DEAD:
                healthString = getString(R.string.text_dead);
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                healthString = getString(R.string.text_good);
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                healthString = getString(R.string.text_over_voltage);
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                healthString = getString(R.string.text_over_heat);
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                healthString = getString(R.string.text_failure);
                break;
        }
        return healthString;
    }

    private String getStatusString(int status) {
        String statusString = getString(R.string.text_unknown);
        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                statusString = getString(R.string.text_charging);
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                statusString = getString(R.string.text_discharging);
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                statusString = getString(R.string.text_full);
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                statusString = getString(R.string.text_not_charging);
                break;
        }
        return statusString;
    }

    private void setBatteryLevelText(String textBatteryLevel, String textBatteryTechnology, String textBatteryPlugged, String textBatteryHealth, String textBatteryStatus) {
        mTvBatteryLevel.setText(textBatteryLevel);
        mTvBatteryTechnology.setText(textBatteryTechnology);
        mTvBatteryPlugged.setText(textBatteryPlugged);
        mTvBatteryHealth.setText(textBatteryHealth);
        mTvBatteryStatus.setText(textBatteryStatus);
    }

    private void registerBatteryLevelReceiver() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        getActivity().registerReceiver(battery_receiver, filter);
    }
}
