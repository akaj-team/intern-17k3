package vn.asiantech.internship.ui.databinding;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;
import vn.asiantech.internship.databinding.ActivityProfileBinding;
import vn.asiantech.internship.models.ProfileUser;

public class ProfileActivity extends AppCompatActivity {
    private ActivityProfileBinding mActivityProfileBinding;
    public static final int EDIT_PROFILE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        ProfileUser profileUser = new ProfileUser("nhat", "nhat.com", 1, "0909090909", "https://onehdwallpaper.com/wp-content/uploads/2016/11/Most-Beautiful-Girls-Wallpapers.jpg");
        mActivityProfileBinding.setProfileUser(profileUser);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_PROFILE_REQUEST_CODE && data != null && resultCode == RESULT_OK) {
            mActivityProfileBinding.setProfileUser((ProfileUser) data.getParcelableExtra(ProfileUser.class.getSimpleName()));
        }
    }
}
