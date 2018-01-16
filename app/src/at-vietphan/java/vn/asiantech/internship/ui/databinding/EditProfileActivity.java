package vn.asiantech.internship.ui.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;
import vn.asiantech.internship.databinding.ActivityEditProfileBinding;
import vn.asiantech.internship.models.ProfileUser;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEditProfileBinding activityEditProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);
        if (getIntent().getParcelableExtra(ProfileUser.class.getSimpleName()) != null) {
            activityEditProfileBinding.setProfileUser((ProfileUser) getIntent().getParcelableExtra(ProfileUser.class.getSimpleName()));
        }
    }
}
