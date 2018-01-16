package vn.asiantech.internship.ui.databinding;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;
import vn.asiantech.internship.databinding.ActivityPreviewProfileBinding;
import vn.asiantech.internship.models.ProfileUser;

public class PreviewProfileActivity extends AppCompatActivity {
    private ActivityPreviewProfileBinding mActivityPreviewProfileBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityPreviewProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_preview_profile);
        ProfileUser profileUser = new ProfileUser();
        profileUser.setName("viet");
        profileUser.setEmail("viet.phan@asiantech.vn");
        profileUser.setBirthDate("2/4/1996");
        profileUser.setGenDer(0);
        profileUser.setPhone("0979047004");
        profileUser.setImageUrl("http://2sao.vietnamnetjsc.vn/images/2017/03/24/20/38/Anh-doi-thuong-xinh-dep-cua-hot-girl-bong-chuyen-tuyen-Viet-Nam-3.jpg");
        mActivityPreviewProfileBinding.setProfileUser(profileUser);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ProfileUser.REQUEST_CODE && data != null) {
            mActivityPreviewProfileBinding.setProfileUser((ProfileUser) data.getParcelableExtra(ProfileUser.class.getSimpleName()));
        }
    }
}
