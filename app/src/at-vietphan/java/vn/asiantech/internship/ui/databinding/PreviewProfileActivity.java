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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreviewProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_preview_profile);
        ProfileUser profileUser = new ProfileUser();
        profileUser.setName("viet");
        profileUser.setEmail("viet.phan@asiantech.vn");
        profileUser.setBirthDate("Apr 02, 1996");
        profileUser.setGenDer(0);
        profileUser.setPhone("0979047004");
        profileUser.setImageUrl("https://znews-photo-td.zadn.vn/w1024/Uploaded/ohunua2/2017_03_24/huyen3.jpg");
        mPreviewProfileBinding.setProfileUser(profileUser);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_USER_REQUEST_CODE && data != null) {
            mPreviewProfileBinding.setProfileUser((ProfileUser) data.getParcelableExtra(ProfileUser.class.getSimpleName()));
        }
    }
}
