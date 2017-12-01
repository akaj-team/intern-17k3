package vn.asiantech.internship.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import vn.asiantech.internship.R;

public class FillOutFragment extends Fragment implements TextWatcher, View.OnClickListener {
    private CheckBox mChkTermAccept;
    private EditText mEdtEmail;
    private EditText mEdtFullName;
    private ImageView mImgNext;
    private EditText mEdtPhone;
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fill_out, container, false);

        initView();
        addOnClickListener();
        return v;
    }

    private void initView() {
        mEdtPhone = v.findViewById(R.id.edtPhone);
        mImgNext = v.findViewById(R.id.imgNext);
        mChkTermAccept = v.findViewById(R.id.chkSignUp);
        mEdtEmail = v.findViewById(R.id.edtEmail);
        mEdtFullName = v.findViewById(R.id.edtFullName);
    }

    private void addOnClickListener() {
        mEdtEmail.addTextChangedListener(this);
        mEdtPhone.addTextChangedListener(this);
        mEdtFullName.addTextChangedListener(this);
        mChkTermAccept.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mChkTermAccept.setSelected(!mChkTermAccept.isSelected());
            }
        });

        mChkTermAccept.setOnClickListener(this);
    }

    private boolean checkSignUp(EditText edtPhone, EditText edtEmail, EditText edtFullName, CheckBox chkCheck) {
        return (((edtPhone.getText().toString().trim().length() != 0) && (edtEmail.getText().toString().trim().length() != 0) && (edtFullName.getText().toString().trim().length() != 0)) && chkCheck.isChecked());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        mImgNext.setSelected(checkSignUp(mEdtPhone, mEdtFullName, mEdtEmail, mChkTermAccept));
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mImgNext.setSelected(checkSignUp(mEdtPhone, mEdtFullName, mEdtEmail, mChkTermAccept));
    }

    @Override
    public void afterTextChanged(Editable s) {
        mImgNext.setSelected(checkSignUp(mEdtPhone, mEdtFullName, mEdtEmail, mChkTermAccept));
    }

    @Override
    public void onClick(View v) {
        mImgNext.setSelected(checkSignUp(mEdtPhone, mEdtFullName, mEdtEmail, mChkTermAccept));
    }
}
