package vn.asiantech.internship.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import vn.asiantech.internship.R;

public class InfoFragment extends Fragment {
    private EditText mEdtPhoneNumber;
    private EditText mEdtUserName;
    private EditText mEdtEmail;
    private CheckBox mChkAcceptTerm;
    private ImageView mImgNext;

    public InfoFragment() {
        // No-op
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        mEdtPhoneNumber = view.findViewById(R.id.edtPhoneNumber);
        mEdtUserName = view.findViewById(R.id.edtUserName);
        mEdtEmail = view.findViewById(R.id.edtEmail);
        mChkAcceptTerm = view.findViewById(R.id.chkAcceptTerm);
        mImgNext = view.findViewById(R.id.imgNext);
        TextView tvMemo = view.findViewById(R.id.tvMemo);

        mEdtPhoneNumber.addTextChangedListener(new TextWatcher() {
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
                checkInputInfo();
            }
        });

        mEdtUserName.addTextChangedListener(new TextWatcher() {
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
                checkInputInfo();
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
                checkInputInfo();
            }
        });

        mChkAcceptTerm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (mEdtPhoneNumber.getText().length() != 0 && mEdtUserName.getText().length() != 0 && mEdtEmail.getText().length() != 0) {
                        mImgNext.setSelected(true);
                    }
                } else {
                    mImgNext.setSelected(false);
                }
            }
        });
        Spannable spannableString = new SpannableString(tvMemo.getText());
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.colorBlue)), 31, 51, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvMemo.setText(spannableString);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((LoginActivity) getActivity()).setVisibilityImageBack(View.VISIBLE);
    }

    private void checkInputInfo() {
        if (mEdtPhoneNumber.getText().length() != 0 && mEdtUserName.getText().length() != 0 && mEdtEmail.getText().length() != 0 && mChkAcceptTerm.isChecked()) {
            mImgNext.setSelected(true);
        } else {
            mImgNext.setSelected(false);
        }
    }
}
