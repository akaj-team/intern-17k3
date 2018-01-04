package vn.asiantech.internship.ui.mockito;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.asiantech.internship.R;
import vn.asiantech.internship.unit_test.PasswordValidation;
import vn.asiantech.internship.unit_test.UserNameValidation;

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
        setButtonLogin();
    }

    private void setButtonLogin() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private void validate() {
        if (validateUserName(mEdtUserName.getText().toString()) &&
                validatePassWord(mEdtPassword.getText().toString())) {
            showToast(getString(R.string.validate_done));
        }
    }

    private void initViews() {
        mEdtUserName = findViewById(R.id.edtUserName);
        mEdtPassword = findViewById(R.id.edtPassword);
        mBtnLogin = findViewById(R.id.btnLogin);
    }

    private boolean validateUserName(String userName) {
        boolean validationUserName = false;
        if (userName.length() == 0) {
            showToast(getString(R.string.user_name_empty));
        } else if (!UserNameValidation.checkLength(userName)) {
            showToast(getString(R.string.test_user_name_length));
        } else if (!UserNameValidation.checkCapitalLetter(userName)) {
            showToast(getString(R.string.test_user_name_capital_letter));
        } else if (!UserNameValidation.checkHaveSpace(userName)) {
            showToast(getString(R.string.test_user_name_have_space));
        } else if (!UserNameValidation.checkCharacters(userName)) {
            showToast(getString(R.string.test_user_name_special_character));
        } else if (!UserNameValidation.checkDigitNumber(userName)) {
            showToast(getString(R.string.test_user_name_digit_number));
        } else if (!UserNameValidation.checkUpperCaseOrLowerCase(userName)) {
            showToast(getString(R.string.test_user_name_upper_case_or_lower_case));
        } else {
            validationUserName = true;
        }
        return validationUserName;
    }

    private boolean validatePassWord(String password) {
        boolean validationPassword = false;
        if (password.length() == 0) {
            showToast(getString(R.string.password_empty));
        } else if (!PasswordValidation.checkLength(password)) {
            showToast(getString(R.string.test_password_length));
        } else if (!PasswordValidation.checkDifferentUserName(mEdtUserName.getText().toString(), password)) {
            showToast(getString(R.string.test_password_different_user_name));
        } else if (PasswordValidation.checkCharacterSpecialAndDigitNumber(password)) {
            showToast(getString(R.string.test_password_character_special_and_digit_number));
        } else if (!PasswordValidation.checkHaveSpace(password)) {
            showToast(getString(R.string.test_password_have_space));
        } else if (!PasswordValidation.checkCapitalLetter(password)) {
            showToast(getString(R.string.test_password_capital_letter));
        } else if (!PasswordValidation.checkSameCharacter(password)) {
            showToast(getString(R.string.test_password_same_character));
        } else {
            validationPassword = true;
        }
        return validationPassword;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
