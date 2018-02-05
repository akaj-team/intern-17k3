package vn.asiantech.internship.viewpagerandtablelayout.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import vn.asiantech.internship.R;

/**
 * Another Fragment have ex W8
 */
public class AnotherFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private Button mBtnBatteryInfo;
    private Button mBtnPlayMusic;

    public AnotherFragment() {
        // No-opp
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_another, container, false);
        //initViews
        initViews();
        // initListeners
        initListeners();
        return mView;
    }

    /**
     * init Views
     */
    private void initViews() {
        mBtnBatteryInfo = mView.findViewById(R.id.btnBatteryInfo);
        mBtnPlayMusic = mView.findViewById(R.id.btnPlayMusic);
    }

    /**
     * init Listeners
     */
    private void initListeners() {
        mBtnBatteryInfo.setOnClickListener(this);
        mBtnPlayMusic.setOnClickListener(this);
    }

    /**
     * Onclick Button
     *
     * @param view view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBatteryInfo:
                startActivity(new Intent(getActivity(), BatteryActivity.class));
                break;
            case R.id.btnPlayMusic:
                startActivity(new Intent(getActivity(), PlayMusicActivity.class));
        }
    }
}
