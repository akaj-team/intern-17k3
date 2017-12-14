package vn.asiantech.internship.viewpagerandtablelayout.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import vn.asiantech.internship.R;

/**
 * Created at 2017
 * Created by jackty on 14/12/2017.
 */

public class UpdateInfoDialogFragment extends DialogFragment implements View.OnClickListener {
    private EditText mEdtName;
    private EditText mEdtTeam;
    private EditText mEdtLocation;
    private TextInputLayout mInputLayoutName;
    private TextInputLayout mInputLayouTeam;
    private TextInputLayout mInputLayoutLocation;
    private Button mBtnConfirm;
    private Button mBtnCancel;
    private View mView;

    public static UpdateInfoDialogFragment newInstance(String title) {
        Bundle args = new Bundle();
        UpdateInfoDialogFragment fragment = new UpdateInfoDialogFragment();
        args.putString("title", title);
        fragment.setArguments(args);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_update_info, container, false);
        initViews();
        addTextChanged();
        initListeners();
        return mView;
    }

    /**
     * Init Views
     */
    private void initViews() {
        mEdtName = mView.findViewById(R.id.edtNameInfo);
        mEdtTeam = mView.findViewById(R.id.edtTeamInfo);
        mEdtLocation = mView.findViewById(R.id.edtLocationInfo);
        mInputLayoutName = mView.findViewById(R.id.inputLayoutName);
        mInputLayouTeam = mView.findViewById(R.id.inputLayoutTeam);
        mInputLayoutLocation = mView.findViewById(R.id.inputLayoutLocation);
        mBtnConfirm = mView.findViewById(R.id.btnConfirmUpdate);
        mBtnCancel = mView.findViewById(R.id.btnCancelUpdate);
    }

    /**
     * Validate EditText Name
     */
    private boolean validateEditName() {
        if (mEdtName.getText().toString().trim().isEmpty()) {
            mInputLayoutName.setError(getResources().getString(R.string.edt_null));
            requestFocus(mEdtName);
            return false;
        } else {
            mInputLayoutName.setErrorEnabled(false);
        }
        return true;
    }

    /**
     * Validate EditText Name
     */
    private boolean validateEditTeam() {
        if (mEdtTeam.getText().toString().trim().isEmpty()) {
            mInputLayouTeam.setError(getResources().getString(R.string.edt_null));
            requestFocus(mEdtTeam);
            return false;
        } else {
            mInputLayouTeam.setErrorEnabled(false);
        }
        return true;
    }

    /**
     * Validate EditText Name
     */
    private boolean validateEditLocation() {
        if (mEdtLocation.getText().toString().trim().isEmpty()) {
            mInputLayoutLocation.setError(getResources().getString(R.string.edt_null));
            requestFocus(mEdtLocation);
            return false;
        } else {
            mInputLayoutLocation.setErrorEnabled(false);
        }
        return true;
    }

    /**
     * Init listeners
     */
    private void initListeners() {
        mBtnConfirm.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
    }

    /**
     * Add Text Changed
     */
    private void addTextChanged() {
        mEdtName.addTextChangedListener(new MyTextWatcher(mEdtName));
        mEdtTeam.addTextChangedListener(new MyTextWatcher(mEdtTeam));
        mEdtLocation.addTextChangedListener(new MyTextWatcher(mEdtLocation));
    }

    /**
     * Onclick Button
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnConfirmUpdate:
                submitForm();
                break;
        }
    }

    /**
     * Show Error Empty EditText
     */
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }

    /**
     * Submit Form to Save
     */
    private void submitForm() {
        if (validateEditName() && validateEditTeam() && validateEditLocation()) {
            sendBackResult();
        }
    }

    /**
     * Listen Update Info
     */
    public interface UpdateInfoDialogListener {
        void onFinishEditDialog(String name, String team, String address);
    }

    /**
     * Send data to Info Fragment
     */
    public void sendBackResult() {
        UpdateInfoDialogListener listener = (UpdateInfoDialogListener) getTargetFragment();
        listener.onFinishEditDialog(mEdtName.getText().toString(),
                mEdtTeam.getText().toString(),
                mEdtLocation.getText().toString());
        dismiss();

    }


    /**
     * TextWatcher For Editext
     */
    private class MyTextWatcher implements TextWatcher {
        private View mView;

        MyTextWatcher(View view) {
            mView = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (mView.getId()) {
                case R.id.edtNameInfo:
                    validateEditName();
                    break;
                case R.id.edtTeamInfo:
                    validateEditTeam();
                    break;
                case R.id.edtLocationInfo:
                    validateEditLocation();
                    break;
            }

        }
    }
}
