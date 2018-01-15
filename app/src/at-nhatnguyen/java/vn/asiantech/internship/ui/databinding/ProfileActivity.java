package vn.asiantech.internship.ui.databinding;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import vn.asiantech.internship.R;
import vn.asiantech.internship.databinding.ActivityProfileBinding;
import vn.asiantech.internship.models.ProfileUser;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProfileBinding activityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        activityProfileBinding.setProfileUser((ProfileUser) getIntent().getParcelableExtra("profile"));
    }
}
