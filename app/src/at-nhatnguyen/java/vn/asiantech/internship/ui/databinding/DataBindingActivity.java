package vn.asiantech.internship.ui.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;
import vn.asiantech.internship.databinding.ActivityDataBindingBinding;
import vn.asiantech.internship.models.ProfileUser;

public class DataBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindingBinding activityDataBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        activityDataBindingBinding.setProfileUser(new ProfileUser("nhat", "nhat.com", 0, "0909090909"));
    }
}
