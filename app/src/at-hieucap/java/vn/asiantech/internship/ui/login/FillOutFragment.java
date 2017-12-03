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
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_fill_out, container, false);

        initView();
        addOnClickListener();
        return mView;
    }

    private void initView() {
        mEdtPhone = mView.findViewById(R.id.edtPhone);
        mImgNext = mView.findViewById(R.id.imgNext);
        mChkTermAccept = mView.findViewById(R.id.chkSignUp);
        mEdtEmail = mView.findViewById(R.id.edtEmail);
        mEdtFullName = mView.findViewById(R.id.edtFullName);
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
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mImgNext.setSelected(checkSignUp(mEdtPhone, mEdtFullName, mEdtEmail, mChkTermAccept));
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.chkSignUp) {
            mImgNext.setSelected(checkSignUp(mEdtPhone, mEdtFullName, mEdtEmail, mChkTermAccept));
        }
    }
}
