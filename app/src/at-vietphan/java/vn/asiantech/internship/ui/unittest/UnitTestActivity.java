package vn.asiantech.internship.ui.unittest;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.asiantech.internship.R;

/**
 * Class UnitTestActivity
 */
public class UnitTestActivity extends AppCompatActivity implements View.OnClickListener {
    boolean mCheckLogin = false;
    private EditText mEdtUserName;
    private EditText mEdtPassWord;
    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_test);
        initViews();
        initListener();
    }

    private void initViews() {
        mEdtUserName = findViewById(R.id.edtUserName);
        mEdtPassWord = findViewById(R.id.edtPassword);
        mBtnLogin = findViewById(R.id.btnLogin);
    }

    private void initListener() {
        mBtnLogin.setOnClickListener(this);
    }

    private void userNameValidator(String userName) {
        if (userName.length() != 0) {
            if (!UserValidation.isLengthUserName(userName)) {
                showToast(getString(R.string.check_length_username));
            } else if (!UserValidation.isLeastOneCapitalLetterUserName(userName)) {
                showToast(getString(R.string.check_username_least_one_capital_letter));
            } else if (!UserValidation.isSpecialCharAndSpaceUserName(userName)) {
                showToast(getString(R.string.check_username_special_char_and_white_space));
            } else if (!UserValidation.isMostTwoNumberUserName(userName)) {
                showToast(getString(R.string.check_username_most_two_number));
            } else if (!UserValidation.isUpperCaseLowercaseUserName(userName)) {
                showToast(getString(R.string.check_username_uppercase_lowercase));
            } else {
                mCheckLogin = true;
            }
        } else {
            showToast(getString(R.string.null_input_username));
        }
    }

    private void passwordValidator(String username, String password) {
        if (password.length() != 0) {
            if (!UserValidation.isPasswordDifferentUserName(username, password)) {
                showToast(getString(R.string.check_password_different_username));
            } else if (!UserValidation.isAtLeastSpecialOrNumber(password)) {
                showToast(getString(R.string.check_password_at_least_special_or_number));
            } else if (!UserValidation.isLengthPassword(password)) {
                showToast(getString(R.string.check_password_length));
            } else if (UserValidation.isRepeatCharacterPassword(password)) {
                showToast(getString(R.string.check_password_repeat_charater_a_little_two_times));
            } else if (!UserValidation.isSpacePassword(password)) {
                showToast(getString(R.string.check_password_have_not_white_space));
            } else if (!UserValidation.isLestThreeCharacters(password)) {
                showToast(getString(R.string.check_password_have_least_three_characters));
            } else {
                mCheckLogin = true;
            }
        } else {
            showToast(getString(R.string.null_input_password));
        }
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if (mCheckLogin) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.notification);
            builder.setMessage(R.string.login_success);
            AlertDialog dialog = builder.create();
            dialog.setCancelable(true);
            dialog.show();
        }
        userNameValidator(mEdtUserName.getText().toString().trim());
        passwordValidator(mEdtUserName.getText().toString().trim(), mEdtPassWord.getText().toString().trim());
    }
}
