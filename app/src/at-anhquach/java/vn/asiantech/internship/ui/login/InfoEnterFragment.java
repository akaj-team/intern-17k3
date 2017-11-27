package vn.asiantech.internship.ui.login;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
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
        TextView tvReadAgreeTerm = view.findViewById(R.id.tvReadAndAgreeTerm);
        SpannableString spannableString = new SpannableString(tvReadAgreeTerm.getText());
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {

            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.bgColor = Color.WHITE;
                ds.setARGB(255, 255, 255, 255);
                ds.setUnderlineText(false);
                ds.setColor(ContextCompat.getColor(getContext(), R.color.colorTextBlue));
            }
        };
        spannableString.setSpan(clickableSpan, 30, tvReadAgreeTerm.getText().length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvReadAgreeTerm.setText(spannableString);
        tvReadAgreeTerm.setMovementMethod(LinkMovementMethod.getInstance());
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
