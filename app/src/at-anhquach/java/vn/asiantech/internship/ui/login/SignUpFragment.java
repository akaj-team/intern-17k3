package vn.asiantech.internship.ui.login;

import android.app.AlertDialog;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import vn.asiantech.internship.R;
import vn.asiantech.internship.validation.PasswordValidation;
import vn.asiantech.internship.validation.UserNameValidation;

public class SignUpFragment extends Fragment {
    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    private ImageView mImgNext;
    private EditText mEdtEnterPhoneNumber;
    private EditText mEdtUsername;
    private EditText mEdtPassword;
    private CheckBox mChkTermAccept;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        mImgNext = view.findViewById(R.id.imgNext);
        mEdtUsername = view.findViewById(R.id.edtEmail);
        mEdtPassword = view.findViewById(R.id.edtFullName);
        mEdtEnterPhoneNumber = view.findViewById(R.id.edtEnterPhoneNumber);
        mChkTermAccept = view.findViewById(R.id.chkTermAccept);
        TextView tvReadAgreeTerm = view.findViewById(R.id.tvReadAndAgreeTerm);
        SpannableString spannableString = new SpannableString(tvReadAgreeTerm.getText());
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                // No-op
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.bgColor = Color.WHITE;
                ds.setARGB(255, 255, 255, 255);
                ds.setUnderlineText(false);
                ds.setColor(ContextCompat.getColor(getContext(), R.color.colorBlue));
            }
        };
        spannableString.setSpan(clickableSpan, 30, tvReadAgreeTerm.getText().length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvReadAgreeTerm.setText(spannableString);
        tvReadAgreeTerm.setMovementMethod(LinkMovementMethod.getInstance());
        mEdtUsername.addTextChangedListener(new TextWatcher() {

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

        mEdtEnterPhoneNumber.addTextChangedListener(new TextWatcher() {

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

        mEdtPassword.addTextChangedListener(new TextWatcher() {

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

        mChkTermAccept.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (!TextUtils.isEmpty(mEdtEnterPhoneNumber.getText()) && !TextUtils.isEmpty(mEdtPassword.getText()) &&
                            !TextUtils.isEmpty(mEdtUsername.getText())) {
                        mImgNext.setSelected(true);
                    }
                } else {
                    mImgNext.setSelected(false);
                }
            }
        });
        signUpValidation();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((LoginActivity) getActivity()).setVisibilityImageBack(View.VISIBLE);
    }

    private void checkInputInfo() {
        if (!TextUtils.isEmpty(mEdtEnterPhoneNumber.getText()) && !TextUtils.isEmpty(mEdtPassword.getText()) &&
                !TextUtils.isEmpty(mEdtUsername.getText()) && mChkTermAccept.isChecked()) {
            mImgNext.setSelected(true);
        } else {
            mImgNext.setSelected(false);
        }
    }

    private void signUpValidation() {
        mImgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mEdtUsername.getText().toString();
                String password = mEdtPassword.getText().toString();
                if (TextUtils.equals(username, "") || TextUtils.equals(password, "")) {
                    showToast("Not enough data");
                } else if (!UserNameValidation.isIncorrectUserNameLength(username)) {
                    showToast("Length must more than 5 and less than 24");
                } else if (!UserNameValidation.isCapitalUserName(username)) {
                    showToast("User name must have at least a capital letter");
                } else if (!UserNameValidation.isContainSpecialChar(username)) {
                    showToast("User name can't contain special letter and space");
                } else if (!UserNameValidation.isMostTwoDigits(username)) {
                    showToast("User name have most two digit");
                } else if (!PasswordValidation.isDifferentUserName(password, username)) {
                    showToast("Password must different username");
                } else if (!PasswordValidation.isContainPasswordSpace(password)) {
                    showToast("Password don't contain space");
                } else if (PasswordValidation.isContainSpecialCharOrNumber(password)) {
                    showToast("Password must contain at least a specical letter or a number letter");
                } else if (PasswordValidation.isIncorrectPasswordLenght(password)) {
                    showToast("Passwords have at least seven letter and contain at most two duplicate letters");
                } else if (!PasswordValidation.isAtLeastThreeCapitalChar(password)) {
                    showToast("Password must have at least three capital letter");
                } else {
                    showDialog();
                }
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Login successfully").setTitle("Done");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
