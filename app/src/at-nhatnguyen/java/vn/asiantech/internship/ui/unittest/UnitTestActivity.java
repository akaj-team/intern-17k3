package vn.asiantech.internship.ui.unittest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.asiantech.internship.R;

public class UnitTestActivity extends AppCompatActivity {
    private EditText mEdtUserName;
    private EditText mEdtPassWord;
    private Button mBtnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_test);
        initViews();
        initListeners();
    }

    private void initViews() {
        mEdtUserName = findViewById(R.id.edtUserName);
        mEdtPassWord = findViewById(R.id.edtPassWord);
        mBtnSubmit = findViewById(R.id.btnSubmit);
    }

    private void initListeners() {
        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private void showToast(String a) {
        Toast.makeText(this, a, Toast.LENGTH_SHORT).show();
    }

    private void validate() {
        if (!ValidateUserName.isLengthUserName(mEdtUserName.getText().toString().trim())) {
            showToast(getResources().getString(R.string.user_name_length));
        } else if (!ValidateUserName.isUpperCaseUserName(mEdtUserName.getText().toString())) {
            showToast(getResources().getString(R.string.user_name_upper_case));
        } else if (!ValidateUserName.isSpecialCaseUserName(mEdtUserName.getText().toString())) {
            showToast(getResources().getString(R.string.user_name_special_case));
        } else if (!ValidateUserName.isNumberUserName(mEdtUserName.getText().toString())) {
            showToast(getResources().getString(R.string.user_name_number));
        } else if (!ValidateUserName.
                isUserNameUpperCaseOrLowerCase(mEdtUserName.getText().toString())) {
            showToast(getResources().getString(R.string.user_name_care));
        } else if (ValidatePassWord.isPassWordLikeUserName(mEdtUserName.getText().toString()
                , mEdtPassWord.getText().toString())) {
            showToast(getResources().getString(R.string.user_name_like));
        } else if (!ValidatePassWord.isSpecialNumberPassWord(mEdtPassWord.getText().toString())) {
            showToast(getResources().getString(R.string.password_special));
        } else if (!ValidatePassWord.isLengthPassWord(mEdtPassWord.getText().toString()) || !ValidatePassWord.isAppearCase(mEdtPassWord.getText().toString())) {
            showToast(getResources().getString(R.string.password_length));
        } else if (!ValidatePassWord.isSpaceCase(mEdtPassWord.getText().toString())) {
            showToast(getResources().getString(R.string.password_space));
        } else if (!ValidatePassWord.isLeastThreeUpperCase(mEdtPassWord.getText().toString())) {
            showToast(getResources().getString(R.string.password_three_upper_case));
        } else {
            showToast(getResources().getString(R.string.pass));
        }
    }
}
