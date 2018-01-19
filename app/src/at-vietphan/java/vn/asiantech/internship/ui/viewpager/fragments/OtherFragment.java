package vn.asiantech.internship.ui.viewpager.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager.broadcast.BroadCastReceiverActivity;
import vn.asiantech.internship.ui.viewpager.service.MusicActivity;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class OtherFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    private Button mBtnBroadcastReceiver;
    private Button mBtnService;

    public static OtherFragment newInstance() {
        return new OtherFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other, container, false);
        initViews(view);
        initListeners();
        return view;
    }

    private void initViews(View view) {
        mBtnBroadcastReceiver = view.findViewById(R.id.btnBroadCastReceiver);
        mBtnService = view.findViewById(R.id.btnService);
    }

    private void initListeners() {
        mBtnBroadcastReceiver.setOnClickListener(this);
        mBtnService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnBroadCastReceiver) {
            startActivity(new Intent(getContext(), BroadCastReceiverActivity.class));
        } else {
            startActivity(new Intent(getContext(), MusicActivity.class));
        }
    }
}
