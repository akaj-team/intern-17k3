package vn.asiantech.internship.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import vn.asiantech.internship.R;

/**
 * Created at 2017
 * Created by jackty on 23/11/2017.
 */
public class SignupFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private View mView;
    private EditText mEdtPhone;
    private EditText mEdtName;
    private EditText mEdtMail;
    private ImageView mImgNext;
    private CheckBox mChkAgree;

    public static SignupFragment newInstance() {
        Bundle args = new Bundle();
        SignupFragment fragment = new SignupFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_signup, container, false);
        // Inflate the layout for this fragment
        initViewAndListener();
        validateInputData();
        return mView;
    }

    private void initViewAndListener() {
        mEdtPhone = mView.findViewById(R.id.edtPhoneNum);
        mEdtName = mView.findViewById(R.id.edtFullName);
        mEdtMail = mView.findViewById(R.id.edtEmail);
        mImgNext = mView.findViewById(R.id.imgNext);
        mChkAgree = mView.findViewById(R.id.chkAgree);
        mChkAgree.setOnCheckedChangeListener(this);
        ((LoginActivity) getActivity()).setVisibilityImageBack(View.VISIBLE);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.chkAgree:
                if (b && !TextUtils.isEmpty(mEdtPhone.getText()) && !TextUtils.isEmpty(mEdtName.getText()) && !TextUtils.isEmpty(mEdtMail.getText())) {
                    mImgNext.setSelected(true);
                } else {
                    mImgNext.setSelected(false);
                }
                break;
        }
    }

    private void validateInputData() {
        mEdtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No-op
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkEmptyText();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No-op
            }
        });

        mEdtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No-op
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkEmptyText();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No-op
            }
        });

        mEdtMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No-op
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkEmptyText();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No-op
            }
        });
    }

    private void checkEmptyText() {
        mImgNext.setSelected(!TextUtils.isEmpty(mEdtPhone.getText()) && !TextUtils.isEmpty(mEdtName.getText()) && !TextUtils.isEmpty(mEdtMail.getText()) && mChkAgree.isChecked());
    }
}
