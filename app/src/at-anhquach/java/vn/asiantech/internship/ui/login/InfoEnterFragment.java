package vn.asiantech.internship.ui.login;

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

public class InfoEnterFragment extends Fragment {
    private ImageView mImgNext;
    private EditText mEdtEnterPhoneNumber;
    private EditText mEdtEmail;
    private EditText mEdtFullName;
    private CheckBox mChkTermAccept;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_enter, container, false);

        mImgNext = view.findViewById(R.id.imgNext);
        mEdtEmail = view.findViewById(R.id.edtEmail);
        mEdtFullName = view.findViewById(R.id.edtFullName);
        mEdtEnterPhoneNumber = view.findViewById(R.id.edtEnterPhoneNumber);
        mChkTermAccept = view.findViewById(R.id.chkTermAccept);
        ((LoginActivity) getActivity()).getImgBack().setVisibility(View.VISIBLE);
        mEdtEmail.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //No-op
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //No-op
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkInputInfo();
            }
        });

        mEdtEnterPhoneNumber.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //No-op
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //No-op
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkInputInfo();
            }
        });

        mEdtFullName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //No-op
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //No-op
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkInputInfo();
            }
        });

        mChkTermAccept.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (!TextUtils.isEmpty(mEdtEnterPhoneNumber.getText()) && !TextUtils.isEmpty(mEdtFullName.getText()) &&
                            !TextUtils.isEmpty(mEdtEmail.getText())) {
                        mImgNext.setSelected(true);
                    }
                } else {
                    mImgNext.setSelected(false);
                }
            }
        });
        return view;
    }

    private void checkInputInfo() {
        if (!TextUtils.isEmpty(mEdtEnterPhoneNumber.getText()) && !TextUtils.isEmpty(mEdtFullName.getText()) &&
                !TextUtils.isEmpty(mEdtEmail.getText()) && mChkTermAccept.isChecked()) {
            mImgNext.setSelected(true);
        } else {
            mImgNext.setSelected(false);
        }
    }

}
