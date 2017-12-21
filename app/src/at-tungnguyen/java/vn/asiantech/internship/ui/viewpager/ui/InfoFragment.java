package vn.asiantech.internship.ui.viewpager.ui;


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
public class InfoFragment extends Fragment implements DialogInfoFragment.DialogFragment {
    private Button mBtnUpdate;
    private TextView mTvNameInfo;
    private TextView mTvAddressInfo;
    private View mView;

    public InfoFragment() {
        //No-opp
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_info, container, false);
        initView();

        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        return mView;
    }

    private void initView() {
        mTvNameInfo = mView.findViewById(R.id.tvNameInfo);
        mTvAddressInfo = mView.findViewById(R.id.tvAddressInfo);
        mBtnUpdate = mView.findViewById(R.id.btnUpdate);
    }

    public void showDialog() {
        FragmentManager fm = getFragmentManager();
        DialogInfoFragment dialogInfoFragment = new DialogInfoFragment();
        dialogInfoFragment.setTargetFragment(InfoFragment.this, 300);
        dialogInfoFragment.show(fm, null);
    }

    @Override
    public void onFinishEditDialog(String edtName, String edtAddress) {
        mTvAddressInfo.setText(edtAddress);
        mTvNameInfo.setText(edtName);
    }
}
