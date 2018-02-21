package vn.asiantech.internship.ui.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.databinding.ActivityEditProfileBinding;
import vn.asiantech.internship.models.ProfileUser;
import vn.asiantech.internship.utils.MyEditTextClear;

public class EditProfileActivity extends AppCompatActivity implements TextWatcher {
    private MyEditTextClear mEdtName;
    private MyEditTextClear mEdtEmail;
    private MyEditTextClear mEdtPhoneNumber;
    private Button mBtnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEditProfileBinding activityEditProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);
        ProfileUser profileUser = getIntent().getParcelableExtra(ProfileUser.class.getSimpleName());
        if (profileUser != null) {
            activityEditProfileBinding.setProfileUser(profileUser);
        }
        initViews();
        initListeners();
    }

    private void initViews() {
        mEdtName = findViewById(R.id.edtName);
        mEdtEmail = findViewById(R.id.edtEmail);
        mEdtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        mBtnSubmit = findViewById(R.id.btnSubmit);
    }

    private void initListeners() {
        mEdtName.addTextChangedListener(this);
        mEdtEmail.addTextChangedListener(this);
        mEdtPhoneNumber.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // No-op
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (TextUtils.isEmpty(mEdtName.getText().toString().trim())
                || TextUtils.isEmpty(mEdtEmail.getText().toString().trim())
                || TextUtils.isEmpty(mEdtPhoneNumber.getText().toString().trim())) {
            mBtnSubmit.setEnabled(false);
        } else {
            mBtnSubmit.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        // No-op
    }
}
