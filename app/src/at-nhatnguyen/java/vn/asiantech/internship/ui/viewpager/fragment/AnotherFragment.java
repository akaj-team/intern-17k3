package vn.asiantech.internship.ui.viewpager.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.MainActivity;
import vn.asiantech.internship.ui.viewpager.Battery.BatteryActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnotherFragment extends Fragment {
    private Button mBtnBattery;

    public AnotherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_another, container, false);
        mBtnBattery = view.findViewById(R.id.btnBattery);
        mBtnBattery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), BatteryActivity.class));
            }
        });
        return view;
    }

}
