package vn.asiantech.internship.ui.viewpager_tablayout.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager_tablayout.InfoBatteryActivity;

/**
 * Created by anh.quach on 1/9/18.
 * Fragment information battery
 */
public class InfoBatteryFragment extends Fragment {

    public InfoBatteryFragment newInstance() {
        return new InfoBatteryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_battery, container, false);
        Button mBtnInfoBattery = view.findViewById(R.id.btnInfoBattery);
        mBtnInfoBattery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), InfoBatteryActivity.class));
            }
        });
        return view;
    }
}
