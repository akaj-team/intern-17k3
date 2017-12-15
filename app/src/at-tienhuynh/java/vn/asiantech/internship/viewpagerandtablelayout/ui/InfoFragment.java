package vn.asiantech.internship.viewpagerandtablelayout.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import vn.asiantech.internship.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment implements View.OnClickListener, UpdateInfoDialogFragment.UpdateInfoDialogListener {
    private TextView mTvNameInfo;
    private TextView mTvTeamInfo;
    private TextView mTvLocationInfo;
    private Button mBtnUpdateInfo;
    private View mView;


    public InfoFragment() {
        //No-op
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_info, container, false);
        initViews();
        initListeners();
        return mView;
    }

    /**
     * Init Data
     */
    private void initViews() {
        mTvNameInfo = mView.findViewById(R.id.tvNameInfo);
        mTvTeamInfo = mView.findViewById(R.id.tvTeamInfo);
        mTvLocationInfo = mView.findViewById(R.id.tvLocationInfo);
        mBtnUpdateInfo = mView.findViewById(R.id.btnUpdateInfo);
    }

    /**
     * Init Listeners
     */
    private void initListeners() {
        mBtnUpdateInfo.setOnClickListener(this);
    }

    /**
     * Onclick Button
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnUpdateInfo:
                showUpdateDialog();
                break;
        }
    }

    /**
     * Show Update Dialog
     */
    private void showUpdateDialog() {
        FragmentManager fm = getFragmentManager();
        UpdateInfoDialogFragment updateDialogFragment = UpdateInfoDialogFragment.newInstance(null);
        updateDialogFragment.setTargetFragment(InfoFragment.this, 300);
        updateDialogFragment.setCancelable(false);
        updateDialogFragment.show(fm, null);
    }

    /**
     * Listen Data From DiaLog Fragment
     */
    @Override
    public void onFinishEditDialog(String name, String team, String address) {
        mTvNameInfo.setText(name);
        mTvTeamInfo.setText(team);
        mTvLocationInfo.setText(address);
    }

}
