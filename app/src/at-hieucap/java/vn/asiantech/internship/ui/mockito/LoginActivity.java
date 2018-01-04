package vn.asiantech.internship.ui.mockito;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
        if (isValidateUserName(mEdtUserName.getText().toString()) &&
                validatePassWord(mEdtPassword.getText().toString().trim())) {
            showToast(getString(R.string.test_done));
        }
    }

    private void initViews() {
        mEdtUserName = findViewById(R.id.edtUserName);
        mEdtPassword = findViewById(R.id.edtPassword);
        mBtnLogin = findViewById(R.id.btnLogin);
    }

    private boolean isValidateUserName(String userName) {
        if (userName.length() == 0) {
            showToast(getString(R.string.user_name_empty));
        } else if (!UsernameValidationTest.checkLength(userName)) {
            showToast(getString(R.string.test_user_name_length));
        } else if (!UsernameValidationTest.checkCapitalLetter(userName)) {
            showToast(getString(R.string.test_user_name_capital_letter));
        } else if (!UsernameValidationTest.checkHaveSpace(userName)) {
            showToast(getString(R.string.test_user_name_have_space));
        } else if (!UsernameValidationTest.checkCharacters(userName)) {
            showToast(getString(R.string.test_user_name_special_character));
        } else if (!UsernameValidationTest.checkDigitNumber(userName)) {
            showToast(getString(R.string.test_user_name_digit_number));
        } else if (!UsernameValidationTest.checkUpperCaseOrLowerCase(userName)) {
            showToast(getString(R.string.test_user_name_upper_case_or_lower_case));
        } else {
            return true;
        }
        return false;
    }

    private boolean validatePassWord(String password) {
        if (password.length() == 0) {
            showToast(getString(R.string.password_empty));
        } else if (!PasswordValidationTest.checkLength(password)) {
            showToast(getString(R.string.test_password_length));
        } else if (!PasswordValidationTest.isDifferentUserName(mEdtUserName.getText().toString(), password)) {
            showToast(getString(R.string.test_password_different_user_name));
        } else if (PasswordValidationTest.checkCharacterSpecialAndDigitNumber(password)) {
            showToast(getString(R.string.test_password_character_special_and_digit_number));
        } else if (!PasswordValidationTest.isHaveSpace(password)) {
            showToast(getString(R.string.test_password_have_space));
        } else if (!PasswordValidationTest.checkCapitalLetter(password)) {
            showToast(getString(R.string.test_password_capital_letter));
        } else if (!PasswordValidationTest.checkSameCharacter(password)) {
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
