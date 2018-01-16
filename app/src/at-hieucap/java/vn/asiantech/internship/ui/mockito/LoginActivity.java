package vn.asiantech.internship.ui.mockito;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.asiantech.internship.R;
import vn.asiantech.internship.unit_test.PasswordValidationTest;
import vn.asiantech.internship.unit_test.UsernameValidationTest;

/**
 * Create login activity unit test
 * Created by tiboo on 02/01/2018.
 */

public class LoginActivity extends AppCompatActivity {
    private EditText mEdtUserName;
    private EditText mEdtPassword;
    private Button mBtnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_test);
        initViews();
        setListener();
    }

    private void setListener() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private void validate() {
        if (isValidateUserName(mEdtUserName.getText().toString().trim()) && validatePassWord(mEdtPassword.getText().toString().trim())) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setMessage(R.string.success_login)
                    .setNegativeButton(R.string.event_cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
            builder.create();
            builder.show();
        }
    }

    private void initViews() {
        mEdtUserName = findViewById(R.id.edtUserName);
        mEdtPassword = findViewById(R.id.edtPassword);
        mBtnLogin = findViewById(R.id.btnLogin);
    }

    private boolean isValidateUserName(String userName) {
        if (userName.isEmpty()) {
            showToast(getString(R.string.username_empty));
        } else if (!UsernameValidationTest.isLengthAllowed(userName)) {
            showToast(getString(R.string.test_username_length));
        } else if (!UsernameValidationTest.isCapitalLetter(userName)) {
            showToast(getString(R.string.test_username_capital_letter));
        } else if (!UsernameValidationTest.isHaveSpace(userName)) {
            showToast(getString(R.string.test_username_have_space));
        } else if (!UsernameValidationTest.isCharacters(userName)) {
            showToast(getString(R.string.test_username_special_character));
        } else if (!UsernameValidationTest.isDigitNumber(userName)) {
            showToast(getString(R.string.test_username_digit_number));
        } else if (!UsernameValidationTest.isUpperCaseOrLowerCase(userName)) {
            showToast(getString(R.string.test_username_upper_case_or_lower_case));
        } else {
            return true;
        }
        return false;
    }

    private boolean validatePassWord(String password) {
        if (password.isEmpty()) {
            showToast(getString(R.string.password_empty));
        } else if (!PasswordValidationTest.isLengthAllowed(password)) {
            showToast(getString(R.string.test_password_length));
        } else if (!PasswordValidationTest.isDifferentUserName(mEdtUserName.getText().toString(), password)) {
            showToast(getString(R.string.test_password_different_username));
        } else if (PasswordValidationTest.isCharacterSpecialAndDigitNumber(password)) {
            showToast(getString(R.string.test_password_character_special_and_digit_number));
        } else if (!PasswordValidationTest.isHaveSpace(password)) {
            showToast(getString(R.string.test_password_have_space));
        } else if (!PasswordValidationTest.isCapitalLetter(password)) {
            showToast(getString(R.string.test_password_capital_letter));
        } else if (!PasswordValidationTest.isSameCharacter(password)) {
            showToast(getString(R.string.test_password_same_character));
        } else {
            return true;
        }
        return false;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
