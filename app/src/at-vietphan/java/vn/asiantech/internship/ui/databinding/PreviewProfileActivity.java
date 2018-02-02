package vn.asiantech.internship.ui.databinding;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;
import vn.asiantech.internship.databinding.ActivityPreviewProfileBinding;
import vn.asiantech.internship.models.ProfileUser;

public class PreviewProfileActivity extends AppCompatActivity {
    public static final int EDIT_USER_REQUEST_CODE = 1;
    private ActivityPreviewProfileBinding mPreviewProfileBinding;
    private ProfileUser mProfileUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreviewProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_preview_profile);
        mProfileUser = new ProfileUser();
        mProfileUser.setName("viet");
        mProfileUser.setEmail("viet.phan@asiantech.vn");
        mProfileUser.setBirthDate("Apr 02, 1996");
        mProfileUser.setGenDer(0);
        mProfileUser.setPhone("0979047004");
        mProfileUser.setImageUrl("https://znews-photo-td.zadn.vn/w1024/Uploaded/ohunua2/2017_03_24/huyen3.jpg");
        mPreviewProfileBinding.setProfileUser(mProfileUser);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_USER_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            ProfileUser profileUser = data.getParcelableExtra(ProfileUser.class.getSimpleName());
            mProfileUser.setName(profileUser.name);
            mProfileUser.setEmail(profileUser.email);
            mProfileUser.setBirthDate(profileUser.birthDate);
            mProfileUser.setGenDer(profileUser.genDer);
            mProfileUser.setPhone(profileUser.phone);
            mPreviewProfileBinding.setProfileUser(mProfileUser);
        }
    }
}
