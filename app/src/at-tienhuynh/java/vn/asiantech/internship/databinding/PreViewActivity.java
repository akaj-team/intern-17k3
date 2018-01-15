package vn.asiantech.internship.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;

public class PreViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vn.asiantech.internship.databinding.ActivityPreViewBinding activityPreViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_pre_view);
        // get data
        Bundle bundle = getIntent().getExtras();
        // setBinding
        if (bundle != null) {
            activityPreViewBinding.setUser((User) bundle.getParcelable(User.class.getSimpleName()));
        }
    }
}