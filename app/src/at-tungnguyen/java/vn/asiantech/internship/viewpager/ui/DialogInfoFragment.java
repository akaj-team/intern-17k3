package vn.asiantech.internship.viewpager.ui;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import vn.asiantech.internship.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogInfoFragment extends DialogFragment implements View.OnClickListener {

    private TextInputEditText mEdtNameInfo;
    private TextInputEditText mEdtAddressInfo;

    private Button mBtnCancel;
    private Button mBtnConfirm;
    private View mView;

    public static DialogInfoFragment newInstance(String data) {
        DialogInfoFragment fragment = new DialogInfoFragment();
        Bundle args = new Bundle();
        args.putString("data", data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_dialog_info, container, false);
        initViews();
        initListener();
        return mView;
    }


    /**
     * initView Dialog
     */
    private void initViews() {
        mEdtNameInfo = mView.findViewById(R.id.edtNameInfo);
        mEdtAddressInfo = mView.findViewById(R.id.edtAddressInfo);
        mBtnConfirm = mView.findViewById(R.id.btnDialogConfirm);
        mBtnCancel = mView.findViewById(R.id.btnDialogCancle);
    }

    private void initListener() {
        mBtnConfirm.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
    }
    /**
     *
     * Override onClick Button
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDialogConfirm:
                sendBackResult();
                break;
            case R.id.btnDialogCancle:
                getDialog().dismiss();
                break;

        }
    }
    /**
     * Send data
     */
    public void sendBackResult() {
        DialogFragment listener = (DialogFragment) getTargetFragment();
        listener.onFinishEditDialog(mEdtNameInfo.getText().toString().trim(), mEdtAddressInfo.getText().toString().trim());
        dismiss();
    }


    public interface DialogFragment {
        void onFinishEditDialog(String edtName, String edtAddress);
    }

}
