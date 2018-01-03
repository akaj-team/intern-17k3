package vn.asiantech.internship.ui.unittest;

import android.os.Bundle;
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
            if (!UserValidation.checkLengthUserName(userName)) {
                showToast("length must more than 5 and a little than 24");
            } else {
                showToast("good username");
            }
        } else {
            showToast("null input username");
        }
    }

    private void passwordValidator(String password) {
        if (password.length() != 0) {
            if (!UserValidation.checkLengthPassword(password)) {
                showToast("lenght must more than 7");
            } else {
                showToast("good password");
            }
        } else {
            showToast("null input password");
        }
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        userNameValidator(mEdtUserName.getText().toString().trim());
        passwordValidator(mEdtPassWord.getText().toString().trim());
    }
}
