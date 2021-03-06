package vn.asiantech.internship.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import vn.asiantech.internship.R;

public class InforFragment extends Fragment implements TextWatcher, CompoundButton.OnCheckedChangeListener {
    private EditText mEdtPhone;
    private EditText mEdtFullName;
    private EditText mEdtEmail;
    private CheckBox mChkConfirm;
    private Button mBtnNext;

    public static InforFragment newInstance() {
        return new InforFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_infor, container, false);
        initView(view);
        initListener();
        return view;
    }

    public void initView(View view) {
        mEdtPhone = view.findViewById(R.id.edtPhoneNumber);
        mEdtFullName = view.findViewById(R.id.edtFullname);
        mEdtEmail = view.findViewById(R.id.edtEmail);
        mChkConfirm = view.findViewById(R.id.chkConfirm);
        mBtnNext = view.findViewById(R.id.btnNext);
    }

    public void initListener() {
        mEdtEmail.addTextChangedListener(this);
        mEdtFullName.addTextChangedListener(this);
        mEdtPhone.addTextChangedListener(this);
        mChkConfirm.setOnCheckedChangeListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((LoginActivity) getActivity()).setVisibleBackButton(View.VISIBLE);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (mChkConfirm.isChecked() && !TextUtils.isEmpty(mEdtPhone.getText().toString()) && !TextUtils.isEmpty(mEdtEmail.getText().toString()) && !TextUtils.isEmpty(mEdtFullName.getText().toString())) {
            mBtnNext.setSelected(true);
        } else {
            mBtnNext.setSelected(false);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (mChkConfirm.isChecked() && !TextUtils.isEmpty(mEdtPhone.getText().toString()) && !TextUtils.isEmpty(mEdtEmail.getText().toString()) && !TextUtils.isEmpty(mEdtFullName.getText().toString())) {
            mBtnNext.setSelected(true);
        } else {
            mBtnNext.setSelected(false);
        }
    }
}
