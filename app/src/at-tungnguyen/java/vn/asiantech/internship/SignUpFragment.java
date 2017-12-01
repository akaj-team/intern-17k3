package vn.asiantech.internship;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class SignUpFragment extends Fragment {

    private EditText mEdtNumber;
    private EditText mEdtEmail;
    private EditText mEdtFullName;
    private CheckBox mChkTerms;
    private ImageView mImgNext;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_singup, container, false);
        mEdtEmail = view.findViewById(R.id.edtEmail);
        mEdtNumber = view.findViewById(R.id.edtPhoneNumber);
        mChkTerms = view.findViewById(R.id.chkSignUp);
        mEdtFullName = view.findViewById(R.id.edtFullName);
        mImgNext = view.findViewById(R.id.imgSignUp);
        initListener();
        return view;
    }

    private void initListener() {
        mChkTerms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (!TextUtils.isEmpty(mEdtNumber.getText()) && !TextUtils.isEmpty(mEdtEmail.getText()) && !TextUtils.isEmpty(mEdtFullName.getText())) {
                        mImgNext.setSelected(true);
                    }
                } else {
                    mImgNext.setSelected(false);
                }
            }
        });

        mEdtNumber.addTextChangedListener(new TextWatcher() {
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
                updateNextButtonStatus();
            }
        });

        mEdtFullName.addTextChangedListener(new TextWatcher() {
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
                updateNextButtonStatus();
            }
        });

        mEdtEmail.addTextChangedListener(new TextWatcher() {
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
                updateNextButtonStatus();
            }
        });
    }

    private void updateNextButtonStatus() {
        if (!TextUtils.isEmpty(mEdtNumber.getText()) && !TextUtils.isEmpty(mEdtEmail.getText()) && !TextUtils.isEmpty(mEdtFullName.getText()) && mChkTerms.isChecked()) {
            mImgNext.setSelected(true);
        } else {
            mImgNext.setSelected(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setVisibilityImageBack(View.VISIBLE);
    }
}
