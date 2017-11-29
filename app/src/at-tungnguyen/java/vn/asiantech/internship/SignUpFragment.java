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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class    SignUpFragment extends Fragment {
    private TextView mTvNumber;
    private Button mBtnSingUp;
    private EditText mEdtNumber;
    private EditText mEdtEmail;
    private EditText mEdtFullname;
    private CheckBox mChkTerms;
    private ImageView mImgNext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_singup, container, false);
        mTvNumber = view.findViewById(R.id.tvTerm);
        mEdtEmail = view.findViewById(R.id.edtEmail);
        mEdtNumber = view.findViewById(R.id.edtPhoneNumBer);
        mChkTerms = view.findViewById(R.id.chkSignUp);
        mEdtFullname = view.findViewById(R.id.edtFullName);
        mImgNext = view.findViewById(R.id.imgSignUp);
        initListenter();
        ((MainActivity) getActivity()).getBack().setVisibility(View.VISIBLE);
        return view;
    }

    private void initListenter() {
        mChkTerms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (!TextUtils.isEmpty(mEdtNumber.getText()) && !TextUtils.isEmpty(mEdtEmail.getText()) && !TextUtils.isEmpty(mEdtFullname.getText())) {
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
        mEdtFullname.addTextChangedListener(new TextWatcher() {
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
        if (!TextUtils.isEmpty(mEdtNumber.getText()) && !TextUtils.isEmpty(mEdtEmail.getText()) && !TextUtils.isEmpty(mEdtFullname.getText()) && mChkTerms.isChecked()) {
            mImgNext.setSelected(true);
        } else {
            mImgNext.setSelected(false);
        }
    }
}
