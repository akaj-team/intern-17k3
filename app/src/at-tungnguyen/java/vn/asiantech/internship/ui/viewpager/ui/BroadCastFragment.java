package vn.asiantech.internship.ui.viewpager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.broadcast.BroadcastActivity;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 10/01/2018.
 */

public class BroadCastFragment extends Fragment {
    private Button mBtnBattery;
    public BroadCastFragment() {
        //No-opp
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_broadcast, container, false);
       mBtnBattery = view.findViewById(R.id.btnBroadcast);
       mBtnBattery.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(getContext(), BroadcastActivity.class);
               startActivity(i);
           }
       });
        return view;
    }


}
