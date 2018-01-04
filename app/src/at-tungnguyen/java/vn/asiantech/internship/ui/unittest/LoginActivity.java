package vn.asiantech.internship.ui.unittest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.validation.PasswordValidation;
import vn.asiantech.internship.ui.validation.UserValidation;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 08/12/2017.
 */
public class LoginActivity extends AppCompatActivity {
    private EditText mEdtName;
    private EditText mEdtPass;
    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);
        initViews();
        initListener();
    }

    /**
     * initView LoginActivity
     */
    private void initViews() {
        mEdtName = findViewById(R.id.edtUserName);
        mEdtPass = findViewById(R.id.edtPassWord);
        mBtnLogin = findViewById(R.id.btnLoginUnit);
    }

    /**
     * initListener LoginActivity
     */
    private void initListener() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidate();
            }
        });
    }

    /**
     * showToast in checkValidate
     *
     * @param abc
     */
    private void showToast(String abc) {
        Toast.makeText(this, abc, Toast.LENGTH_SHORT).show();
    }

    /**
     * checkValidate LoginActivity
     */
    private void checkValidate() {
        String userName = mEdtName.getText().toString();
        String password = mEdtPass.getText().toString();
        if (!UserValidation.ischeckUserLenght(userName)) {
            showToast(getResources().getString(R.string.user_name_lenght));
        } else if (!UserValidation.isCheckUserCapitalAndNumber(userName)) {
            showToast(getResources().getString(R.string.user_name_capital_or_number));
        } else if (!UserValidation.isCheckUserSpace(userName)) {
            showToast(getResources().getString(R.string.user_name_not_space));
        } else if (!UserValidation.isCheckUserSpecial(userName)) {
            showToast(getResources().getString(R.string.user_name_not_character_special));
        } else if (!PasswordValidation.isCheckPassCapital(password)) {
            showToast(getResources().getString(R.string.password_three_character_Capital));
        } else if (!PasswordValidation.isCheckPassLenght(password)) {
            showToast(getResources().getString(R.string.password_character_lenght));
        } else if (!PasswordValidation.isCheckPassRepeat(password)) {
            showToast(getResources().getString(R.string.password_not_repeat));
        } else if (!PasswordValidation.isPasswordSpace(password)) {
            showToast(getResources().getString(R.string.password_not_space));
        } else if (!PasswordValidation.isCheckPasswordDifferentUser(userName, password)) {
            showToast(getResources().getString(R.string.password_user_different));
        }
    }

}
