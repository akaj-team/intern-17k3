package vn.asiantech.internship.ui.viewpager.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager.UpdateDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class InforFragment extends Fragment implements UpdateDialog.EditNameDialogListener {
    private TextView mTvNameUpdate;
    private TextView mTvOldUpdate;
    private TextView mTvAdressUpdate;
    private Button mBtnUpdate;

    public InforFragment() {
        //No-op
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infor2, container, false);
        initViews(view);
        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDialog updateDialog = UpdateDialog.newInstance();
                updateDialog.setRetainInstance(true);
                updateDialog.setTargetFragment(InforFragment.this, 300);
                updateDialog.show(getFragmentManager(), "");
            }
        });
        return view;
    }

    private void initViews(View view) {
        mTvNameUpdate = view.findViewById(R.id.tvNameUpdate);
        mTvOldUpdate = view.findViewById(R.id.tvOldUpdate);
        mTvAdressUpdate = view.findViewById(R.id.tvAdressUpdate);
        mBtnUpdate = view.findViewById(R.id.btnUpdate);
    }

    @Override
    public void onFinishEditDialog(String inputName, String inputOld, String inputAdress) {
        if(TextUtils.isEmpty(inputName)){
            inputName = getContext().getString(R.string.no_name);
        }
        if (TextUtils.isEmpty(inputOld)){
            inputOld = getContext().getString(R.string.no_old);
        }
        if (TextUtils.isEmpty(inputAdress)){
            inputAdress = getContext().getString(R.string.no_house);
        }
        mTvNameUpdate.setText(inputName);
        mTvOldUpdate.setText(inputOld);
        mTvAdressUpdate.setText(inputAdress);
    }
}
