package vn.asiantech.internship.ui.viewpager_tablayout.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import vn.asiantech.internship.R;

public class InfoFragment extends Fragment implements DialogUpdateFragment.onDialogUpdateListener {
    private TextView mTvName;
    private TextView mTvPhone;
    private TextView mTvEmail;
    private Button mBtnUpdate;

    public static InfoFragment newInstance() {
        return new InfoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        initViews(view);
        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                DialogUpdateFragment dialogFragment = new DialogUpdateFragment();
                dialogFragment.setTargetFragment(InfoFragment.this, 0);
                dialogFragment.show(fragmentManager, "Update");
                dialogFragment.setCancelable(false);
            }
        });
        return view;
    }

    private void initViews(View view) {
        mTvName = view.findViewById(R.id.tvNameUser);
        mTvPhone = view.findViewById(R.id.tvPhoneNumberUser);
        mTvEmail = view.findViewById(R.id.tvEmailUser);
        mBtnUpdate = view.findViewById(R.id.btnUpdate);
    }

    @Override
    public void onDialogUpdateData(String name, String email, String phone) {
        mTvName.setText(name);
        mTvPhone.setText(phone);
        mTvEmail.setText(email);
    }
}
