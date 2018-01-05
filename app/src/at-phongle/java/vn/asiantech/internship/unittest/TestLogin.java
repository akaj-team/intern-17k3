package vn.asiantech.internship.unittest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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
                if (validateUsername(mEdtUsername.getText().toString().trim())) {
                    if (validatePassword(mEdtUsername.getText().toString().trim(), mEdtPassword.getText().toString().trim())) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(
                                TestLogin.this);
                        builder.setTitle("Notification");
                        builder.setMessage("Access granted!");
                        builder.show();
                    }
                }
            }
        });
    }

    private void initViews() {
        mEdtUsername = findViewById(R.id.edtUsername);
        mEdtPassword = findViewById(R.id.edtPassword);
        mBtnLogin = findViewById(R.id.btnLogin);
    }

    private boolean validateUsername(String username) {
        if (!ValidateUsername.isCheckLengthUsername(username)) {
            showToast(getResources().getString(R.string.check_length_username));
            return false;
        } else if (!ValidateUsername.isAtLeastUpperCase(username)) {
            showToast(getResources().getString(R.string.at_least_uppercase_username));
            return false;
        } else if (!ValidateUsername.isNonSpecialChar(username)) {
            showToast(getResources().getString(R.string.non_special_char_username));
            return false;
        } else if (!ValidateUsername.isNonWhiteSpace(username)) {
            showToast(getResources().getString(R.string.non_space_username));
            return false;
        } else if (!ValidateUsername.isAtMostTwoNumber(username)) {
            showToast(getResources().getString(R.string.at_most_two_num_username));
            return false;
        } else if (!ValidateUsername.isUserNameUpperCaseOrLowerCase(username)) {
            showToast(getResources().getString(R.string.non_uppercase_or_lowercase));
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePassword(String username, String password) {
        if (!ValidatePassword.isDifferenceUsername(username, password)) {
            showToast(getResources().getString(R.string.check_difference_password));
            return false;
        } else if (!ValidatePassword.isCheckMinLength(password)) {
            showToast(getResources().getString(R.string.check_length_password));
            return false;
        } else if (!ValidatePassword.isAtLeastSpecialCharOrNumber(password)) {
            showToast(getResources().getString(R.string.at_least_special_char_or_num_password));
            return false;
        } else if (!ValidatePassword.isAtLeastThreeUpperCase(password)) {
            showToast(getResources().getString(R.string.at_least_three_uppercase_password));
            return false;
        } else if (!ValidatePassword.isNonRepeat(password)) {
            showToast(getResources().getString(R.string.non_repeat_password));
            return false;
        } else if (!ValidatePassword.isNonWhiteSpace(password)) {
            showToast(getResources().getString(R.string.non_space_password));
            return false;
        } else {
            return true;
        }
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
