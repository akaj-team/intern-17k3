package vn.asiantech.internship.unittest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.asiantech.internship.R;

/**
 * Created by TienHuynh on 02/01/2018.
 * AsianTech Co., Ltd
 */
public class LoginUnitTestActivity extends AppCompatActivity {
    private EditText mEdtUserName;
    private EditText mEdtPassWord;
    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_unit_test);
        initViews();
        initListener();
    }

    /**
     * Init Views
     */
    private void initViews() {
        mEdtUserName = findViewById(R.id.edtUserName);
        mEdtPassWord = findViewById(R.id.edtPassWord);
        mBtnLogin = findViewById(R.id.btnLoginUnitTest);
    }

    /**
     * Init Listener
     */
    private void initListener() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // validate
                validate();
            }
        });
    }


    /**
     * this method used to validate EditText
     */
    private void validate() {
        // validation user name
        if (isValidateUserName(mEdtUserName.getText().toString()) &&
                isValidatePassWord(mEdtPassWord.getText().toString())) {
            showToast(getString(R.string.validate_done));
        }
    }

    /**
     * Validate for username
     *
     * @param username username
     * @return isValidationUserName
     */
    private boolean isValidateUserName(String username) {
        boolean isValidationUserName = false;
        if (username.length() == 0) {
            showToast(getString(R.string.null_user_name));
        } else if (!UserNameValidation.isLengthUserName(username)) {
            showToast(getString(R.string.validate_username_length));
        } else if (!UserNameValidation.isCapitalLetterAndNumber(username)) {
            showToast(getString(R.string.validate_username_capture_number));
        } else if (!UserNameValidation.isUserNameSpace(username)) {
            showToast(getString(R.string.validate_username_space));
        } else if (!UserNameValidation.isUserNameSpecialChar(username)) {
            showToast(getString(R.string.validate_username_special_char));
        } else if (!UserNameValidation.isUserNameIgnoreUpperCase(username)) {
            showToast(getString(R.string.validate_username_no_distinction));
        } else {
            isValidationUserName = true;
        }
        return isValidationUserName;
    }

    /**
     * Validate for password
     *
     * @param password password
     * @return isValidationPassword
     */
    private boolean isValidatePassWord(String password) {
        boolean isValidationPassword = false;
        if (password.length() == 0) {
            showToast(getString(R.string.null_password));
        } else if (!PasswordValidation.isLength(password)) {
            showToast(getString(R.string.validate_password_length));
        } else if (!PasswordValidation.isDifferentUserName(mEdtUserName.getText().toString(), password)) {
            showToast(getString(R.string.validate_password_different_user_name));
        } else if (PasswordValidation.isAtLeastNumber(password)) {
            showToast(getString(R.string.validate_password_have_least_number));
        } else if (!PasswordValidation.isPasswordSpace(password)) {
            showToast(getString(R.string.validate_password_space));
        } else if (!PasswordValidation.isCapitalLetter(password)) {
            showToast(getString(R.string.validate_password_least_3_capital_letter));
        } else if (!PasswordValidation.isRepeatCharacter(password)) {
            showToast(getString(R.string.validate_password_repeat));
        } else {
            isValidationPassword = true;
        }
        return isValidationPassword;
    }

    /**
     * Show Toast to notice
     *
     * @param message message
     */
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
