package vn.asiantech.internship.ui.viewpager.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.asiantech.internship.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFragment extends DialogFragment implements View.OnClickListener, InformationDialogFragment.UpdateDialogListener {
    private TextView mTvName;
    private TextView mTvBirthday;
    private TextView mTvEmail;
    private TextView mTvUpdate;

    public static InformationFragment newInstance() {
        return new InformationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        initViews(view);
        initListener();
        return view;
    }

    private void initViews(View view) {
        mTvName = view.findViewById(R.id.tvName);
        mTvBirthday = view.findViewById(R.id.tvBirthday);
        mTvEmail = view.findViewById(R.id.tvEmail);
        mTvUpdate = view.findViewById(R.id.tvUpdate);
    }

    private void initListener() {
        mTvUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        FragmentManager fm = getFragmentManager();
        InformationDialogFragment dialogFragment = new InformationDialogFragment();
        dialogFragment.setTargetFragment(this, 0);
        dialogFragment.show(fm, getString(R.string.dialog_fragment));
    }

    @Override
    public void onUpdateDialog(String name, String birthday, String email) {
        mTvName.setText(name);
        mTvBirthday.setText(birthday);
        mTvEmail.setText(email);
    }
}

