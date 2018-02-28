package vn.asiantech.internship.databinding;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.User;

/**
 * Created by anh.quach on 1/10/18.
 * Edit Profile Activity
 */
public class PreEditProfileActivity extends AppCompatActivity {
    public static final int EDIT_USER_REQUEST_CODE = 1;
    private User mUser;
    private ActivityPreEditProfileBinding mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_pre_edit_profile);
        mUser = new User();
        setData();
        mBinding.setUser(mUser);
    }

    private void setData() {
        mUser.setAvatar("https://i1.wp.com/therighthairstyles.com/wp-content/uploads/2013/12/5-wavy-brunette-bob.jpg?w=500&ssl=1");
        mUser.setFullname("Ngoc Anh");
        mUser.setBirthday("Sep 28,1997");
        mUser.setGender(0);
        mUser.setEmail("anh.quach@asiantech");
        mUser.setContactNumber("01679961569");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_USER_REQUEST_CODE && data != null) {
            User user = data.getParcelableExtra(User.class.getSimpleName());
            mUser.setFullname(mBinding.getUser().fullName);
            mUser.setGender(mBinding.getUser().gender);
            mUser.setContactNumber(mBinding.getUser().contactNumber);
            mUser.setBirthday(mBinding.getUser().birthday);
            mUser.setEmail(mBinding.getUser().email);
            mBinding.setUser(user);
        }
    }
}
