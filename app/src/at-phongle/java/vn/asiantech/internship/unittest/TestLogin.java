package vn.asiantech.internship.unittest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.asiantech.internship.R;

/**
 * Created by phongle on 3/1/2561.
 * Activity Login
 */
public class TestLogin extends AppCompatActivity {
    private EditText mEdtUsername;
    private EditText mEdtPassword;
    private Button mBtnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unittest_login);
        initViews();
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUsername(mEdtUsername.getText().toString());
                validatePassword(mEdtUsername.getText().toString(), mEdtPassword.getText().toString());
            }
        });
    }

    private void initViews() {
        mEdtUsername = findViewById(R.id.edtUsername);
        mEdtPassword = findViewById(R.id.edtPassword);
        mBtnLogin = findViewById(R.id.btnLogin);
    }

    private void validateUsername(String username) {
        if (!ValidateUsername.checkLengthUsername(username)) {
            showToast(getResources().getString(R.string.check_length_username));
        } else if (!ValidateUsername.isAtLeastUpperCase(username)) {
            showToast(getResources().getString(R.string.at_least_uppercase_username));
        } else if (!ValidateUsername.isNonSpecialChar(username)) {
            showToast(getResources().getString(R.string.non_special_char_username));
        } else if (!ValidateUsername.isNonWhiteSpace(username)) {
            showToast(getResources().getString(R.string.non_space_username));
        } else if (!ValidateUsername.isAtMostTwoNumber(username)) {
            showToast(getResources().getString(R.string.at_most_two_num_username));
        } else if (!ValidateUsername.isUserNameUpperCaseOrLowerCase(username)) {
            showToast(getResources().getString(R.string.non_uppercase_or_lowercase));
        }
    }

    private void validatePassword(String username, String password) {
        if (!ValidatePassword.isDifferenceUsername(username, password)) {
            showToast(getResources().getString(R.string.check_difference_password));
        } else if (!ValidatePassword.checkMinLength(password)) {
            showToast(getResources().getString(R.string.check_length_password));
        } else if (!ValidatePassword.atLeastSpecialCharOrNumber(password)) {
            showToast(getResources().getString(R.string.at_least_special_char_or_num_password));
        } else if (!ValidatePassword.isAtLeastThreeUpperCase(password)) {
            showToast(getResources().getString(R.string.at_least_three_uppercase_password));
        } else if (!ValidatePassword.isNonRepeat(password)) {
            showToast(getResources().getString(R.string.non_repeat_password));
        } else if (!ValidatePassword.isNonWhiteSpace(password)) {
            showToast(getResources().getString(R.string.non_space_password));
        }
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
