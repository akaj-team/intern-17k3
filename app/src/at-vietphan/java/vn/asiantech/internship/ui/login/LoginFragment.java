package vn.asiantech.internship.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

/**
 * Class LoginFragment: screen when login
 */
public class LoginFragment extends Fragment {
    private Button mBtnSubmit;
    private EditText mEdtPhone;
    private EditText mEdtFullName;
    private EditText mEdtMail;
    private CheckBox mChkTermsAccept;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login, container, false);
        mChkTermsAccept = view.findViewById(R.id.chkTermsAccept);
        mBtnSubmit = view.findViewById(R.id.btnSubmit);
        mEdtPhone = view.findViewById(R.id.tvPhone);
        mEdtFullName = view.findViewById(R.id.edtFullName);
        mEdtMail = view.findViewById(R.id.edtEmail);
        checkAllInputData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((LoginActivity) getActivity()).setVisibilityBackImageView(View.VISIBLE);
    }

    private void checkAllInputData() {
        checkInputEdt(mEdtPhone);
        checkInputEdt(mEdtFullName);
        checkInputEdt(mEdtMail);
        checkClickChk(mChkTermsAccept);
    }

    private void checkInputEdt(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No-op
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No-op
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(mEdtPhone.getText()) && !TextUtils.isEmpty(mEdtFullName.getText()) && !TextUtils.isEmpty(mEdtMail.getText()) && mChkTermsAccept.isChecked()) {
                    mBtnSubmit.setSelected(true);
                } else {
                    mBtnSubmit.setSelected(false);
                }
            }
        });
    }

    private void checkClickChk(CheckBox checkBox) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!TextUtils.isEmpty(mEdtPhone.getText()) && !TextUtils.isEmpty(mEdtFullName.getText()) && !TextUtils.isEmpty(mEdtMail.getText()) && b) {
                    mBtnSubmit.setSelected(true);
                } else {
                    mBtnSubmit.setSelected(false);
                }
            }
        });
    }
}
